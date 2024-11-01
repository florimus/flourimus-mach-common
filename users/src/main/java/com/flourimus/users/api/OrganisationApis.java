package com.flourimus.users.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.flourimus.users.dto.OrganisationVitalsRequest;
import com.flourimus.users.dto.OrganisationVitalsResponse;
import com.flourimus.users.lib.graphql.OrganizationSchemas;
import com.flourimus.users.lib.service.GraphQLService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganisationApis {

    private static final String TARGET_URL = "http://localhost:8503/graphql";

    private final GraphQLService graphQLService;

    public OrganisationVitalsResponse getCustomerByEmailAndPassword(final OrganisationVitalsRequest organisationVitalsRequest) {
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("organizationId", organisationVitalsRequest.getOrganizationId());
        variables.put("brandId", organisationVitalsRequest.getBrandId());
        variables.put("locationId", organisationVitalsRequest.getLocationId());
        variables.put("channelId", organisationVitalsRequest.getChannelId());
        return graphQLService.callGraphQLApi(TARGET_URL, OrganizationSchemas.authenticationLayerSettingsByVitals, variables, OrganisationVitalsResponse.class);
    }
}
