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

import com.github.fa2bio.api.assembler.GroupInputDisassembler;
import com.github.fa2bio.api.assembler.GroupModelAssembler;
import com.github.fa2bio.api.model.GroupModel;
import com.github.fa2bio.api.model.input.GroupInput;
import com.github.fa2bio.api.swaggeropenapi.controller.GroupControllerSwagger;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.repository.GroupRepository;
import com.github.fa2bio.domain.service.GroupService;

@RestController
@RequestMapping(value = "/groups")
public class GroupController implements GroupControllerSwagger{

	@Autowired
	private GroupRepository grupoRepository;
	
	@Autowired
	private GroupService cadastroGrupoService;
	
	@Autowired
	private GroupModelAssembler grupoModelAssembler;
	
	@Autowired
	private GroupInputDisassembler grupoInputDisassembler;
	
	@Override
	@GetMapping
	public List<GroupModel> list(){
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@Override
	@GetMapping("/{groupId}")
	public GroupModel find(@PathVariable Long groupId) {
		Grupo group = cadastroGrupoService.fetchOrFail(groupId);
		return grupoModelAssembler.toModel(group);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupModel register(@RequestBody @Valid GroupInput groupInput) {
		Grupo group = grupoInputDisassembler.toDomainObject(groupInput);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(group));
	}
	
	@Override
	@PutMapping("/{groupId}")
	public GroupModel update(@PathVariable Long groupId ,@RequestBody @Valid GroupInput groupInput) {
		Grupo currentGroup = cadastroGrupoService.fetchOrFail(groupId);
		grupoInputDisassembler.copyToDomainObject(groupInput, currentGroup);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(currentGroup));
	}
	
	@Override
	@DeleteMapping("/{grupoId}")
	public void delete(@PathVariable Long groupId) {
		cadastroGrupoService.excluir(groupId);
	}
}
