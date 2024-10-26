package com.flourimus.users.factory.dao;

import java.util.Optional;

import com.flourimus.users.factory.entity.Customer;

public interface CustomerDao {

    Customer save(final Customer customer);

    Optional<Customer> findCustomerById(final Integer id);
    
}
