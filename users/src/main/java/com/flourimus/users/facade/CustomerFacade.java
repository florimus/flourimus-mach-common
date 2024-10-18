package com.flourimus.users.facade;

import com.flourimus.users.dto.Customer;
import org.springframework.graphql.data.method.annotation.Argument;

public interface CustomerFacade {

    public Customer getCustomer(@Argument String id);

}
