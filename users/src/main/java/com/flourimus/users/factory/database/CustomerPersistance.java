package com.flourimus.users.factory.database;

import org.springframework.stereotype.Component;

import com.flourimus.users.factory.dao.CustomerDao;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.factory.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerPersistance implements CustomerDao {

    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return customerRepository.getReferenceById(id);
    }
    
}
