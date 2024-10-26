package com.flourimus.users.service;

import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.exceptions.NotFoundException;
import com.flourimus.users.factory.CustomerDaoFactory;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.helper.CustomerHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDaoFactory customerDaoFactory;

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve.
     * @return a Mono that emits the CustomerDto of the specified customer.
     * @throws NotFoundException if the customer is not found.
     */
    @Override
    public Mono<CustomerDto> getCustomer(final Integer id) {
        return Mono.fromCallable(() -> {
            Customer customer = customerDaoFactory.getCustomerDao()
                    .findCustomerById(id)
                    .orElseThrow(() -> new NotFoundException("Customer not found", "CUSTOMER_NOT_FOUND", HttpStatus.SC_NOT_FOUND));
            return CustomerHelper.convertCustomerToCustomerDto(customer);
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
