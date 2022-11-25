package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.PaymentMethodInput;
import com.github.fa2bio.domain.model.FormaPagamento;

@Component
public class PaymentMethodInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(PaymentMethodInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}
	
	public void copyToDomainObject(PaymentMethodInput formaPagamentoInput, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoInput, formaPagamento);
	}
}
