package com.flourimus.users.service;

import com.flourimus.users.dto.Customer;
import org.springframework.graphql.data.method.annotation.Argument;

public interface CustomerService {

    public Customer getCustomer(@Argument String id);

}
