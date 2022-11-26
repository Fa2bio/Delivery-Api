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
import com.github.fa2bio.api.swaggeropenapi.controller.UserGroupControllerSwagger;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.service.UserService;

@RestController
@RequestMapping("/users/{userId}")
public class UserGroupController implements UserGroupControllerSwagger{

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupModelAssembler groupModelAssembler;
	
	@Override
	@GetMapping("/groups")
	public List<GroupModel> list(@PathVariable Long userId){
		Usuario user = userService.fetchOrFail(userId);
		return groupModelAssembler.toCollectionModel(user.getGrupos());
	}
	
	@Override
	@PutMapping("/groups/{groupId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
		userService.associate(userId, groupId);
	}
	
	@Override
	@DeleteMapping("/groups/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
		userService.disassociate(userId, groupId);
	}
	
}
