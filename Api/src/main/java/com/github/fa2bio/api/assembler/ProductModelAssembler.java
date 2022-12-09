package com.github.fa2bio.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.ProductModel;
import com.github.fa2bio.domain.model.Product;

@Component
public class ProductModelAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductModel toModel(Product product) {
		return modelMapper.map(product, ProductModel.class);
	}
	
	public List<ProductModel> toCollectionModel(Collection<Product> products){
		return products.stream()
				.map(product -> toModel(product))
				.collect(Collectors.toList());
	}
}
