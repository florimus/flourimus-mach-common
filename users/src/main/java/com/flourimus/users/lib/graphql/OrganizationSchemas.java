package com.flourimus.users.lib.graphql;

public class OrganizationSchemas {
    public static final String authenticationLayerSettingsByVitals = """
        query FindAuthenticationLayerSettingsByVitals(
            $organizationId: ID!
            $brandId: ID!
            $locationId: ID!
            $channelId: ID!
        ) {
            response: getAuthenticationLayerSettingsByVitals(
                organizationId: $organizationId
                brandId: $brandId
                locationId: $locationId
                channelId: $channelId
            ) {
                organizationId
                brandId
                locationId
                channelId
            }
        }
    """;
}
