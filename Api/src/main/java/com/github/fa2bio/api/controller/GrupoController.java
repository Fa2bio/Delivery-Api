package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.GrupoInputDisassembler;
import com.github.fa2bio.api.assembler.GrupoModelAssembler;
import com.github.fa2bio.api.model.GrupoModel;
import com.github.fa2bio.api.model.input.GrupoInput;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.repository.GrupoRepository;
import com.github.fa2bio.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@GetMapping
	public List<GrupoModel> listar(){
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscarPorId(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		return grupoModelAssembler.toModel(grupo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId ,@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupoAtual));
	}
	
	@DeleteMapping("/{grupoId}")
	public void excluir(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
