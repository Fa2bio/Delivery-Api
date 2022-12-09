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

import com.github.fa2bio.api.assembler.ClusterModelAssembler;
import com.github.fa2bio.api.model.ClusterModel;
import com.github.fa2bio.api.swaggeropenapi.controller.UserGroupControllerSwagger;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.User;
import com.github.fa2bio.domain.service.UserService;

@RestController
@RequestMapping("/users/{userId}")
public class UserClusterController implements UserGroupControllerSwagger{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClusterModelAssembler clusterModelAssembler;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	@Override
	@GetMapping("/clusters")
	public CollectionModel<ClusterModel> list(@PathVariable Long userId){
		User user = userService.fetchOrFail(userId);
		
		CollectionModel<ClusterModel> clustersModel = clusterModelAssembler
				.toCollectionModel(user.getClusters())
				.removeLinks()
				.add(deliveryLinks.linkToClustersUsersAssociate(userId, "associate"));
		
		clustersModel.getContent().forEach(clusterModel -> {
			clusterModel.add(deliveryLinks.linkToClustersUsersDisassociate(userId, clusterModel.getId(), "disassociate"));
		});
		return clustersModel;
	}
	
	@Override
	@PutMapping("/clusters/{clusterId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long clusterId) {
		userService.associate(userId, clusterId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/clusters/{clusterId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long clusterId) {
		userService.disassociate(userId, clusterId);
		return ResponseEntity.noContent().build();
	}
	
}
