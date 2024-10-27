package com.flourimus.users.resolvers;

import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.facade.CustomerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomersResolvers {

    private final CustomerFacade customerFacade;

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve.
     * @return a Mono that emits the CustomerDto of the specified customer.
     *         Requires the user to have ROLE_ADMIN authority.
     */
    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<CustomerDto> getCustomer(@Argument final Integer id) {
        log.info("Requesting for customer by id: {}", id);
        return customerFacade.getCustomer(id);
    }

}
