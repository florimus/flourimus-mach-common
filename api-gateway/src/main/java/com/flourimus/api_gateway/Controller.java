package com.flourimus.api_gateway;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/fallback")
public class Controller {

    @RequestMapping   
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) 
    public Mono<String> fallback() {
        return Mono.just("Service is currently unavailable. Please try again later.");
    }

}
