package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.domain.service.OrderStatusService;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class OrderStatusController {
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@PutMapping("/confirm")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirm(@PathVariable String orderCode) {
		orderStatusService.confirmar(orderCode);
	}
	
	@PutMapping("/cancellation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancellation(@PathVariable String orderCode) {
		orderStatusService.cancelar(orderCode);
	}
	
	@PutMapping("/delivered")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delivered(@PathVariable String orderCode) {
		orderStatusService.entregar(orderCode);
	}
}
