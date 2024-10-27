package com.flourimus.users.service;

import com.flourimus.users.common.constants.CustomerErrorCodes;
import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.exceptions.BadRequstException;
import com.flourimus.users.exceptions.NotFoundException;
import com.flourimus.users.factory.CustomerDaoFactory;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.helper.CustomerHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

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
     * @throws BadRequstException if the ID is null
     * @throws NotFoundException  if no customer is found with the given ID
     */
    @Override
    public Mono<CustomerDto> getCustomer(final Integer id) {
        log.info("Searching for customer by id: {}", id);
        if (id == null) {
            log.info("Cannot find customer with invalid id: {}", id);
            throw new BadRequstException("Id is mandatory", CustomerErrorCodes.INVALID_ID);
        }
        Customer customer = customerDaoFactory.getCustomerDao()
                .findCustomerById(id)
                .orElseThrow(() -> {
                    log.error("Customer not found with id: {}", id);
                    throw new NotFoundException("Customer not found", CustomerErrorCodes.NOT_FOUND);
                });
        log.info("Found customer: {}", customer);
        return Mono.just(CustomerHelper.convertCustomerToCustomerDto(customer));
    }
}
