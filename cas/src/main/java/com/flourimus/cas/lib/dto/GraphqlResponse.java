package com.flourimus.cas.lib.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphqlResponse<T> {
    private ResponseData<T> data;

    private List<ErrorData> errors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseData<T> {
        private T response;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorData {

        private String message;
        
        private String code;
        
        private Integer status;
    }

}

