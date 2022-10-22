package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.FormaPagamentoModel;
import com.github.fa2bio.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}
	
	public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formaPagamentos){
		return formaPagamentos.stream()
				.map(formaPagamento -> toModel(formaPagamento))
				.collect(Collectors.toList());
	}
}
