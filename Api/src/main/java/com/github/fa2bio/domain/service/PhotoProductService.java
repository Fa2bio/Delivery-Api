package com.github.fa2bio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.PhotoProductNotFoundException;
import com.github.fa2bio.domain.model.PhotoProduct;
import com.github.fa2bio.domain.repository.ProductRepository;
import com.github.fa2bio.domain.service.PhotoStorageService.NewPhoto;

@Service
public class PhotoProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PhotoStorageService photoStorageService;
	
	@Transactional
	public PhotoProduct save(PhotoProduct photo, java.io.InputStream dataFile) {
		Long restaurantId = photo.getRestaurantId();
		Long productId = photo.getProduct().getId();
		String newFileName = photoStorageService.generateFileName(photo.getFileName());
		String existingFileName = null;
		
		Optional<PhotoProduct> photoExisting = productRepository
				.findPhotoById(restaurantId, productId);
		
		if (photoExisting.isPresent()) {
			existingFileName = photoExisting.get().getFileName();
			productRepository.delete(photoExisting.get());
		}
		
		photo.setFileName(newFileName);
		photo = productRepository.save(photo);
		productRepository.flush();
		
		NewPhoto newPhoto = NewPhoto.builder()
				.fileName(photo.getFileName())
				.inputStream(dataFile)
				.build();
		
		photoStorageService.toReplace(existingFileName, newPhoto);
		
		return photo;
	}
	
	@Transactional
	public void delete(Long restaurantId, Long productId) {
		PhotoProduct foto = fetchOrFail(restaurantId, productId);
		
		productRepository.delete(foto);
		productRepository.flush();

		photoStorageService.toRemove(foto.getFileName());
	}
	
	public PhotoProduct fetchOrFail(Long restaurantId,Long productId) {
		return productRepository.findPhotoById(restaurantId, productId)
				.orElseThrow(() -> new PhotoProductNotFoundException(restaurantId, productId));
	}
	
}
