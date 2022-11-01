package com.github.fa2bio.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.fa2bio.api.model.EnderecoModel;
import com.github.fa2bio.api.model.input.ItemPedidoInput;
import com.github.fa2bio.domain.model.Endereco;
import com.github.fa2bio.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();		 
		
		var skipItemPedidoId = modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class);
		skipItemPedidoId.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);
		
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		
		return modelMapper;
	}
}

