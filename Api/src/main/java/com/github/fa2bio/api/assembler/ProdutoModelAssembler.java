package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.ProdutoModel;
import com.github.fa2bio.domain.model.Produto;

@Component
public class ProdutoModelAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoModel toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);
	}
	
	public List<ProdutoModel> toCollectionModel(Collection<Produto> produtos){
		return produtos.stream()
				.map(produto -> toModel(produto))
				.collect(Collectors.toList());
	}
}
