package com.flourimus.users.facade;

import com.flourimus.users.dto.CustomerDto;

import reactor.core.publisher.Mono;

public interface CustomerFacade {

    public Mono<CustomerDto> getCustomer(final Integer id);

}
