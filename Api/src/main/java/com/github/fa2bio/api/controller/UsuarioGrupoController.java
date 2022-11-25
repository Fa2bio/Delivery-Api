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

import com.github.fa2bio.api.assembler.GroupModelAssembler;
import com.github.fa2bio.api.model.GroupModel;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private GroupModelAssembler grupoModelAssembler;
	
	@GetMapping("/grupos")
	public List<GroupModel> listar(@PathVariable Long usuarioId){
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
	}
	
	@PutMapping("/grupos/{grupoId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.associar(usuarioId, grupoId);
	}
	
	@DeleteMapping("/grupos/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.deassociar(usuarioId, grupoId);
	}
	
}
