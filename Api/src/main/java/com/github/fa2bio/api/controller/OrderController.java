package com.github.fa2bio.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.OrderAbstractModelAssembler;
import com.github.fa2bio.api.assembler.OrderInputDisassembler;
import com.github.fa2bio.api.assembler.OrderModelAssembler;
import com.github.fa2bio.api.model.OderAbstractModel;
import com.github.fa2bio.api.model.OrderModel;
import com.github.fa2bio.api.model.input.OrderInput;
import com.github.fa2bio.api.swaggeropenapi.controller.OrderControllerSwagger;
import com.github.fa2bio.core.data.PageableTranslator;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.exception.EntityNotFoundException;
import com.github.fa2bio.domain.filter.OrderFilter;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.OrderRepository;
import com.github.fa2bio.domain.service.OrderService;
import com.github.fa2bio.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/orders")
public class OrderController implements OrderControllerSwagger{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService issuingOrderService;
	
	@Autowired
	private OrderModelAssembler orderModelAssembler;
	
	@Autowired
	private OrderAbstractModelAssembler orderAbstractModelAssembler;
	
	@Autowired
	private OrderInputDisassembler orderInputDisassembler;
	
	@Override
	@GetMapping
	public Page<OderAbstractModel> findWithPageable(@PageableDefault(size = 10) Pageable pageable, OrderFilter filter) {
		
		pageable = mappingPageable(pageable);
	
		Page<Pedido> ordersPage = orderRepository.findAll(PedidoSpecs.
				usandoFiltro(filter),pageable);
		
		List<OderAbstractModel> ordersAbstractModel = orderAbstractModelAssembler.
				toCollectionModel(ordersPage.getContent());
		
		Page<OderAbstractModel> orderAbstractModelPage = new PageImpl<>(ordersAbstractModel, pageable,
				ordersPage.getTotalElements());
		
		return orderAbstractModelPage;
	}

	@Override
	@GetMapping("/{codeOrder}")
	public OrderModel find(@PathVariable String codeOrder) {
		Pedido order = issuingOrderService.fetchOrFail(codeOrder);
		
		return orderModelAssembler.toModel(order);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderModel register(@RequestBody @Valid OrderInput orderInput) {
		try {
			Pedido newOrder = orderInputDisassembler.toDomainObject(orderInput);
			
			// TODO pegar usuário autenticado
			newOrder.setCliente(new Usuario());
			newOrder.getCliente().setId(1L);
			return orderModelAssembler.toModel(issuingOrderService.emitir(newOrder));
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}
	
	private Pageable mappingPageable(Pageable apiPegeable) {
		var mapping = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.nome", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome"
				);
		return PageableTranslator.translate(apiPegeable, mapping);
	}
	
}