package com.flourimus.users.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phone {

    private String dialCode;

    private String number;
}
