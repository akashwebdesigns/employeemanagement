package com.springbootprojects.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

//<AnnotationName,Type on which validation will happen>
public class EmployeeTypeValidator implements ConstraintValidator<EmployeeTypeAnnotation,String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        List<String> employeeType = List.of("ADMIN","USER");
        return employeeType.contains(role);
    }
}
