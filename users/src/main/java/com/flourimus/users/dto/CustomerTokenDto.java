package com.flourimus.users.dto;

import com.flourimus.users.enums.TokensKeys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTokenDto {

    private TokensKeys key;

    private String value;
}
