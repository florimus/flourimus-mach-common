package com.flourimus.cas.service;

import com.flourimus.cas.common.constants.CasErrorCodes;
import com.flourimus.cas.common.utils.JwtTokenUtil;
import com.flourimus.cas.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import com.flourimus.cas.api.CustomerApis;
import com.flourimus.cas.dto.CustomerDto;
import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.dto.TokenResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final CustomerApis customerApis;

    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Retrieves a token response for the provided email and password input.
     *
     * This method searches for a customer using the provided email and password
     * details, and generates a token response. If the token creation fails, it
     * throws a BadRequestException.
     *
     * @param emailAndPasswordTokenInput the input containing email and password
     *                                   details.
     * @return a Mono that emits a TokenResponse with the token information.
     * @throws BadRequestException if the token creation fails.
     */
    @Override
    public Mono<TokenResponse> getTokenByEmailAndPassword(final EmailAndPasswordTokenInput emailAndPasswordTokenInput) {
        log.info("Searching for the customer with details: {}", emailAndPasswordTokenInput);
        CustomerDto customer = customerApis.getCustomerByEmailAndPassword(emailAndPasswordTokenInput);
        log.info("Received customer with details: {}", customer);

        // Note: Setting organizational data to customer from request.
        // Inorder to handle the mismatch may occur from organizational
        // authentication-layer configurations.
        customer.setOrganizationId(emailAndPasswordTokenInput.getOrganizationId());
        customer.setChannelId(emailAndPasswordTokenInput.getChannelId());
        customer.setBrandId(emailAndPasswordTokenInput.getBrandId());
        customer.setLocationId(emailAndPasswordTokenInput.getLocationId());

        try {
            log.info("Creating token customer with details: {}", customer);
            TokenResponse response = jwtTokenUtil.populateTokenResponse(customer, false);
            return Mono.just(response);
        } catch (Exception e) {
            throw new BadRequestException("Cannot create JWE token", CasErrorCodes.JWE_CREATION_FAILED);
        }
    }

}
