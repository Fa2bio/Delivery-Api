package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.FormaPagamentoNaoEncontradaException;
import com.github.fa2bio.domain.exception.PedidoNaoEncontradoException;
import com.github.fa2bio.domain.model.Cidade;
import com.github.fa2bio.domain.model.FormaPagamento;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.model.Restaurante;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CityService cadastroCidadeService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private PaymentMethodsService cadastroFormaPagamentoService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	public Pedido buscarOuFalhar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
			.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		
		return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.fetchOrFail(pedido.getFormaPagamento().getId());
		Cidade cidade = cadastroCidadeService.fetchOrFail(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
		
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(cliente);
		pedido.getEnderecoEntrega().setCidade(cidade);
		
		if(restaurante.naoAceitaFormaPagamento(formaPagamento)) throw new FormaPagamentoNaoEncontradaException(formaPagamento.getId(), restaurante.getId());


	}
	
	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProdutoService.buscarOuFalhar(
					pedido.getRestaurante().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
	
}