package com.github.fa2bio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.RestaurantNotFoundException;
import com.github.fa2bio.domain.model.City;
import com.github.fa2bio.domain.model.Kitchen;
import com.github.fa2bio.domain.model.PaymentMethod;
import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.model.User;
import com.github.fa2bio.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restauranteRepository;
	
	@Autowired
	private KitchenService kitchenService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private PaymentMethodsService paymentMethodsService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		Long cozinhaId = restaurant.getKitchen().getId();
		Long cidadeId = restaurant.getAddress().getCity().getId();
		
		Kitchen kitchen = kitchenService.fetchOrFail(cozinhaId);
		City city = cityService.fetchOrFail(cidadeId);
		
		restaurant.setKitchen(kitchen);
		restaurant.getAddress().setCity(city);
		
		return restauranteRepository.save(restaurant);
	}
	
	@Transactional
	public void activate (Long restaurantId) {
		Restaurant restauranteAtual = fetchOrFail(restaurantId);
		restauranteAtual.activate();
	}
	
	@Transactional
	public void activateMultiples(List<Long> restaurantId) {
		for (Long long1 : restaurantId) {
			this.activate(long1);	
		}
	}
	
	@Transactional
	public void inactivate (Long restaurantId) {
		Restaurant restauranteAtual = fetchOrFail(restaurantId);
		restauranteAtual.inactivate();
	}
	
	@Transactional
	public void inactivateMultiples(List<Long> restaurantIds) {
		restaurantIds.forEach(this::inactivate);
	}
	
	@Transactional
	public void open(Long restaurantId) {
		Restaurant restaurant = fetchOrFail(restaurantId);
		restaurant.open();
	}
	
	@Transactional
	public void close(Long restaurantId) {
		Restaurant restaurant = fetchOrFail(restaurantId);
		restaurant.close();
	}
	
	public Restaurant fetchOrFail(Long restaurantId) {
		return restauranteRepository.findById(restaurantId)
			.orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
	}
	
	@Transactional
	public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
		Restaurant restaurant = fetchOrFail(restaurantId);
		PaymentMethod paymentMethod = paymentMethodsService.fetchOrFail(paymentMethodId);
		
		restaurant.addPaymentMethod(paymentMethod);
	
	}
	
	@Transactional
	public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
		Restaurant restaurant = fetchOrFail(restaurantId);
		PaymentMethod paymentMethod = paymentMethodsService.fetchOrFail(paymentMethodId);
		
		restaurant.deletePaymentMethod(paymentMethod);
	
	}
	
	@Transactional
	public void associateUser(Long restaurantId, Long userId) {
		Restaurant restaurant = fetchOrFail(restaurantId);
		User user = userService.fetchOrFail(userId);
		
		restaurant.addResponsible(user);
	}
	
	@Transactional
	public void disassociateUser(Long restaurantId, Long userId) {
		Restaurant restaurant = fetchOrFail(restaurantId);
		User user = userService.fetchOrFail(userId);
		
		restaurant.deleteResponsible(user);
	}

}
