package com.github.fa2bio.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValueZeroIncludesDescriptionValidator.class })
public @interface ValueZeroIncludesDescription {

	String message() default "invalid mandatory description";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String valueField();
	
	String descriptionField();
	
	String descriptionMandatory();
	
}
