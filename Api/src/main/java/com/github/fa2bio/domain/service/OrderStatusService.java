package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.model.Orderr;

@Service
public class OrderStatusService {
	    
	@Autowired
	private OrderService orderService;

	@Transactional
	public void confirm(String orderCode) {
		Orderr orderr = orderService.fetchOrFail(orderCode);
		orderr.confirm();
	}
	
	@Transactional
	public void cancel(String orderCode) {
		Orderr orderr = orderService.fetchOrFail(orderCode);
		orderr.cancel();
	}
	
	@Transactional
	public void deliver(String orderCode) {
		Orderr orderr = orderService.fetchOrFail(orderCode);
		orderr.deliver();
	}
}
