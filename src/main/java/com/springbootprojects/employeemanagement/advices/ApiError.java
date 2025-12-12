package com.springbootprojects.employeemanagement.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

//With builder we can prevent --> User user = new User("John", "Doe", 25, "john@example.com");
@Data
@Builder
public class ApiError {
    private HttpStatus statusCode;
    private String message;
}
