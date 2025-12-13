package com.springbootprojects.employeemanagement.dto;

import com.springbootprojects.employeemanagement.validation.PasswordAnnotation;
import com.springbootprojects.employeemanagement.validation.PrimeNoAnnotation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Integer id;
    @NotBlank(message = "Department Name cannot be blank")
    private String title;
    private boolean active;
    private LocalDate createdAt;
//    @PrimeNoAnnotation
//    private Integer prime;
//    @PasswordAnnotation
//    private String password;
}
