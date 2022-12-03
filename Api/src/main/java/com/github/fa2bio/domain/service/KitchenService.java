package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.KitchenNotFoundException;
import com.github.fa2bio.domain.model.Kitchen;
import com.github.fa2bio.domain.repository.KitchenRepository;

@Service
public class KitchenService {

	private static final String MSG_KITCHEN_IN_USE  
		= "The kitchen with code %d cannot be removed because it is in use";

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	@Transactional
	public void delete(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
			kitchenRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNotFoundException(kitchenId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_KITCHEN_IN_USE, kitchenId));
		}
	}
	
	public Kitchen fetchOrFail(Long kitchenId) {
		return kitchenRepository.findById(kitchenId)
			.orElseThrow(() -> new KitchenNotFoundException(kitchenId));
	}
	
}
