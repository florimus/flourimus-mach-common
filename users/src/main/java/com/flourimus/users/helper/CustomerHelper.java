package com.flourimus.users.helper;

import org.springframework.beans.BeanUtils;

import com.flourimus.users.dto.CustomerByEmailAndPasswordRequest;
import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.dto.CustomerTokenDto;
import com.flourimus.users.dto.OrganisationVitalsRequest;
import com.flourimus.users.dto.PhoneDto;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.factory.entity.CustomerToken;
import com.flourimus.users.factory.entity.Phone;

public class CustomerHelper {

    /**
     * Converts a Phone entity to a PhoneDto.
     * 
     * @param phone the phone entity to convert
     * @return the converted phone dto
     */
    public static PhoneDto convertPhoneToPhoneDto(final Phone phone) {
        PhoneDto updatedPhone = new PhoneDto();
        BeanUtils.copyProperties(phone, updatedPhone);
        return updatedPhone;
    }

    /**
     * Converts a CustomerToken entity to a CustomerTokenDto.
     * 
     * @param customerToken the customer token entity to convert
     * @return the converted customer token dto
     */
    public static CustomerTokenDto convertCustomerTokenToCustomerTokenDto(final CustomerToken customerToken) {
        CustomerTokenDto updatedCustomerToken = new CustomerTokenDto();
        BeanUtils.copyProperties(customerToken, updatedCustomerToken);
        return updatedCustomerToken;
    }

    /**
     * Converts a Customer entity to a CustomerDto.
     * 
     * @param customer the customer entity to convert
     * @return the converted customer dto
     */
    public static CustomerDto convertCustomerToCustomerDto(final Customer customer) {
        CustomerDto updatedCustomer = new CustomerDto();
        BeanUtils.copyProperties(customer, updatedCustomer);
        if (null != customer.getPhone()) {
            updatedCustomer.setPhone(convertPhoneToPhoneDto(customer.getPhone()));
        }
        if (null != customer.getTokens()) {
            updatedCustomer.setTokens(convertCustomerTokenToCustomerTokenDto(customer.getTokens()));
        }
        return updatedCustomer;
    }

    /**
     * Converts a CustomerByEmailAndPasswordRequest to an OrganisationVitalsRequest.
     * 
     * @param request the customer by email and password request to convert
     * @return the converted organisation vitals request
     */
    public static OrganisationVitalsRequest convertCustomerByEmailAndPasswordRequestToOrganisationVitalsRequest(
            final CustomerByEmailAndPasswordRequest request) {
        OrganisationVitalsRequest organisationVitalsRequest = new OrganisationVitalsRequest();
        BeanUtils.copyProperties(request, organisationVitalsRequest);
        return organisationVitalsRequest;
    }
}
