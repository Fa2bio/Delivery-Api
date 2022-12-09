package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.ProductInputDisassembler;
import com.github.fa2bio.api.assembler.ProductModelAssembler;
import com.github.fa2bio.api.model.ProductModel;
import com.github.fa2bio.api.model.input.ProductInput;
import com.github.fa2bio.api.swaggeropenapi.controller.RestaurantProductsControllerSwagger;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.Product;
import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.repository.ProductRepository;
import com.github.fa2bio.domain.service.ProductService;
import com.github.fa2bio.domain.service.RestaurantService;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/products")
public class RestaurantProductsController 
	implements RestaurantProductsControllerSwagger{

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;
	
	@Autowired
	private ProductInputDisassembler productInputDisassembler;
	
	@Autowired
	private DeliveryLinks deliveryLinks;

	@Override
	@GetMapping
	public CollectionModel<ProductModel> list(@PathVariable Long restaurantId, 
			@RequestParam(required = false, defaultValue = "false") Boolean includeInactive) {
		Restaurant restaurant = restaurantService.fetchOrFail(restaurantId);
		List<Product> allProducts = null;
		
		if(includeInactive) allProducts = productRepository.findAllByRestaurant(restaurant);
		else allProducts = productRepository.findActivesByRestaurant(restaurant);
		
		return productModelAssembler.toCollectionModel(allProducts)
				.add(deliveryLinks.linkToProducts(restaurantId));
	}
	
	@Override
	@GetMapping("/{productId}")
	public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
		Product product = productService.fetchOrFail(restaurantId, productId);
		return productModelAssembler.toModel(product);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel register(@PathVariable Long restaurantId, @RequestBody @Valid ProductInput productInput) {
		Restaurant restaurant = restaurantService.fetchOrFail(restaurantId);
		Product product = productInputDisassembler.toDomainObject(productInput);
		product.setRestaurant(restaurant);
		return productModelAssembler.toModel(productService.save(product));
	}
	
	@Override
	@PutMapping("/{productId}")
	public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody @Valid ProductInput productInput) {
		Product product = productService.fetchOrFail(restaurantId, productId);
		productInputDisassembler.copyToDomainObject(productInput, product);
		return productModelAssembler.toModel(productService.save(product));
	}
}
