package com.flourimus.cas.service;

import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.dto.TokenResponse;

import reactor.core.publisher.Mono;

public interface TokenService {

    public Mono<TokenResponse> getTokenByEmailAndPassword(final EmailAndPasswordTokenInput emailAndPasswordTokenInput);
}
