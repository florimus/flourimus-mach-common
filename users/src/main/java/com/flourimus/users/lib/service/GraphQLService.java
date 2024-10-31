package com.flourimus.users.lib.service;

import org.apache.http.HttpStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flourimus.users.common.constants.CustomerErrorCodes;
import com.flourimus.users.exceptions.ApiException;
import com.flourimus.users.lib.config.GraphQLRequest;
import com.flourimus.users.lib.dto.GraphqlResponse;
import com.flourimus.users.lib.dto.GraphqlResponse.ErrorData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GraphQLService {

    private final RestTemplate restTemplate;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * Sends a GraphQL query to the specified URL and returns the response as an
     * object of type {@code classType}.
     *
     * @param targetUrl the URL of the GraphQL API
     * @param query     the GraphQL query to be sent
     * @param variables the variables to be passed with the GraphQL query (nullable)
     * @param classType the class type of the response object
     * @return the response object of type {@code classType}
     * @throws ApiException if the GraphQL API returns any errors
     */
    public <T> T callGraphQLApi(final String targetUrl, final String query, final Map<String, Object> variables,
            final Class<T> classType) {
        GraphQLRequest request = new GraphQLRequest(query, variables);
        HttpHeaders headers = createHeaders();
        HttpEntity<GraphQLRequest> entity = new HttpEntity<>(request, headers);

        logRequestDetails(targetUrl, headers, request);
        ResponseEntity<GraphqlResponse<T>> responseEntity = sendGraphQLRequest(targetUrl, entity, classType);

        return parseResponse(responseEntity, classType);
    }

    /**
     * Creates the default HTTP headers for GraphQL requests.
     *
     * @return HttpHeaders with Content-Type set to application/json.
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", CONTENT_TYPE_JSON);
        return headers;
    }

    /**
     * Logs request details for debugging.
     *
     * @param targetUrl the URL of the GraphQL API
     * @param headers   the HTTP headers being sent
     * @param request   the GraphQL request body
     */
    private void logRequestDetails(String targetUrl, HttpHeaders headers, GraphQLRequest request) {
        log.debug("Sending HTTP request to URL: {}", targetUrl);
        log.debug("Request Headers: {}", headers);
        log.debug("Request Body: {}", request);
    }

    /**
     * Sends the GraphQL HTTP request and returns the raw response.
     *
     * @param targetUrl the URL of the GraphQL API
     * @param entity    the HTTP entity containing the request
     * @param classType the class type of the response object
     * @return ResponseEntity containing the GraphQL response
     */
    private <T> ResponseEntity<GraphqlResponse<T>> sendGraphQLRequest(String targetUrl,
            HttpEntity<GraphQLRequest> entity, Class<T> classType) {
        ParameterizedTypeReference<GraphqlResponse<T>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(targetUrl, HttpMethod.POST, entity, responseType);
    }

    /**
     * Parses the GraphQL response and returns the response object of type
     * {@code classType}.
     * 
     * @param responseEntity the ResponseEntity containing the GraphQL response
     * @param classType      the class type of the response object
     * @return the response object of type {@code classType}
     * @throws ApiException if the GraphQL response body is missing or if errors are
     *                      present in the response
     */
    private <T> T parseResponse(ResponseEntity<GraphqlResponse<T>> responseEntity, Class<T> classType) {
        GraphqlResponse<T> body = responseEntity.getBody();
        if (body == null) {
            throw new ApiException("GraphQL response body is missing", CustomerErrorCodes.NO_RESPONSE_BODY,
                    HttpStatus.SC_METHOD_FAILURE);
        }
        handleErrors(body.getErrors());
        return objectMapper.convertValue(body.getData().getResponse(), classType);
    }

    /**
     * Handles GraphQL errors by throwing an ApiException if errors are present.
     *
     * @param errors a list of GraphQL error data
     * @throws ApiException if errors are present in the list
     */
    private void handleErrors(List<ErrorData> errors) {
        if (errors != null && !errors.isEmpty()) {
            ErrorData error = errors.get(0);
            log.error("GraphQL error: {}", error.getMessage());

            String message = "";
            String code = "";
            Integer status = HttpStatus.SC_EXPECTATION_FAILED;

            if (error.getMessage() != null) {
                message = error.getMessage();
            }

            if (error.getCode() != null) {
                code = error.getCode();
            }

            if (error.getStatus() != null) {
                status = error.getStatus();
            }

            throw new ApiException(message, code, status);
        }
    }
}
