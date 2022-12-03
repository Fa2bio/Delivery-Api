package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.StateNotFoundException;
import com.github.fa2bio.domain.model.State;
import com.github.fa2bio.domain.repository.StateRepository;

@Service
public class StateService {

	private static final String MSG_STATE_IN_USE 
		= "The state with code %d cannot be removed because it is in use";
	
	@Autowired
	private StateRepository stateRepository;
	
	@Transactional
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	@Transactional
	public void delete(Long stateId) {
		try {
			stateRepository.deleteById(stateId);
			stateRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new StateNotFoundException(stateId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_STATE_IN_USE , stateId));
		}
	}

	public State fetchOrFail(Long stateId) {
		return stateRepository.findById(stateId)
			.orElseThrow(() -> new StateNotFoundException(stateId));
	}
	
}