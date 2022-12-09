package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.RestaurantPhotoProductController;
import com.github.fa2bio.api.model.PhotoProductModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.PhotoProduct;

@Component
public class PhotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<PhotoProduct, PhotoProductModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public PhotoProdutoModelAssembler() {
		super(RestaurantPhotoProductController.class, PhotoProductModel.class);
	}
	
	@Override
	public PhotoProductModel toModel(PhotoProduct photo) {
		PhotoProductModel photoProductModel = modelMapper.map(photo, PhotoProductModel.class);
		
		photoProductModel.add(deliveryLinks.linkToPhotoProduct(photo.getRestaurantId(), photo.getProduct().getId()));
		
		return photoProductModel;
	}
}
