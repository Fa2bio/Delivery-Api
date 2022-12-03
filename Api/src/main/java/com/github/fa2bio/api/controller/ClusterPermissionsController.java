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
import com.github.fa2bio.api.swaggeropenapi.controller.ClusterPermissionsControllerSwagger;
import com.github.fa2bio.domain.model.Cluster;
import com.github.fa2bio.domain.service.ClusterService;

@RestController
@RequestMapping(value = "/clusters/{clusterId}")
public class ClusterPermissionsController implements ClusterPermissionsControllerSwagger{
	
	@Autowired
	private ClusterService clusterService;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@GetMapping("/permissions")
	public List<PermissionModel> list(@PathVariable Long clusterId){
		Cluster cluster = clusterService.fetchOrFail(clusterId); 
		return permissionModelAssembler.toCollectionModel(cluster.getPermissions());
	}
	
	@PutMapping("/permissions/{permissionId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associate(@PathVariable Long clusterId, @PathVariable Long permissionId) {
		clusterService.associatePermission(clusterId, permissionId);
	}
	
	@DeleteMapping("/permissions/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long clusterId, @PathVariable Long permissionId) {
		clusterService.disassociatePermission(clusterId, permissionId);
	}
}
