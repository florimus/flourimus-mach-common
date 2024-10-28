package com.flourimus.cas.service;

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
    /**
     * Retrieves a token response for the provided email and password input.
     *
     * @param emailAndPasswordTokenInput the input containing email and password details.
     * @return a Mono that emits a TokenResponse with the token information.
     */
    @Override
    public Mono<TokenResponse> getTokenByEmailAndPassword(final EmailAndPasswordTokenInput emailAndPasswordTokenInput) {
        CustomerDto customer = customerApis.getCustomerByEmailAndPassword(emailAndPasswordTokenInput);
        return Mono.just(TokenResponse.builder().email(customer.getEmail()).build());
    }
    
}
