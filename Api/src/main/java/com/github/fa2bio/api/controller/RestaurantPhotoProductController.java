package com.github.fa2bio.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import com.github.fa2bio.domain.model.FotoProduto;
import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.service.PhotoProductService;
import com.github.fa2bio.domain.service.PhotoStorageService;
import com.github.fa2bio.domain.service.ProductService;

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
		
		FotoProduto photoProduct = photoProductService.fetchOrFail(restaurantId, productId);
		
		return photoProdutoModelAssembler.toModel(photoProduct);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<InputStreamResource> recoverPhoto(@PathVariable Long restaurantId,
			@PathVariable Long productId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException{
		
		try {
			FotoProduto photoProduct = photoProductService.fetchOrFail(restaurantId, productId);
			MediaType mediaTypePhoto = MediaType.parseMediaType(photoProduct.getContentType());
			List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);
			verificarCompatibilidadeMediaType(mediaTypePhoto, acceptedMediaTypes);
			
			java.io.InputStream inputStream = photoStorageService.toRecover(photoProduct.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(mediaTypePhoto)
					.body(new InputStreamResource(inputStream));
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PhotoProductModel registerPhoto(@PathVariable Long restaurantId,
			@PathVariable Long productId, @Valid PhotoProductInput photoProductInput) throws IOException{
		
		Produto product = productService.fetchOrFail(restaurantId, productId);
		
		MultipartFile file = photoProductInput.getArquivo();
		
		FotoProduto photo = new FotoProduto();
		photo.setProduto(product);
		photo.setDescricao(photoProductInput.getDescricao());
		photo.setContentType(file.getContentType());
		photo.setTamanho(file.getSize());
		photo.setNomeArquivo(file.getOriginalFilename());
		
		FotoProduto photoSaved = photoProductService.salvar(photo, file.getInputStream());
		
		return photoProdutoModelAssembler.toModel(photoSaved);
		
	}
	
	@Override
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long restaurantId, 
			@PathVariable Long productId) {
		photoProductService.excluir(restaurantId, productId);
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
			List<MediaType> acceptedMediaTypes) throws HttpMediaTypeNotAcceptableException{

		boolean compatible = acceptedMediaTypes.stream()
				.anyMatch(acceptedMediaType -> acceptedMediaType.isCompatibleWith(mediaTypeFoto));
		
		if(!compatible) throw new HttpMediaTypeNotAcceptableException(acceptedMediaTypes);
		
	}
}
