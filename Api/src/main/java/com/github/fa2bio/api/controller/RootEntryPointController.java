package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.core.hypermedia.DeliveryLinks;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	DeliveryLinks deliveryLinks;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		rootEntryPointModel.add(deliveryLinks.linkToCities("cities"));
		rootEntryPointModel.add(deliveryLinks.linkToClusters("clusters"));
		rootEntryPointModel.add(deliveryLinks.linkToKitchens("kitchens"));
		rootEntryPointModel.add(deliveryLinks.linkToOrders("orders"));
		rootEntryPointModel.add(deliveryLinks.linkToPaymentMethods("payment-methods"));
		rootEntryPointModel.add(deliveryLinks.linkToPermissions("permissions"));
		rootEntryPointModel.add(deliveryLinks.linkToRestaurants("restaurants"));
		rootEntryPointModel.add(deliveryLinks.linkToStates("states"));
		rootEntryPointModel.add(deliveryLinks.linkToStatistics("statistics"));
		rootEntryPointModel.add(deliveryLinks.linkToUsers("users"));
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}
}
