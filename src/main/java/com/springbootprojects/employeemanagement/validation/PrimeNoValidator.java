package com.springbootprojects.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNoValidator implements ConstraintValidator<PrimeNoAnnotation,Integer> {
    @Override
    public boolean isValid(Integer no, ConstraintValidatorContext constraintValidatorContext) {
        for(int i=2;i*i<=no;i++){
            if(no%i==0){
                return false;
            }
        }
        return true;
    }
}
