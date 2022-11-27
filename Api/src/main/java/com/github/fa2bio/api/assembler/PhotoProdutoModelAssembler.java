package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.PhotoProductModel;
import com.github.fa2bio.domain.model.FotoProduto;

@Component
public class PhotoProdutoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PhotoProductModel toModel(FotoProduto foto) {
		return modelMapper.map(foto, PhotoProductModel.class);
	}
}
