package com.meal.validation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {com.meal.validation.NotNullFileValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface NotNullFile {
	String message() default "{defaultNotNullFileValidationMessage}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    

}
