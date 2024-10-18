package com.flourimus.users.service;

import com.flourimus.users.dto.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(String id) {
        return Customer.builder()
                .id(1)
                .email("guest@flourimus.com")
                .build();
    }
}
