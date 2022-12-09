package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.RestaurantProductsController;
import com.github.fa2bio.api.model.ProductModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Product;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public ProductModelAssembler() {
		super(RestaurantProductsController.class, ProductModel.class);
	}
	
	@Override
	public ProductModel toModel(Product product) {
		ProductModel productModel = createModelWithId(product.getId(),product, product.getRestaurant().getId());
		modelMapper.map(product, productModel);
		productModel.add(deliveryLinks.linkToProducts(product.getId(), "products"));
		productModel.add(deliveryLinks.linkToPhotoProduct(product.getRestaurant().getId(), product.getId(), "photo"));
		return productModel;
	}
}
