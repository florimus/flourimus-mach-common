package com.flourimus.cas.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.flourimus.cas.dto.CustomerDto;
import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.lib.graphql.UserSchemas;
import com.flourimus.cas.lib.service.GraphQLService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerApis {

    private static final String TARGET_URL = "http://localhost:8500/graphql";

    private final GraphQLService graphQLService;

    public CustomerDto getCustomerByEmailAndPassword(final EmailAndPasswordTokenInput emailAndPasswordTokenInput) {
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("id", emailAndPasswordTokenInput.getBrandId());
        return graphQLService.callGraphQLApi(TARGET_URL, UserSchemas.customerByEmailAndPassword, variables, CustomerDto.class);
    }
}
