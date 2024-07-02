package com.meal.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {com.meal.validation.TextValidByRegExValidator.class})
public @interface TextValidByRegEx {
	String message() default "{defaultTextValidationMessage}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	String regEx() default "";
}
