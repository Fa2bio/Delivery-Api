package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.PaymentMethodsController;
import com.github.fa2bio.api.model.PaymentMethodModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.PaymentMethod;

@Component
public class PaymentMethodModelAssembler 
	extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public PaymentMethodModelAssembler() {
		super(PaymentMethodsController.class, PaymentMethodModel.class);
	}
	
	@Override
	public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
		PaymentMethodModel paymentModel = createModelWithId(paymentMethod.getId(), paymentMethod);
		
		modelMapper.map(paymentMethod, paymentModel);
		
		paymentModel.add(deliveryLinks.linkToPaymentMethods("payment-methods"));
		
		paymentModel.add(deliveryLinks.linkToDeletePaymentMethods(paymentMethod.getId(), "delete"));
		return paymentModel;
	}
	
	@Override
	public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> entities){
		return super.toCollectionModel(entities)
				.add(deliveryLinks.linkToPaymentMethods());
	}
}
