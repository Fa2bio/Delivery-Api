package com.github.fa2bio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.RestaurantNotFoundException;
import com.github.fa2bio.domain.model.Cidade;
import com.github.fa2bio.domain.model.Cozinha;
import com.github.fa2bio.domain.model.FormaPagamento;
import com.github.fa2bio.domain.model.Restaurante;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restauranteRepository;
	
	@Autowired
	private KitchenService cadastroCozinhaService;
	
	@Autowired
	private CityService cadastroCidadeService;
	
	@Autowired
	private PaymentMethodsService cadastroFormaPagamentoService;
	
	@Autowired
	private UserService cadastroUsuarioService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurant) {
		Long cozinhaId = restaurant.getCozinha().getId();
		Long cidadeId = restaurant.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cadastroCozinhaService.fetchOrFail(cozinhaId);
		Cidade cidade = cadastroCidadeService.fetchOrFail(cidadeId);
		
		restaurant.setCozinha(cozinha);
		restaurant.getEndereco().setCidade(cidade);
		
		return restauranteRepository.save(restaurant);
	}
	
	@Transactional
	public void activate (Long restaurantId) {
		Restaurante restauranteAtual = fetchOrFail(restaurantId);
		restauranteAtual.ativar();
	}
	
	@Transactional
	public void activateMultiples(List<Long> restaurantId) {
		for (Long long1 : restaurantId) {
			this.activate(long1);	
		}
	}
	
	@Transactional
	public void inactivate (Long restaurantId) {
		Restaurante restauranteAtual = fetchOrFail(restaurantId);
		restauranteAtual.inativar();
	}
	
	@Transactional
	public void inactivateMultiples(List<Long> restaurantIds) {
		restaurantIds.forEach(this::inactivate);
	}
	
	@Transactional
	public void open(Long restaurantId) {
		Restaurante restaurante = fetchOrFail(restaurantId);
		restaurante.abrir();
	}
	
	@Transactional
	public void close(Long restaurantId) {
		Restaurante restaurante = fetchOrFail(restaurantId);
		restaurante.fechar();
	}
	
	public Restaurante fetchOrFail(Long restaurantId) {
		return restauranteRepository.findById(restaurantId)
			.orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
	}
	
	@Transactional
	public void associatePaymentMethod(Long restaurantId, Long formaPagamentoId) {
		Restaurante restaurante = fetchOrFail(restaurantId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.fetchOrFail(formaPagamentoId);
		
		restaurante.adicionarFormaPagamento(formaPagamento);
	
	}
	
	@Transactional
	public void disassociatePaymentMethod(Long restaurantId, Long formaPagamentoId) {
		Restaurante restaurante = fetchOrFail(restaurantId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.fetchOrFail(formaPagamentoId);
		
		restaurante.removerFormaPagamento(formaPagamento);
	
	}
	
	@Transactional
	public void associateUser(Long restaurantId, Long usuarioId) {
		Restaurante restaurante = fetchOrFail(restaurantId);
		Usuario usuario = cadastroUsuarioService.fetchOrFail(usuarioId);
		
		restaurante.adicionarResponsavel(usuario);
	}
	
	@Transactional
	public void disassociateUser(Long restaurantId, Long usuarioId) {
		Restaurante restaurante = fetchOrFail(restaurantId);
		Usuario usuario = cadastroUsuarioService.fetchOrFail(usuarioId);
		
		restaurante.removerResponsavel(usuario);
	}

}
