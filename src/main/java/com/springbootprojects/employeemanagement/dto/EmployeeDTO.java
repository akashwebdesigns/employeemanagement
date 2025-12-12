package com.springbootprojects.employeemanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootprojects.employeemanagement.validation.EmployeeTypeAnnotation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 10, message = "Length of name must be between 2-10")
    private String name;

    @Email(message = "Please enter a valid email")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull
    @Min(value = 18, message = "Employee's age cannot be less than 18")
    @Max(value = 60, message = "Employee's age cannot be greater than 60")
    private Integer age;

    @PastOrPresent(message = "Date of joining cannot be in future")
    private LocalDate dateOfJoining;

    @Pattern(regexp = "^(Gurugram|Noida|Bangalore|Hyderabad)$")
    private String baseLocation;

    @NotNull
    @EmployeeTypeAnnotation
    private String role;

    @NotNull
    private boolean isActive;

//    In Java, when you define a boolean field like:
//
//    private boolean isActive;
//
//    The JavaBean getter becomes:
//
//    public boolean isActive() { ... }
//    According to JavaBean conventions, a boolean getter starting with isXyz() means the property name is xyz, not isXyz.
//
//    So Jackson treats isActive() as a getter for the field active, not isActive.
//
//    Therefore, your JSON becomes:
//
//    {
//        "active": true
//    }

}
