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

import com.github.fa2bio.api.assembler.OrderrAbstractModelAssembler;
import com.github.fa2bio.api.assembler.OrderrInputDisassembler;
import com.github.fa2bio.api.assembler.OrderrModelAssembler;
import com.github.fa2bio.api.model.OrderrAbstractModel;
import com.github.fa2bio.api.model.OrderrModel;
import com.github.fa2bio.api.model.input.OrrderInput;
import com.github.fa2bio.api.swaggeropenapi.controller.OrderControllerSwagger;
import com.github.fa2bio.core.data.PageableTranslator;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.exception.EntityNotFoundException;
import com.github.fa2bio.domain.filter.OrderFilter;
import com.github.fa2bio.domain.model.Orderr;
import com.github.fa2bio.domain.model.User;
import com.github.fa2bio.domain.repository.OrderRepository;
import com.github.fa2bio.domain.service.OrderService;
import com.github.fa2bio.infrastructure.repository.spec.OrderSpecs;

@RestController
@RequestMapping(value = "/orders")
public class OrderController implements OrderControllerSwagger{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService issuingOrderService;
	
	@Autowired
	private OrderrModelAssembler orderrModelAssembler;
	
	@Autowired
	private OrderrAbstractModelAssembler orderrAbstractModelAssembler;
	
	@Autowired
	private OrderrInputDisassembler orderrInputDisassembler;
	
	@Override
	@GetMapping
	public Page<OrderrAbstractModel> findWithPageable(@PageableDefault(size = 10) Pageable pageable, OrderFilter filter) {
		
		pageable = mappingPageable(pageable);
	
		Page<Orderr> ordersPage = orderRepository.findAll(OrderSpecs.
				usingFilter(filter),pageable);
		
		List<OrderrAbstractModel> ordersAbstractModel = orderrAbstractModelAssembler.
				toCollectionModel(ordersPage.getContent());
		
		Page<OrderrAbstractModel> orderAbstractModelPage = new PageImpl<>(ordersAbstractModel, pageable,
				ordersPage.getTotalElements());
		
		return orderAbstractModelPage;
	}

	@Override
	@GetMapping("/{orderCode}")
	public OrderrModel find(@PathVariable String orderCode) {
		Orderr orderr = issuingOrderService.fetchOrFail(orderCode);
		
		return orderrModelAssembler.toModel(orderr);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderrModel register(@RequestBody @Valid OrrderInput orrderInput) {
		try {
			Orderr newOrder = orderrInputDisassembler.toDomainObject(orrderInput);
			
			// TODO get authenticated user
			newOrder.setClient(new User());
			newOrder.getClient().setId(1L);
			return orderrModelAssembler.toModel(issuingOrderService.issue(newOrder));
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}
	
	private Pageable mappingPageable(Pageable apiPegeable) {
		var mapping = Map.of(
				"uuiCode", "uuiCode",
				"subtotal", "subtotal",
				"rateShipping", "rateShipping",
				"totalAmount", "totalAmount",
				"restaurant.name", "restaurant.name",
				"restaurant.id", "restaurant.id",
				"client.id", "client.id",
				"client.name", "client.name"
				);
		return PageableTranslator.translate(apiPegeable, mapping);
	}
	
}