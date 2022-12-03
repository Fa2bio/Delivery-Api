package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.ClusterModel;
import com.github.fa2bio.domain.model.Cluster;

@Component
public class ClusterModelAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public ClusterModel toModel(Cluster cluster) {
		return modelMapper.map(cluster, ClusterModel.class);
	}
	
	public List<ClusterModel> toCollectionModel(Collection<Cluster> clusters){
		return clusters.stream()
				.map(cluster -> toModel(cluster))
				.collect(Collectors.toList());
	}
}
