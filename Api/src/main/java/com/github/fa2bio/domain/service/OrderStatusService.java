package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.model.Pedido;

@Service
public class OrderStatusService {
	    
	@Autowired
	private OrderService emissaoPedidoService;

	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.fetchOrFail(codigoPedido);
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.fetchOrFail(codigoPedido);
		pedido.cancelar();
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.fetchOrFail(codigoPedido);
		pedido.entregar();
	}
}
