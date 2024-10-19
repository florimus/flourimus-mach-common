package com.flourimus.users.helper;

import org.springframework.beans.BeanUtils;

import com.flourimus.users.dto.CustomerDto;
import com.flourimus.users.dto.CustomerTokenDto;
import com.flourimus.users.dto.PhoneDto;
import com.flourimus.users.factory.entity.Customer;
import com.flourimus.users.factory.entity.CustomerToken;
import com.flourimus.users.factory.entity.Phone;

public class CustomerHelper {

    public static PhoneDto convertPhoneToPhoneDto(final Phone phone) {
        PhoneDto updatedPhone = new PhoneDto();
        BeanUtils.copyProperties(phone, updatedPhone);
        return updatedPhone;
    }

    public static CustomerTokenDto convertCustomerTokenToCustomerTokenDto(final CustomerToken customerToken) {
        CustomerTokenDto updatedCustomerToken = new CustomerTokenDto();
        BeanUtils.copyProperties(customerToken, updatedCustomerToken);
        return updatedCustomerToken;
    }

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
}
