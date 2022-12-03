package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.PermissionNotFoundException;
import com.github.fa2bio.domain.model.Permission;
import com.github.fa2bio.domain.repository.PermissionRepository;

@Service
public class PermissionService {
	private static final String MSG_PERMISSION_IN_USE 
	= "The permission with code %d cannot be removed because it is in use";

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Transactional
	public Permission save (Permission permission) {
		return permissionRepository.save(permission);
	}
	
	@Transactional
	public void excluir (Long id) {
		try {
			permissionRepository.deleteById(id);
			permissionRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PermissionNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_PERMISSION_IN_USE , id));
		}
	}
	
	public Permission fetchOrFail(Long permissionId) {
		return permissionRepository.findById(permissionId)
			.orElseThrow(() -> new PermissionNotFoundException(permissionId));
	}
}
