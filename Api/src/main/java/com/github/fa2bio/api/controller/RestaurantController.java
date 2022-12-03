package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.github.fa2bio.api.assembler.RestaurantInputDisassembler;
import com.github.fa2bio.api.assembler.RestaurantModelAssembler;
import com.github.fa2bio.api.model.RestaurantModel;
import com.github.fa2bio.api.model.input.RestaurantInput;
import com.github.fa2bio.api.swaggeropenapi.controller.RestaurantControllerSwagger;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.exception.CityNotFoundException;
import com.github.fa2bio.domain.exception.KitchenNotFoundException;
import com.github.fa2bio.domain.exception.RestaurantNotFoundException;
import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.repository.RestaurantRepository;
import com.github.fa2bio.domain.service.RestaurantService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController implements RestaurantControllerSwagger{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantModelAssembler restaurantModelAssembler;
	
	@Autowired
	private RestaurantInputDisassembler restaurantInputDisassembler;

	@Override
	@GetMapping
	public List<RestaurantModel> list() {
		return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
	}
	
	@Override
	@GetMapping(params="projection=name-only")
	public List<RestaurantModel> listOnlyName() {
		return list();
	}
	
	@Override
	@GetMapping("/{restaurantId}")
	public RestaurantModel find(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.fetchOrFail(restaurantId);
		return restaurantModelAssembler.toModel(restaurant);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantModel register(@RequestBody @Valid RestaurantInput restaurantInput) {

		try {
			Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
			return restaurantModelAssembler.toModel(restaurantService.save(restaurant));
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	@PutMapping("/{restaurantId}")
	public RestaurantModel update(@PathVariable Long restaurantId,
			@RequestBody @Valid RestaurantInput restaurantInput) {
		try {

			Restaurant restaurantCurrent = restaurantService.fetchOrFail(restaurantId);
			
			restaurantInputDisassembler.copyToDomainObject(restaurantInput, restaurantCurrent);
			return restaurantModelAssembler.toModel(restaurantService.save(restaurantCurrent));
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	@PutMapping("/{restaurantId}/active")
	@ResponseStatus(HttpStatus.CREATED)
	public void activate(@PathVariable Long restaurantId) {
		restaurantService.activate(restaurantId);
	}
	
	@Override
	@PutMapping("/activations")
	@ResponseStatus(HttpStatus.CREATED)
	public void activateMultiples(@RequestBody List<Long> restaurantIds) {
		try {
			restaurantService.activateMultiples(restaurantIds);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	@DeleteMapping("/{restaurantId}/inactivate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inactivate(@PathVariable Long restaurantId) {
		restaurantService.inactivate(restaurantId);
	}
	
	@Override
	@DeleteMapping("/inactivations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inactivateMultiples(@RequestBody List<Long> restaurantIds) {
		try {
			restaurantService.inactivateMultiples(restaurantIds);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	@PutMapping("/{restaurantId}/open")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void open(@PathVariable Long restaurantId) {
		restaurantService.open(restaurantId);
	}
	
	@Override
	@PutMapping("/{restaurantId}/close")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long restaurantId) {
		restaurantService.close(restaurantId);
	}

}
