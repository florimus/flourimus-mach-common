package com.flourimus.users.exceptions;


public class NotFoundException extends RuntimeException implements ExceptionFamily {

    private final String message;
    private final String errorCode;
    private final int status;

    public NotFoundException(String message, String errorCode, int status) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }

}
