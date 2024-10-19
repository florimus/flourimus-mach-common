package com.flourimus.users.service;

import com.flourimus.users.dto.CustomerDto;
import org.springframework.graphql.data.method.annotation.Argument;

public interface CustomerService {

    public CustomerDto getCustomer(@Argument Integer id);

}
