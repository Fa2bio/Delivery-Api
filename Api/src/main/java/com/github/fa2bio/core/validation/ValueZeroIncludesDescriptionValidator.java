package com.github.fa2bio.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValueZeroIncludesDescriptionValidator implements ConstraintValidator<ValueZeroIncludesDescription, Object> {

	private String valueField;
	private String descriptionField;
	private String descriptionMandatory;
	
	@Override
	public void initialize(ValueZeroIncludesDescription constraint) {
		this.valueField = constraint.valueField();
		this.descriptionField = constraint.descriptionField();
		this.descriptionMandatory = constraint.descriptionMandatory();
	}
	
	@Override
	public boolean isValid(Object objectValidation, ConstraintValidatorContext context) {
		boolean valid = true;
		
		try {
			BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectValidation.getClass(), valueField)
					.getReadMethod().invoke(objectValidation);
			
			String description = (String) BeanUtils.getPropertyDescriptor(objectValidation.getClass(), descriptionField)
					.getReadMethod().invoke(objectValidation);
			
			if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null) {
				valid = description.toLowerCase().contains(this.descriptionMandatory.toLowerCase());
			}
			
			return valid;
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}

}
