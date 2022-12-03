package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.PaymentMethodInput;
import com.github.fa2bio.domain.model.PaymentMethod;

@Component
public class PaymentMethodInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput) {
		return modelMapper.map(paymentMethodInput, PaymentMethod.class);
	}
	
	public void copyToDomainObject(PaymentMethodInput paymentMethodInput, PaymentMethod paymentMethod) {
		modelMapper.map(paymentMethodInput, paymentMethod);
	}
}
