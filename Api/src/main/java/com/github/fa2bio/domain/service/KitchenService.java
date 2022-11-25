package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.KitchenNaoEncontradaException;
import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.model.Cozinha;
import com.github.fa2bio.domain.repository.KitchenRepository;

@Service
public class KitchenService {

	private static final String MSG_COZINHA_EM_USO 
		= "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private KitchenRepository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNaoEncontradaException(cozinhaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}
	
	public Cozinha fetchOrFail(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new KitchenNaoEncontradaException(cozinhaId));
	}
	
}
