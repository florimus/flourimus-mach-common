package com.flourimus.users.factory.repository.custom;

import com.flourimus.users.dto.OrganisationVitalsResponse;

public class QueryStringConstructor {

    public static String customerByEmail = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password";

    public static String customerByEmail(OrganisationVitalsResponse organisationVitals) {
        if (organisationVitals.isOrganizationId()) {
            customerByEmail = customerByEmail + " AND c.organizationId = :organizationId";
        }
        if (organisationVitals.isChannelId()) {
            customerByEmail = customerByEmail + " AND c.channelId = :channelId";
        }
        if (organisationVitals.isBrandId()) {
            customerByEmail = customerByEmail + " AND c.brandId = :brandId";
        }
        if (organisationVitals.isChannelId()) {
            customerByEmail = customerByEmail + " AND c.locationId = :locationId";
        }
        return customerByEmail;
    }
}
