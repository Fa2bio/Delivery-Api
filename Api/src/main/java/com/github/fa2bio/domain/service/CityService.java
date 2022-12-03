package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.CityNotFoundException;
import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.model.City;
import com.github.fa2bio.domain.model.State;
import com.github.fa2bio.domain.repository.CityRepository;

@Service
public class CityService {

	private static final String MSG_CITY_IN_USE 
		= "The city with code %d cannot be removed because it is in use";

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateService stateService;
	
	@Transactional
	public City save(City city) {
		Long stateId = city.getState().getId();

		State state = stateService.fetchOrFail(stateId);
		
		city.setState(state);
		
		return cityRepository.save(city);
	}
	
	@Transactional
	public void delete(Long cityId) {
		try {
			cityRepository.deleteById(cityId);
			cityRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(cityId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_CITY_IN_USE, cityId));
		}
	}
	
	public City fetchOrFail(Long cityId) {
		return cityRepository.findById(cityId)
			.orElseThrow(() -> new CityNotFoundException(cityId));
	}
	
}
