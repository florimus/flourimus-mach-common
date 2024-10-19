package com.flourimus.users.service;

import com.flourimus.users.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomer(final Integer id) {
        return CustomerDto.builder()
                .id(1)
                .email("guest@flourimus.com")
                .build();
    }
}
