package com.flourimus.users.factory.dao;

import java.util.Optional;

import com.flourimus.users.dto.CustomerByEmailAndPasswordRequest;
import com.flourimus.users.dto.OrganisationVitalsResponse;
import com.flourimus.users.factory.entity.Customer;

public interface CustomerDao {

    Customer save(final Customer customer);

    Optional<Customer> findCustomerById(final Integer id);

    Optional<Customer> findCustomerByEmailAndPassword(final CustomerByEmailAndPasswordRequest request,
            final OrganisationVitalsResponse OrganisationVitals);

}
