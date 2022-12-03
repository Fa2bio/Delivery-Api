package com.github.fa2bio.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.fa2bio.api.model.AddressModel;
import com.github.fa2bio.api.model.input.ItemOrderrInput;
import com.github.fa2bio.domain.model.Address;
import com.github.fa2bio.domain.model.ItemOrderr;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();		 
		
		var skipItemPedidoId = modelMapper.createTypeMap(ItemOrderrInput.class, ItemOrderr.class);
		skipItemPedidoId.addMappings(mapper -> mapper.skip(ItemOrderr::setId));
		
		var addressToAddressModelTypeMap = modelMapper.createTypeMap(
				Address.class, AddressModel.class);
		
		addressToAddressModelTypeMap.<String>addMapping(
				addressSrc -> addressSrc.getCity().getState().getName(),
				(addressModelDest, value) -> addressModelDest.getCity().setState(value));
		
		return modelMapper;
	}
}

