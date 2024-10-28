package com.flourimus.cas.lib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flourimus.cas.exceptions.ApiException;
import com.flourimus.cas.lib.config.GraphQLRequest;
import com.flourimus.cas.lib.dto.GraphqlResponse;
import com.flourimus.cas.lib.dto.GraphqlResponse.ErrorData;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GraphQLService {

    @Autowired
    private RestTemplate restTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TARGET_URL = "http://fedora:8900/users/graphql";

    /**
     * Performs a GraphQL query using the provided query string, variables, and
     * operation name.
     * 
     * @param query     the GraphQL query string
     * @param variables the variables to pass with the query
     * @param operation the operation name of the query
     * @param classtype the expected response type
     * @return the response object of the specified type, or null if the response is
     *         null
     */
    public <T> T callGraphQLApi(String query, Map<String, Object> variables, Class<T> classType) {
        GraphQLRequest request = new GraphQLRequest(query, variables);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<GraphQLRequest> entity = new HttpEntity<>(request, headers);
    
        log.debug("Sending HTTP request to URL: {}", TARGET_URL);
        log.debug("Request Headers: {}", headers);
        log.debug("Request Body: {}", request);
    
        ParameterizedTypeReference<GraphqlResponse<T>> responseType = new ParameterizedTypeReference<GraphqlResponse<T>>() {
        };
    
        ResponseEntity<GraphqlResponse<T>> graphqlResponse = restTemplate.exchange(TARGET_URL, HttpMethod.POST, entity, responseType);

        GraphqlResponse<T> body = graphqlResponse.getBody();
        List<ErrorData> errors = body.getErrors();
        if (null != errors && errors.size() > 0) {
            ErrorData error = body.getErrors().get(0);
            log.error("GraphQL error: {}", error.getMessage());
            throw new ApiException(error.getMessage(), error.getCode(), error.getStatus());
        }
        T response = body.getData().getResponse();
        return objectMapper.convertValue(response, classType);
    }
    
}
