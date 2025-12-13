package com.springbootprojects.employeemanagement.advices;

import com.springbootprojects.employeemanagement.customExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


//All the exceptions from the code will be captured here
@RestControllerAdvice
public class GlobalExceptionHandler {


    //Will catch only ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundHandler(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder().
                statusCode(HttpStatus.NOT_FOUND).
                message(exception.getMessage()).
                build();
        return apiResponseWrapper(apiError);
    }

    public ResponseEntity<ApiResponse<?>> apiResponseWrapper(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatusCode());
    }

    //Will catch all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerErrorHandler(Exception exception){
        ApiError apiError = ApiError.builder().
                statusCode(HttpStatus.INTERNAL_SERVER_ERROR).
                message(exception.getMessage()).
                build();
        return apiResponseWrapper(apiError);
    }

    //Validation annotation exception
    //MethodArgumentNotValidException is thrown by the @Valid validation which we used in DTO
    //• You can get a list of all the errors from the bindingResult of this
    //exception.
    //• You can use this list to return some useful error messages as the
    //API response.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> inputValidationExceptionHandler(MethodArgumentNotValidException exception){
        //This type of exception binds all the exceptions into a single exception
        List<String> errors = exception.getBindingResult().
                getAllErrors().
                stream().
                map((error)->error.getDefaultMessage()).collect(Collectors.toList());

        ApiError apiError = ApiError.builder().
                statusCode(HttpStatus.BAD_REQUEST).
                message(errors.toString()).
                build();
        return apiResponseWrapper(apiError);
    }

}
