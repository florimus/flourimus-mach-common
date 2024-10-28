package com.flourimus.cas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer roleId;

    private Integer organizationId;

    private Integer locationId;

    private Integer brandId;

    private Integer channelId;

}
