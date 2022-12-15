package com.github.fa2bio.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.fa2bio.api.assembler.PhotoProdutoModelAssembler;
import com.github.fa2bio.api.model.PhotoProductModel;
import com.github.fa2bio.api.model.input.PhotoProductInput;
import com.github.fa2bio.api.swaggeropenapi.controller.RestaurantPhotoProductControllerSwagger;
import com.github.fa2bio.domain.exception.EntityNotFoundException;
import com.github.fa2bio.domain.model.PhotoProduct;
import com.github.fa2bio.domain.model.Product;
import com.github.fa2bio.domain.service.PhotoProductService;
import com.github.fa2bio.domain.service.PhotoStorageService;
import com.github.fa2bio.domain.service.PhotoStorageService.PhotoRecover;
import com.github.fa2bio.domain.service.ProductService;

import net.sf.jasperreports.repo.InputStreamResource;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantPhotoProductController 
	implements RestaurantPhotoProductControllerSwagger{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PhotoProductService photoProductService;
	
	@Autowired
	private PhotoProdutoModelAssembler photoProdutoModelAssembler;
	
	@Autowired
	private PhotoStorageService photoStorageService;
	
	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PhotoProductModel find(@PathVariable Long restaurantId,
			@PathVariable Long productId) {
		
		PhotoProduct photoProduct = photoProductService.fetchOrFail(restaurantId, productId);
		
		return photoProdutoModelAssembler.toModel(photoProduct);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<?> recoverPhoto(@PathVariable Long restaurantId,
			@PathVariable Long productId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException{
		
		try {
			PhotoProduct photoProduct = photoProductService.fetchOrFail(restaurantId, productId);
			MediaType mediaTypePhoto = MediaType.parseMediaType(photoProduct.getContentType());
			List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);
			verificarCompatibilidadeMediaType(mediaTypePhoto, acceptedMediaTypes);
			
//			java.io.InputStream inputStream = photoStorageService.toRecover(photoProduct.getFileName());
			PhotoRecover photoRecovered = photoStorageService.toRecover(photoProduct.getFileName());
			
//			return ResponseEntity.ok()
//					.contentType(mediaTypePhoto)
//					.body(new InputStreamResource(inputStream));
			
			if(photoRecovered.withUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, photoRecovered.getUrl())
						.build();
			}else {
				java.io.InputStream inputStream = photoRecovered.getInputStream();
				return ResponseEntity.ok()
						.contentType(mediaTypePhoto)
						.body(new InputStreamResource());
			}
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PhotoProductModel registerPhoto(@PathVariable Long restaurantId,
			@PathVariable Long productId, @Valid PhotoProductInput photoProductInput) throws IOException{
		
		Product product = productService.fetchOrFail(restaurantId, productId);
		
		MultipartFile file = photoProductInput.getFile();
		
		PhotoProduct photo = new PhotoProduct();
		photo.setProduct(product);
		photo.setDescription(photoProductInput.getDescription());
		photo.setContentType(file.getContentType());
		photo.setSize(file.getSize());
		photo.setFileName(file.getOriginalFilename());
		
		PhotoProduct photoSaved = photoProductService.save(photo, file.getInputStream());
		
		return photoProdutoModelAssembler.toModel(photoSaved);
		
	}
	
	@Override
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long restaurantId, 
			@PathVariable Long productId) {
		photoProductService.delete(restaurantId, productId);
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
			List<MediaType> acceptedMediaTypes) throws HttpMediaTypeNotAcceptableException{

		boolean compatible = acceptedMediaTypes.stream()
				.anyMatch(acceptedMediaType -> acceptedMediaType.isCompatibleWith(mediaTypeFoto));
		
		if(!compatible) throw new HttpMediaTypeNotAcceptableException(acceptedMediaTypes);
		
	}
}
