package com.proofit.calculator.exception;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private final String requestUrl;
    private final String errorCode;

    public ApiException(String message,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp,
                        String requestUrl,
                        String errorCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.requestUrl = requestUrl;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
