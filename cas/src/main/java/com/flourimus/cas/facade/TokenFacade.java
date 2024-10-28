package com.flourimus.cas.facade;

import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.dto.TokenResponse;

import reactor.core.publisher.Mono;

public interface TokenFacade {

    public Mono<TokenResponse> getTokenByEmailAndPassword(final EmailAndPasswordTokenInput emailAndPasswordTokenInput);
}
