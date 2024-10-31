package com.flourimus.users.factory.repository.custom;

import java.util.Optional;
import com.flourimus.users.dto.CustomerByEmailAndPasswordRequest;
import com.flourimus.users.dto.OrganisationVitalsResponse;
import com.flourimus.users.factory.entity.Customer;

public interface CustomerCustomRepository {

    public Optional<Customer> findCustomerByEmailAndPassword(final CustomerByEmailAndPasswordRequest request,
            final OrganisationVitalsResponse organisationVitals);
}
