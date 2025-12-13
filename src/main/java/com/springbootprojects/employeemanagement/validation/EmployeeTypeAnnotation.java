package com.springbootprojects.employeemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE_PARAMETER})//Where you want to use the annotation i.e. field, class, etc.
@Retention(RetentionPolicy.RUNTIME)// When you want the validation to happen
@Constraint(validatedBy = EmployeeTypeValidator.class)//What is the class where the logic of the validation is written

public @interface EmployeeTypeAnnotation {

    String message() default "Invalid employee type: Employee can be either ADMIN or USER";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
