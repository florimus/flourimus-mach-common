package com.flourimus.users.factory;

import org.springframework.stereotype.Component;

import com.flourimus.users.factory.dao.CustomerDao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Data
public class CustomerDaoFactory {

    private final CustomerDao customerDao;
    
}
