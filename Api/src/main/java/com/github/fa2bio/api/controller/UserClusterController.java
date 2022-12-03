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

import com.github.fa2bio.api.assembler.ClusterModelAssembler;
import com.github.fa2bio.api.model.ClusterModel;
import com.github.fa2bio.api.swaggeropenapi.controller.UserGroupControllerSwagger;
import com.github.fa2bio.domain.model.User;
import com.github.fa2bio.domain.service.UserService;

@RestController
@RequestMapping("/users/{userId}")
public class UserClusterController implements UserGroupControllerSwagger{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClusterModelAssembler clusterModelAssembler;
	
	@Override
	@GetMapping("/clusters")
	public List<ClusterModel> list(@PathVariable Long userId){
		User user = userService.fetchOrFail(userId);
		return clusterModelAssembler.toCollectionModel(user.getClusters());
	}
	
	@Override
	@PutMapping("/clusters/{clusterId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associate(@PathVariable Long userId, @PathVariable Long clusterId) {
		userService.associate(userId, clusterId);
	}
	
	@Override
	@DeleteMapping("/clusters/{clusterId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long userId, @PathVariable Long clusterId) {
		userService.disassociate(userId, clusterId);
	}
	
}
