package com.github.fa2bio.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.github.fa2bio.api.assembler.ClusterInputDisassembler;
import com.github.fa2bio.api.assembler.ClusterModelAssembler;
import com.github.fa2bio.api.model.ClusterModel;
import com.github.fa2bio.api.model.input.ClusterInput;
import com.github.fa2bio.api.swaggeropenapi.controller.ClusterControllerSwagger;
import com.github.fa2bio.domain.model.Cluster;
import com.github.fa2bio.domain.repository.ClusterRepository;
import com.github.fa2bio.domain.service.ClusterService;

@RestController
@RequestMapping(value = "/clusters")
public class ClusterController implements ClusterControllerSwagger{

	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private ClusterService clusterService;
	
	@Autowired
	private ClusterModelAssembler clusterModelAssembler;
	
	@Autowired
	private ClusterInputDisassembler clusterInputDisassembler;
	
	@Override
	@GetMapping
	public CollectionModel<ClusterModel> list(){
		return clusterModelAssembler.toCollectionModel(clusterRepository.findAll());
	}
	
	@Override
	@GetMapping("/{clusterId}")
	public ClusterModel find(@PathVariable Long clusterId) {
		Cluster cluster = clusterService.fetchOrFail(clusterId);
		return clusterModelAssembler.toModel(cluster);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClusterModel register(@RequestBody @Valid ClusterInput clusterInput) {
		Cluster cluster = clusterInputDisassembler.toDomainObject(clusterInput);
		return clusterModelAssembler.toModel(clusterService.save(cluster));
	}
	
	@Override
	@PutMapping("/{clusterId}")
	public ClusterModel update(@PathVariable Long clusterId ,@RequestBody @Valid ClusterInput clusterInput) {
		Cluster currentGroup = clusterService.fetchOrFail(clusterId);
		clusterInputDisassembler.copyToDomainObject(clusterInput, currentGroup);
		return clusterModelAssembler.toModel(clusterService.save(currentGroup));
	}
	
	@Override
	@DeleteMapping("/{clusterId}")
	public void delete(@PathVariable Long clusterId) {
		clusterService.delete(clusterId);
	}
}
