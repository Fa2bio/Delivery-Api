package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.OrderNotFoundException;
import com.github.fa2bio.domain.exception.PaymentMethodNotFoundException;
import com.github.fa2bio.domain.model.City;
import com.github.fa2bio.domain.model.Orderr;
import com.github.fa2bio.domain.model.PaymentMethod;
import com.github.fa2bio.domain.model.Product;
import com.github.fa2bio.domain.model.Restaurant;
import com.github.fa2bio.domain.model.User;
import com.github.fa2bio.domain.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private PaymentMethodsService paymentMethodsService;
	
	@Autowired
	private ProductService productService;
	
	public Orderr fetchOrFail(String orderCode) {
		return orderRepository.findByCodigo(orderCode)
			.orElseThrow(() -> new OrderNotFoundException(orderCode));
	}
	
	@Transactional
	public Orderr issue(Orderr orderr) {
		validateOrder(orderr);
		validateItens(orderr);		
		orderr.setRateShipping(orderr.getRestaurant().getRateShipping());
		orderr.calculateAmount();
		
		return orderRepository.save(orderr);
	}

	private void validateOrder(Orderr orderr) {
		Restaurant restaurant = restaurantService.fetchOrFail(orderr.getRestaurant().getId());
		PaymentMethod paymentMethod = paymentMethodsService.fetchOrFail(orderr.getPaymentMethod().getId());
		City city = cityService.fetchOrFail(orderr.getDeliveryAddress().getCity().getId());
		User client = userService.fetchOrFail(orderr.getClient().getId());
		
		orderr.setRestaurant(restaurant);
		orderr.setPaymentMethod(paymentMethod);
		orderr.setClient(client);
		orderr.getDeliveryAddress().setCity(city);
		
		if(restaurant.dontAcceptPaymentForm(paymentMethod)) throw new PaymentMethodNotFoundException(paymentMethod.getId(), restaurant.getId());


	}
	
	private void validateItens(Orderr orderr) {
		orderr.getItems().forEach(item -> {
			Product product = productService.fetchOrFail(
					orderr.getRestaurant().getId(), item.getProduct().getId());
			
			item.setOrderr(orderr);
			item.setProduct(product);
			item.setUnitPrice(product.getPrice());
		});
	}
	
}