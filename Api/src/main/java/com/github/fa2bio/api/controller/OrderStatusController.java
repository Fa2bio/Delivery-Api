package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.swaggeropenapi.controller.OrderStatusControllerSwagger;
import com.github.fa2bio.domain.service.OrderStatusService;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class OrderStatusController implements OrderStatusControllerSwagger{
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Override
	@PutMapping("/confirm")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirm(@PathVariable String orderCode) {
		orderStatusService.confirmar(orderCode);
	}
	
	@Override
	@PutMapping("/cancellation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancellation(@PathVariable String orderCode) {
		orderStatusService.cancelar(orderCode);
	}
	
	@Override
	@PutMapping("/delivered")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delivered(@PathVariable String orderCode) {
		orderStatusService.entregar(orderCode);
	}
}
