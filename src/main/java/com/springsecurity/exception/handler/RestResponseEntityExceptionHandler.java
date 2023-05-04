package com.springsecurity.exception.handler;



import com.springsecurity.exception.GeneralException;
import com.springsecurity.exception.UserAlreadyExistsException;
import com.springsecurity.util.HttpResponseBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception exception) {

        if(exception instanceof BadCredentialsException)
        {
            return ResponseEntity.badRequest().body(new HttpResponseBuilder().message("Invalid username or password.").build());
        }
        else if(exception instanceof UsernameNotFoundException)
        {
            return ResponseEntity.badRequest().body(new HttpResponseBuilder().message("Invalid username or password.").build());
        }
        else if(exception instanceof UserAlreadyExistsException)
        {
            return ResponseEntity.badRequest().body(new HttpResponseBuilder().message("User already Exits.").build());
        }
        else if(exception instanceof InvalidBearerTokenException)
        {
            return ResponseEntity.badRequest().body(new HttpResponseBuilder().message("Invalid token.").build());
        }
        return ResponseEntity.badRequest().body(new HttpResponseBuilder().message("Internal error").build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();

        String error = result.getAllErrors().stream().map(e -> {
            if (e instanceof FieldError) {
                return ((FieldError) e).getField() + e.getDefaultMessage();
            } else {
                return e.getObjectName() + e.getDefaultMessage();
            }
        }).collect(Collectors.joining(", "));
        return handleExceptionInternal(ex, new HttpResponseBuilder().message("failed").data(error).build(),headers,HttpStatus.BAD_REQUEST, request);
    }








}
