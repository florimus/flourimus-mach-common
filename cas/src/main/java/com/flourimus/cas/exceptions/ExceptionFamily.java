package com.flourimus.cas.exceptions;

public interface ExceptionFamily {

    public String getMessage();

    public String getErrorCode();

    public int getStatus();
}