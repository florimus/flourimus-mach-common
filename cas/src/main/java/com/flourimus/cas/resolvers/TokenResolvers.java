package com.flourimus.cas.resolvers;

import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.dto.TokenResponse;
import com.flourimus.cas.facade.TokenFacade;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TokenResolvers {

    private final TokenFacade tokenFacade;

    /**
     * Retrieves a token for the given email and password input.
     *
     * @param emailAndPasswordTokenInput the input containing email and password
     *                                   details.
     * @return a Mono that emits a TokenResponse containing the token information.
     */
    @QueryMapping
    public Mono<TokenResponse> getTokenByEmailAndPassword(
            @Argument final @Valid EmailAndPasswordTokenInput emailAndPasswordTokenInput) {
        log.info("Requesting token for : {}", emailAndPasswordTokenInput);
        return tokenFacade.getTokenByEmailAndPassword(emailAndPasswordTokenInput);
    }
}
