package com.flourimus.users.dto;

import com.flourimus.users.enums.TokensKeys;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerTokenDto {

    private TokensKeys key;

    private String value;
}
