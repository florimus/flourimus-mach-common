package com.flourimus.users.factory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.factory.repository.custom.CustomerCustomRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, CustomerCustomRepository {

    Optional<Customer> getCustomerById(final Integer id);

}
