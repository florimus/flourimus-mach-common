package com.flourimus.users.factory.repository.custom;

import com.flourimus.users.dto.OrganisationVitalsResponse;

public class QueryStringConstructor {

    /**
     * Builds a query string that selects a customer from the database, based on the values in a {@link OrganisationVitalsResponse}.
     * The query string will include the email of the customer, as well as any other organisation vitals that the response indicates should be included.
     * @param organisationVitals the organisation vitals to include in the query string
     * @return the query string
     */
    public static String customerByEmail(OrganisationVitalsResponse organisationVitals) {
        StringBuilder queryBuilder = new StringBuilder("SELECT c FROM Customer c WHERE c.email = :email");

        if (organisationVitals.isOrganizationId()) {
            queryBuilder.append(" AND c.organizationId = :organizationId");
        }
        if (organisationVitals.isLocationId()) {
            queryBuilder.append(" AND c.locationId = :locationId");
        }
        if (organisationVitals.isChannelId()) {
            queryBuilder.append(" AND c.channelId = :channelId");
        }
        if (organisationVitals.isBrandId()) {
            queryBuilder.append(" AND c.brandId = :brandId");
        }

        return queryBuilder.toString();
    }
}
