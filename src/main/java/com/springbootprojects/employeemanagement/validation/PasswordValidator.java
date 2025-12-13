package com.springbootprojects.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordAnnotation,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean hasUpper=false,haslower=false,hasSpecial=false;
        if(password.length()<10) return false;

        for(char ch : password.toCharArray()){
            if(Character.isUpperCase(ch)) hasUpper=true;
            else if(Character.isLowerCase(ch)) haslower = true;
            else if(!Character.isDigit(ch)) hasSpecial = true;
        }

        return haslower&&hasUpper&&hasSpecial;
    }
}
