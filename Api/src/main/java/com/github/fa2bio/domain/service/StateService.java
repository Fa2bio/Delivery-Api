package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.StateNotFoundException;
import com.github.fa2bio.domain.model.Estado;
import com.github.fa2bio.domain.repository.StateRepository;

@Service
public class StateService {

	private static final String MSG_ESTADO_EM_USO 
		= "Estado de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private StateRepository estadoRepository;
	
	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new StateNotFoundException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado fetchOrFail(Long estadoId) {
		return estadoRepository.findById(estadoId)
			.orElseThrow(() -> new StateNotFoundException(estadoId));
	}
	
}