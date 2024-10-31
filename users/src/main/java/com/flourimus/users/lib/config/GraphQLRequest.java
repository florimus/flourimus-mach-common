package com.flourimus.users.lib.config;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GraphQLRequest {

    private String query;
    
    private Map<String, Object> variables;
}
