package com.flourimus.cas.facade;

import org.springframework.stereotype.Component;

import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.dto.TokenResponse;
import com.flourimus.cas.service.TokenService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TokenFacadeImpl implements TokenFacade {

    private final TokenService tokenService;

    /**
     * Retrieves a token for the given email and password input.
     *
     * @param emailAndPasswordTokenInput the input containing email and password
     *                                   details.
     * @return a Mono that emits a TokenResponse containing the token information.
     */
    @Override
    public Mono<TokenResponse> getTokenByEmailAndPassword(EmailAndPasswordTokenInput emailAndPasswordTokenInput) {
        return tokenService.getTokenByEmailAndPassword(emailAndPasswordTokenInput);
    }
    
}
