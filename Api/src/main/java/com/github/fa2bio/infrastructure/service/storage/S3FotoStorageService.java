package com.github.fa2bio.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.github.fa2bio.domain.service.PhotoStorageService;

@Service
public class S3FotoStorageService implements PhotoStorageService{

	@Override
	public InputStream toRecover(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(NewPhoto newPhoto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toRemove(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
