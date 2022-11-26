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

import com.github.fa2bio.api.assembler.PermissionModelAssembler;
import com.github.fa2bio.api.model.PermissionModel;
import com.github.fa2bio.api.swaggeropenapi.controller.GroupPermissionsControllerSwagger;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.service.GroupService;

@RestController
@RequestMapping(value = "/groups/{groupId}")
public class GroupsPermissionsController implements GroupPermissionsControllerSwagger{
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@GetMapping("/permissions")
	public List<PermissionModel> list(@PathVariable Long groupId){
		Grupo group = groupService.fetchOrFail(groupId); 
		return permissionModelAssembler.toCollectionModel(group.getPermissoes());
	}
	
	@PutMapping("/permissions/{permissionId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
		groupService.associatePermission(groupId, permissionId);
	}
	
	@DeleteMapping("/permissions/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
		groupService.disassociatePermission(groupId, permissionId);
	}
}
