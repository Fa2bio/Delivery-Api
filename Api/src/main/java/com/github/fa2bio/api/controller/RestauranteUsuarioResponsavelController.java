package com.github.fa2bio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.UserModelAssembler;
import com.github.fa2bio.api.model.UserModel;
import com.github.fa2bio.domain.model.Restaurante;
import com.github.fa2bio.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}")
public class RestauranteUsuarioResponsavelController {

	@Autowired
	private RestaurantService cadastroRestauranteService;
	
	@Autowired
	private UserModelAssembler usuarioModelAssembler;
	
	@GetMapping("/responsaveis")
	public List<UserModel> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());
	}
	
	@PutMapping("/responsaveis/{usuarioId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);
	}
	
	@DeleteMapping("/responsaveis/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);
	}
}
