package com.flourimus.users.factory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flourimus.users.factory.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
