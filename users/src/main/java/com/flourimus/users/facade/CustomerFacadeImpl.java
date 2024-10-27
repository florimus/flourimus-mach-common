package com.flourimus.users.facade;

import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.service.CustomerService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerService customerService;

    /**
     * Return a customer by id.
     *
     * @param id the id of the customer.
     * @return a customerDto.
     */
    @Override
    public Mono<CustomerDto> getCustomer(final Integer id) {
        return customerService.getCustomer(id);
    }
}
