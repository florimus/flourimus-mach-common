package com.flourimus.users.resolvers;

import com.flourimus.users.dto.Customer;
import com.flourimus.users.facade.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomersResolvers {

    private final CustomerFacade customerFacade;

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Customer> getCustomer(@Argument final String id) {
        return Mono.just(customerFacade.getCustomer(id));
    }

}
