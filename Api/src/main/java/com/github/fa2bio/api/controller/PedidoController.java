package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.fa2bio.api.assembler.PedidoInputDisassembler;
import com.github.fa2bio.api.assembler.PedidoModelAssembler;
import com.github.fa2bio.api.assembler.PedidoResumoModelAssembler;
import com.github.fa2bio.api.model.PedidoModel;
import com.github.fa2bio.api.model.PedidoResumoModel;
import com.github.fa2bio.api.model.input.PedidoInput;
import com.github.fa2bio.domain.exception.EntidadeNaoEncontradaException;
import com.github.fa2bio.domain.exception.NegocioException;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.PedidoRepository;
import com.github.fa2bio.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@GetMapping
	public MappingJacksonValue listar(@RequestParam (required = false) String fields) {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos);
		
		MappingJacksonValue pedidosWrapper  = new MappingJacksonValue(pedidosModel);
		
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
		
		if(StringUtils.isNotBlank(fields)) {
			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
		}
		
		pedidosWrapper.setFilters(filterProvider);
		return pedidosWrapper;
	}
	
//	@GetMapping
//	public List<PedidoResumoModel> listar() {
//		List<Pedido> todosPedidos = pedidoRepository.findAll();
//		
//		return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
//	}
	
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			pedido.setCliente(new Usuario());
			pedido.getCliente().setId(1L);
			return pedidoModelAssembler.toModel(emissaoPedidoService.emitir(pedido));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(),e);
		}
	}
	
}