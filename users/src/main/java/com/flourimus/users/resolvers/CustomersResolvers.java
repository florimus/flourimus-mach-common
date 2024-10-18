package com.flourimus.users.resolvers;

import com.flourimus.users.dto.Customer;
import com.flourimus.users.facade.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CustomersResolvers {

    private final CustomerFacade customerFacade;

    @QueryMapping
    public Customer getCustomer(@Argument String id) {
        return customerFacade.getCustomer(id);
    }

}
