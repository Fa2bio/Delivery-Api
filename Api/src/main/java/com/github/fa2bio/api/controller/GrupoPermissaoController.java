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

import com.github.fa2bio.api.assembler.PermissaoModelAssembler;
import com.github.fa2bio.api.model.PermissaoModel;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}")
public class GrupoPermissaoController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping("/permissoes")
	public List<PermissaoModel> listar(@PathVariable Long grupoId){
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId); 
		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	@PutMapping("/permissoes/{permissaoId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao(grupoId, permissaoId);
	}
	
	@DeleteMapping("/permissoes/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
	}
}
