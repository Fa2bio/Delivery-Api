package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Cluster;
import com.github.fa2bio.domain.service.ClusterService;

@RestController
@RequestMapping(value = "/clusters/{clusterId}")
public class ClusterPermissionsController implements ClusterPermissionsControllerSwagger{
	
	@Autowired
	private ClusterService clusterService;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	@GetMapping("/permissions")
	public CollectionModel<PermissionModel> list(@PathVariable Long clusterId){
		Cluster cluster = clusterService.fetchOrFail(clusterId); 
		
		CollectionModel<PermissionModel> permissionsModel = permissionModelAssembler
				.toCollectionModel(cluster.getPermissions())
				.removeLinks()
				.add(deliveryLinks.linkToClustersPermissionsAssociate(clusterId, "associate"))
				.add(deliveryLinks.linkToClustersPermissions(clusterId));
		
		permissionsModel.getContent().forEach(permissionModel -> {
			permissionModel.add(deliveryLinks.linkToClustersPermissionsDisassociate(clusterId, permissionModel.getId(), "disassociate"));
		});
		return permissionsModel;
	}
	
	@PutMapping("/permissions/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long clusterId, @PathVariable Long permissionId) {
		clusterService.associatePermission(clusterId, permissionId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/permissions/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long clusterId, @PathVariable Long permissionId) {
		clusterService.disassociatePermission(clusterId, permissionId);
		return ResponseEntity.noContent().build();
	}
}
