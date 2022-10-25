package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fa2bio.domain.exception.PedidoNaoEncontradoException;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscarOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
			.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}
	
}