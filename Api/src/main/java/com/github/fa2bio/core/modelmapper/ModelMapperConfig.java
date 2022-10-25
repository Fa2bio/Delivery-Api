package com.github.fa2bio.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.github.fa2bio.api.model.UsuarioModel;
//import com.github.fa2bio.domain.model.Usuario;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
//		var usuarioToUsuarioModelTypeMap = modelMapper
//			.createTypeMap(Usuario.class, UsuarioModel.class);
		return modelMapper;
	}
}
