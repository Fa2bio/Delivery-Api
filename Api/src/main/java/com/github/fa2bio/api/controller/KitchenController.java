package com.github.fa2bio.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.github.fa2bio.api.assembler.KitchenInputDisassembler;
import com.github.fa2bio.api.assembler.KitchenModelAssembler;
import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.api.model.input.KitchenInput;
import com.github.fa2bio.api.swaggeropenapi.controller.KitchenControllerSwagger;
import com.github.fa2bio.domain.model.Kitchen;
import com.github.fa2bio.domain.repository.KitchenRepository;
import com.github.fa2bio.domain.service.KitchenService;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController implements KitchenControllerSwagger{

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private KitchenService kitchenService;
	
	@Autowired
	private KitchenModelAssembler kitchenModelAssembler;
	
	@Autowired
	private KitchenInputDisassembler kitchenInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;
	
	@Override
	@GetMapping
	public PagedModel<KitchenModel> list(@PageableDefault(size = 10) Pageable pegeable) {
		Page<Kitchen> kitchenPage = kitchenRepository.findAll(pegeable);
	
		PagedModel<KitchenModel> kitchenPagedModel = pagedResourcesAssembler
				.toModel(kitchenPage, kitchenModelAssembler);
				
		return kitchenPagedModel;
	}
	
	@Override
	@GetMapping("/{kitchenId}")
	public KitchenModel find(@PathVariable Long kitchenId) {
		Kitchen kitchen = kitchenService.fetchOrFail(kitchenId);
		return kitchenModelAssembler.toModel(kitchen);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public KitchenModel register(@RequestBody @Valid KitchenInput kitchenInput) {
		Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);
		return kitchenModelAssembler.toModel(kitchenService.save(kitchen));
	}
	
	@Override
	@PutMapping("/{kitchenId}")
	public KitchenModel update(@PathVariable Long kitchenId, @RequestBody @Valid KitchenInput kitchenInput) {
		Kitchen currentkitchen = kitchenService.fetchOrFail(kitchenId);
		
		kitchenInputDisassembler.copyToDomainObject(kitchenInput, currentkitchen);
		
		return kitchenModelAssembler.toModel(kitchenService.save(currentkitchen));
	}
	
	@Override
	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long kitchenId) {
		kitchenService.delete(kitchenId);
	}
	
}
