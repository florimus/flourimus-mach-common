package com.flourimus.cas.lib.graphql;

public class UserSchemas {
    public static final String customerByEmailAndPassword = """
        query GetCustomer($id: ID!) {
            response: getCustomer(id: $id) {
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
}
