package com.flourimus.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganisationVitalsResponse {

    private boolean organizationId;

    private boolean locationId;

    private boolean brandId;

    private boolean channelId;
}
