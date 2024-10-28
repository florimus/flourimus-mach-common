package com.flourimus.cas.api;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.flourimus.cas.dto.CustomerDto;
import com.flourimus.cas.dto.EmailAndPasswordTokenInput;
import com.flourimus.cas.lib.service.GraphQLService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerApis {

    private final GraphQLService graphQLService;

    public CustomerDto getCustomerByEmailAndPassword(final EmailAndPasswordTokenInput emailAndPasswordTokenInput) {
        String query = """
                query GetCustomer {
                    response: getCustomer(id: " 1  ") {
                        id
                        firstName
                        lastName
                        email
                        roleId
                        organizationId
                        locationId
                        brandId
                        channelId
                    }
                }
                """;
        return graphQLService.callGraphQLApi(query, null, CustomerDto.class);
    }
}
