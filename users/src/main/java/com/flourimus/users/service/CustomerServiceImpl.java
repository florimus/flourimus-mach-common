package com.flourimus.users.service;

import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.factory.CustomerDaoFactory;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.helper.CustomerHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDaoFactory customerDaoFactory;

    @Override
    public CustomerDto getCustomer(final Integer id) {
        Customer customer = customerDaoFactory.getCustomerDao().findCustomerById(id);
        return CustomerHelper.convertCustomerToCustomerDto(customer);

    }
}
