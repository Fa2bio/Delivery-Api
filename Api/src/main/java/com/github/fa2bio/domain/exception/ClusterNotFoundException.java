package com.github.fa2bio.domain.exception;

public class ClusterNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public ClusterNotFoundException(String message) {
		super(message);
	}

	public ClusterNotFoundException(Long clusterId) {
		super(String.format("There is no cluster record with code %d", clusterId));
	}
}
