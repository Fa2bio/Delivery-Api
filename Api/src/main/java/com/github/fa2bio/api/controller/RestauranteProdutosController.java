package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.ProdutoInputDisassembler;
import com.github.fa2bio.api.assembler.ProdutoModelAssembler;
import com.github.fa2bio.api.model.ProdutoModel;
import com.github.fa2bio.api.model.input.ProdutoInput;
import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.model.Restaurante;
import com.github.fa2bio.domain.repository.ProdutoRepository;
import com.github.fa2bio.domain.service.ProductService;
import com.github.fa2bio.domain.service.RestaurantService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

	@Autowired
	private RestaurantService cadastroRestaurante;
	
	@Autowired
	private ProductService cadastroProdutoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId, 
			@RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = cadastroRestaurante.fetchOrFail(restauranteId);
		List<Produto> todosProdutos = null;
		
		if(incluirInativos) todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		else todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
		
		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		return produtoModelAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = cadastroRestaurante.fetchOrFail(restauranteId);
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
		return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
}
