package com.flourimus.users.service;

import org.springframework.graphql.data.method.annotation.Argument;

import com.flourimus.users.dto.CustomerByEmailAndPasswordRequest;
import com.flourimus.users.dto.CustomerDto;

import reactor.core.publisher.Mono;

public interface CustomerService {

    public Mono<CustomerDto> getCustomer(final Integer id);

    public Mono<CustomerDto> getCustomerByEmailAndPassword(@Argument final CustomerByEmailAndPasswordRequest customerByEmailAndPasswordRequest);

}
