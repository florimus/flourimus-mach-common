package com.flourimus.users.facade;

import com.flourimus.users.dto.Customer;
import com.flourimus.users.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerService customerService;

    @Override
    public Customer getCustomer(@Argument String id) {
        return customerService.getCustomer(id);
    }
}
