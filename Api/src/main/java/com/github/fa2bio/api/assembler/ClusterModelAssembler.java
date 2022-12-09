package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.ClusterController;
import com.github.fa2bio.api.model.ClusterModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Cluster;

@Component
public class ClusterModelAssembler 
	extends RepresentationModelAssemblerSupport<Cluster, ClusterModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public ClusterModelAssembler() {
		super(ClusterController.class, ClusterModel.class);
	}
	
	@Override
	public ClusterModel toModel(Cluster cluster) {
		ClusterModel clusterModel = createModelWithId(cluster.getId(), cluster);
		modelMapper.map(cluster, clusterModel);
		clusterModel.add(deliveryLinks.linkToClusters("clusters"));
		clusterModel.add(deliveryLinks.linkToClustersPermissions(cluster.getId(), "permissions"));
		
		return clusterModel;
	}
	
	@Override
	public CollectionModel<ClusterModel> toCollectionModel(Iterable<? extends Cluster> entities){
		return super.toCollectionModel(entities)
				.add(deliveryLinks.linkToClusters());
	}
}
