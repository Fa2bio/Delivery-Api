package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.PermissionNotFoundException;
import com.github.fa2bio.domain.model.Permissao;
import com.github.fa2bio.domain.repository.PermissaoRepository;

@Service
public class PermissionService {
	private static final String MSG_PERMISSAO_EM_USO 
	= "Permissão de código %d não pode ser removido, pois está em uso";

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Transactional
	public Permissao salvar (Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	@Transactional
	public void excluir (Long id) {
		try {
			permissaoRepository.deleteById(id);
			permissaoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PermissionNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_PERMISSAO_EM_USO, id));
		}
	}
	
	public Permissao fetchOrFail(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
			.orElseThrow(() -> new PermissionNotFoundException(permissaoId));
	}
}
