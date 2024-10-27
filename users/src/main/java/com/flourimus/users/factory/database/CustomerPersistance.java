package com.flourimus.users.factory.database;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.flourimus.users.factory.dao.CustomerDao;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.factory.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerPersistance implements CustomerDao {

    private final CustomerRepository customerRepository;

    /**
     * Saves a given customer. Use the returned instance for further operations
     * as the save operation might have changed the customer instance completely.
     *
     * @param customer the customer to save
     * @return the saved customer
     */
    @Override
    public Customer save(final Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieve a customer by ID.
     *
     * @param id the customer ID
     *
     * @return the customer found, or an empty optional if not found
     */
    @Override
    public Optional<Customer> findCustomerById(final Integer id) {
        return customerRepository.getCustomerById(id);
    }
    
}
