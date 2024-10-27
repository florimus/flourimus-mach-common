package com.flourimus.users.exceptions;

public interface ExceptionFamily {

    public String getMessage();

    public String getErrorCode();

    public int getStatus();
}