package com.flourimus.cas.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;

@Component
public class CustomGraphQLErrorHandler extends DataFetcherExceptionResolverAdapter {

    /**
     * Given a {@link Throwable} that is the cause of a {@link DataFetcherException},
     * this method will return a {@link GraphQLError} that should be used to
     * represent the error to the user.
     *
     * @param ex the {@link Throwable} that is the cause of the error
     * @param env the {@link DataFetchingEnvironment} that was present when the
     * error occurred
     * @return a {@link GraphQLError} that should be used to represent the error
     * to the user
     */
    @Override
    protected GraphQLError resolveToSingleError(final Throwable ex, final DataFetchingEnvironment env) {
        
        Map<String, Object> customError = new HashMap<>();

        if (ex instanceof ExceptionFamily) {
            ExceptionFamily exception  = ExceptionFamily.class.cast(ex);
            customError.put("message", exception.getMessage());
            customError.put("status", exception.getStatus());
            customError.put("code", exception.getErrorCode());
        }

        if(ex instanceof ConstraintViolationException){
            ConstraintViolationException exception  = ConstraintViolationException.class.cast(ex);
            customError.put("message", exception.getMessage());
            customError.put("status", HttpStatus.SC_BAD_REQUEST);
            customError.put("code", "INVALID_INPUT");
        }


        return new GraphQLError() {
            @Override
            public String getMessage() {
                return ex.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return List.of();
            }

            @Override
            public ErrorType getErrorType() {
                return ErrorType.DataFetchingException;
            }

            @Override
            public Map<String, Object> toSpecification() {
                return customError;
            }
        };
    }
}
