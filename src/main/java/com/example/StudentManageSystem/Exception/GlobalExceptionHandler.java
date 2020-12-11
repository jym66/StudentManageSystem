package com.example.StudentManageSystem.Exception;

//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.StudentManageSystem.pojo.Response;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public Response handleBindException(BindException e) {
        return Response.fail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstraintViolationException(ConstraintViolationException e){
        String [] result = e.getMessage().split(":");
        return Response.fail(result[result.length -1].trim());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return Response.fail(405,"请求无效");
    }
    @ExceptionHandler(RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e){
        return Response.fail(e.getMessage());
    }
}
