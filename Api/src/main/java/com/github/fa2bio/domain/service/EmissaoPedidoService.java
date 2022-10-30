package com.github.fa2bio.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.FormaPagamentoNaoEncontradaException;
import com.github.fa2bio.domain.exception.PedidoNaoEncontradoException;
import com.github.fa2bio.domain.model.Cidade;
import com.github.fa2bio.domain.model.FormaPagamento;
import com.github.fa2bio.domain.model.ItemPedido;
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
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
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
		pedido.calcularTotal();
		
		return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
		
		for (ItemPedido itemPedido : pedido.getItens()) {
			Produto produto = cadastroProdutoService.buscarOuFalhar(restaurante.getId(), itemPedido.getProduto().getId());
			itemPedido.setProduto(produto);
		}
		
		if(!restaurante.getFormasPagamento().contains(formaPagamento)) throw new FormaPagamentoNaoEncontradaException(formaPagamento.getId(), restaurante.getId());
		
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(cliente);
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.getEnderecoEntrega().getCidade().getEstado().setNome(cidade.getEstado().getNome());

	}
	
	private void validarItens(Pedido pedido) {
		for (ItemPedido itemPedido : pedido.getItens()) {
			cadastroProdutoService.buscarOuFalhar(pedido.getRestaurante().getId(),itemPedido.getProduto().getId());
			Produto produto = cadastroProdutoService.buscarOuFalhar(itemPedido.getProduto().getId());
			
			double total = itemPedido.getQuantidade()*produto.getPreco().doubleValue();
			itemPedido.setPrecoUnitario(produto.getPreco());
			itemPedido.setPrecoTotal(new BigDecimal(total));
		}
	}
	
}