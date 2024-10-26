package com.flourimus.users.service;

import com.flourimus.users.dto.CustomerDto;

import reactor.core.publisher.Mono;

import org.springframework.graphql.data.method.annotation.Argument;

public interface CustomerService {

    public Mono<CustomerDto> getCustomer(@Argument Integer id);

}
