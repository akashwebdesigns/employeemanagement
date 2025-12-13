package com.springbootprojects.employeemanagement.advices;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//ResponseBodyAdvice is a Spring interface that lets you intercept and modify every controller response before it’s written
// to the HTTP response body.

//Note: Exceptions are not handled by ResponseBodyAdvice.
//ResponseBodyAdvice only runs after a controller returns normally.
//If an exception is thrown, Spring switches to the exception handling pipeline, and ResponseBodyAdvice is skipped.
//Thus, we have to wrap the ApiResponse in the exception handlers defined in GlobalExceptionHandler

@RestControllerAdvice
public class GlobalApiResponseHandler implements ResponseBodyAdvice<Object> {

    //In ResponseBodyAdvice, the supports() method decides whether your advice should be applied to a particular controller response.
    //
    //Think of it as a filter / gatekeeper.

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ApiResponse<?>){
            return body;
        }
        else{
            return new ApiResponse<>(body);
        }
    }
}
