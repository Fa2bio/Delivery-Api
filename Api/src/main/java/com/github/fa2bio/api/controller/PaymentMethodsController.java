package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.PaymentMethodInputDisassembler;
import com.github.fa2bio.api.assembler.PaymentMethodModelAssembler;
import com.github.fa2bio.api.model.PaymentMethodModel;
import com.github.fa2bio.api.model.input.PaymentMethodInput;
import com.github.fa2bio.api.swaggeropenapi.controller.PaymentMethodsSwagger;
import com.github.fa2bio.domain.model.PaymentMethod;
import com.github.fa2bio.domain.repository.PaymentMethodsRepository;
import com.github.fa2bio.domain.service.PaymentMethodsService;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodsController implements PaymentMethodsSwagger{

	@Autowired
	private PaymentMethodsRepository paymentMethodsRepository;
	
	@Autowired
	private PaymentMethodsService paymentMethodsService;
	
	@Autowired
	private PaymentMethodModelAssembler paymentMethodModelAssembler;
	
	@Autowired
	private PaymentMethodInputDisassembler paymentMethodInputDisassembler;
	
	@Override
	@GetMapping
	public List<PaymentMethodModel> list(){
		return paymentMethodModelAssembler.toCollectionModel(paymentMethodsRepository.findAll());
	}
	
	@Override
	@GetMapping("/{paymentMethodId}")
	public PaymentMethodModel find(@PathVariable Long paymentMethodId) {
		PaymentMethod paymentMethod = paymentMethodsService.fetchOrFail(paymentMethodId);
		return paymentMethodModelAssembler.toModel(paymentMethod);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodModel register(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
		PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);
		return paymentMethodModelAssembler.toModel(paymentMethodsService.save(paymentMethod));
	}
	
	@PutMapping("/{paymentMethodId}")
	public PaymentMethodModel update(@PathVariable Long paymentMethodId, @RequestBody @Valid PaymentMethodInput paymentMethodInput) {
		PaymentMethod paymentMethod = paymentMethodsService.fetchOrFail(paymentMethodId);
		paymentMethodInputDisassembler.copyToDomainObject(paymentMethodInput, paymentMethod);
		return paymentMethodModelAssembler.toModel(paymentMethodsService.save(paymentMethod));
	}
	
	@Override
	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long paymentMethodId) {
		paymentMethodsService.delete(paymentMethodId);
	}
}
