package com.proofit.calculator.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e, HttpServletRequest request) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.systemDefault()),
                request.getRequestURL().toString(),
                badRequest.toString()
        );

        return new ResponseEntity<>(apiException, badRequest);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Set<String> errors = getAllUniqueValidationErrors(ex);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("http status", status.value());
        body.put("timestamp", ZonedDateTime.now(ZoneId.systemDefault()));
        body.put("request url", ((ServletWebRequest)request).getRequest().getRequestURI());
        body.put("validation errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

     Set<String> getAllUniqueValidationErrors(MethodArgumentNotValidException ex){
         return ex.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .map(DefaultMessageSourceResolvable::getDefaultMessage)
                 .collect(Collectors.toSet());
     }



}
