package com.flourimus.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganisationVitalsRequest {

    private Integer organizationId;

    private Integer locationId;

    private Integer brandId;

    private Integer channelId;
}
