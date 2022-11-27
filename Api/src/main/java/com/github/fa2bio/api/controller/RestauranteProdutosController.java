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

import com.github.fa2bio.api.assembler.ProductInputDisassembler;
import com.github.fa2bio.api.assembler.ProductModelAssembler;
import com.github.fa2bio.api.model.ProductModel;
import com.github.fa2bio.api.model.input.ProductInput;
import com.github.fa2bio.domain.model.Produto;
import com.github.fa2bio.domain.model.Restaurante;
import com.github.fa2bio.domain.repository.ProductRepository;
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
	private ProductRepository productRepository;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;
	
	@Autowired
	private ProductInputDisassembler productInputDisassembler;

	@GetMapping
	public List<ProductModel> listar(@PathVariable Long restauranteId, 
			@RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = cadastroRestaurante.fetchOrFail(restauranteId);
		List<Produto> todosProdutos = null;
		
		if(incluirInativos) todosProdutos = productRepository.findTodosByRestaurante(restaurante);
		else todosProdutos = productRepository.findAtivosByRestaurante(restaurante);
		
		return productModelAssembler.toCollectionModel(todosProdutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProductModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		return productModelAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProductInput productInput) {
		Restaurante restaurante = cadastroRestaurante.fetchOrFail(restauranteId);
		Produto produto = productInputDisassembler.toDomainObject(productInput);
		produto.setRestaurante(restaurante);
		return productModelAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
	
	@PutMapping("/{produtoId}")
	public ProductModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProductInput productInput) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		productInputDisassembler.copyToDomainObject(productInput, produto);
		return productModelAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
}
