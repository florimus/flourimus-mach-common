package com.flourimus.users.dto;

import java.time.LocalDateTime;

import com.flourimus.users.common.enums.GrantTypes;

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

    private PhoneDto phone;

    private String avatar;

    private String password;

    private GrantTypes grandType;

    private LocalDateTime dob;

    private Integer roleId;

    private CustomerTokenDto tokens;

    private Integer organizationId;

    private Integer locationId;

    private Integer brandId;

    private Integer channelId;

    private boolean active;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

}
