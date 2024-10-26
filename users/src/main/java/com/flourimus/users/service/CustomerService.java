package com.flourimus.users.service;

import com.flourimus.users.dto.CustomerDto;

import reactor.core.publisher.Mono;

public interface CustomerService {

    public Mono<CustomerDto> getCustomer(final Integer id);

}
