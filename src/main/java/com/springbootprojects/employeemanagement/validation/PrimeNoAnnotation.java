package com.springbootprojects.employeemanagement.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrimeNoValidator.class )
public @interface PrimeNoAnnotation {
    String message() default "Not a prime no";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
