package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.PaymentMethodNotFoundException;
import com.github.fa2bio.domain.exception.OrderNotFoundException;
import com.github.fa2bio.domain.model.Cidade;
import com.github.fa2bio.domain.model.FormaPagamento;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.model.Restaurante;
import com.github.fa2bio.domain.model.Usuario;
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
	
	public Pedido fetchOrFail(String codigoPedido) {
		return orderRepository.findByCodigo(codigoPedido)
			.orElseThrow(() -> new OrderNotFoundException(codigoPedido));
	}
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		
		return orderRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Restaurante restaurante = restaurantService.fetchOrFail(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = paymentMethodsService.fetchOrFail(pedido.getFormaPagamento().getId());
		Cidade cidade = cityService.fetchOrFail(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario cliente = userService.fetchOrFail(pedido.getCliente().getId());
		
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(cliente);
		pedido.getEnderecoEntrega().setCidade(cidade);
		
		if(restaurante.naoAceitaFormaPagamento(formaPagamento)) throw new PaymentMethodNotFoundException(formaPagamento.getId(), restaurante.getId());


	}
	
	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = productService.buscarOuFalhar(
					pedido.getRestaurante().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
	
}