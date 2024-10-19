package com.flourimus.users.facade;

import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerService customerService;

    @Override
    public CustomerDto getCustomer(final Integer id) {
        return customerService.getCustomer(id);
    }
}
