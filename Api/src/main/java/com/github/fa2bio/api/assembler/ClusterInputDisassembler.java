package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.ClusterInput;
import com.github.fa2bio.domain.model.Cluster;

@Component
public class ClusterInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Cluster toDomainObject(ClusterInput clusterInput) {
		return modelMapper.map(clusterInput, Cluster.class);
	}
	
	public void copyToDomainObject(ClusterInput clusterInput, Cluster cluster) {
		modelMapper.map(clusterInput, cluster);
	}
}
