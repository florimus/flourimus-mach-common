package com.flourimus.users.factory.dao;

import com.flourimus.users.factory.entity.Customer;

public interface CustomerDao {

    Customer save(Customer customer);

    Customer findCustomerById(Integer id);
    
}
