package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.CityInputDisassembler;
import com.github.fa2bio.api.assembler.CityModelAssembler;
import com.github.fa2bio.api.model.CityModel;
import com.github.fa2bio.api.model.input.CityInput;
import com.github.fa2bio.api.swaggeropenapi.controller.CityControllerSwagger;
import com.github.fa2bio.core.hypermedia.ResourceUriHelper;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.exception.StateNotFoundException;
import com.github.fa2bio.domain.model.City;
import com.github.fa2bio.domain.repository.CityRepository;
import com.github.fa2bio.domain.service.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController implements CityControllerSwagger{

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CityModelAssembler cityModelAssembler;
	
	@Autowired
	private CityInputDisassembler cityInputDisassembler;
	
	@Override
	@GetMapping
	public CollectionModel<CityModel> list() {
		List<City> allCities = cityRepository.findAll();
		return cityModelAssembler.toCollectionModel(allCities);
	}
	
	@Override
	@GetMapping("/{cityId}")
	public CityModel find(@PathVariable Long cityId) {
		City city = cityService.fetchOrFail(cityId);
		return cityModelAssembler.toModel(city);
	}


	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityModel register(@RequestBody @Valid CityInput cityInput) {
		try {
			City city = cityInputDisassembler.toDomainObject(cityInput);
			city = cityService.save(city);
			CityModel cityModel = cityModelAssembler.toModel(city);
			
			ResourceUriHelper.addUriInResponseHeader(cityModel.getId());
			return cityModel;
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	@PutMapping("/{cityId}")
	public CityModel update(@PathVariable Long cityId,
			@RequestBody @Valid CityInput cityInput) {
		try {
			City currentCity = cityService.fetchOrFail(cityId);
			
			cityInputDisassembler.copyToDomainObject(cityInput, currentCity);
			
			return cityModelAssembler.toModel(cityService.save(currentCity));
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId) {
		cityService.delete(cityId);	
	}
	
}
