package com.flourimus.users.service;

import com.flourimus.users.common.constants.CustomerErrorCodes;
import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.exceptions.NotFoundException;
import com.flourimus.users.factory.CustomerDaoFactory;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.helper.CustomerHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
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
        log.info("Searching for customer by id: {}", id);
        return Mono.fromCallable(() -> {
            Customer customer = customerDaoFactory.getCustomerDao()
                    .findCustomerById(id)
                    .orElseThrow(() -> {
                        log.error("Customer not found with id: {}", id);
                        throw new NotFoundException("Customer not found", CustomerErrorCodes.NOT_FOUND);
                    });
            log.info("Found customer: {}", customer);
            return CustomerHelper.convertCustomerToCustomerDto(customer);
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
