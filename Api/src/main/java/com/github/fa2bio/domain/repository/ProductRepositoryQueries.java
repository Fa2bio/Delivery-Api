package com.github.fa2bio.domain.repository;

import com.github.fa2bio.domain.model.PhotoProduct;

public interface ProductRepositoryQueries {
	PhotoProduct save(PhotoProduct photo);
	void delete(PhotoProduct photo);
}
