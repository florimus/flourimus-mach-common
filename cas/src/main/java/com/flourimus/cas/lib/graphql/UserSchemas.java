package com.flourimus.cas.lib.graphql;

public class UserSchemas {

    public static final String customerByEmailAndPassword = """
        query GetCustomerByEmailAndPassword(
            $email: String!,
            $password: String!,
            $organizationId: Int!,
            $locationId: Int!,
            $brandId: Int!,
            $channelId: Int!
        ) {
            response: getCustomerByEmailAndPassword(
                customerByEmailAndPasswordRequest: {
                  password: $password,
                  email: $email,
                  organizationId: $organizationId,
                  locationId: $locationId,
                  brandId: $brandId,
                  channelId: $channelId
                }
            ) {
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
        """
    ;

}
