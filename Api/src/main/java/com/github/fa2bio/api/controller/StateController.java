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

import com.github.fa2bio.api.assembler.StateInputDisassembler;
import com.github.fa2bio.api.assembler.StateModelAssembler;
import com.github.fa2bio.api.model.StateModel;
import com.github.fa2bio.api.model.input.StateInput;
import com.github.fa2bio.api.swaggeropenapi.controller.StateControllerSwagger;
import com.github.fa2bio.domain.model.State;
import com.github.fa2bio.domain.repository.StateRepository;
import com.github.fa2bio.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController implements StateControllerSwagger{

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private StateModelAssembler stateModelAssembler;
	
	@Autowired
	private StateInputDisassembler stateInputDisassembler;
	
	@Override
	@GetMapping
	public List<StateModel> list() {
		return stateModelAssembler.toCollectionModel(stateRepository.findAll());
	}
	
	@Override
	@GetMapping("/{stateId}")
	public StateModel find(@PathVariable Long stateId) {
		State state = stateService.fetchOrFail(stateId);
		return stateModelAssembler.toModel(state);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateModel register(@RequestBody @Valid StateInput stateInput) {
		State state = stateInputDisassembler.toDomainObject(stateInput);
		return stateModelAssembler.toModel(stateService.save(state));
	}
	
	@Override
	@PutMapping("/{stateId}")
	public StateModel update(@PathVariable Long stateId,
			@RequestBody @Valid StateInput stateInput) {
		State currentState = stateService.fetchOrFail(stateId);
		
		stateInputDisassembler.copyToDomainObject(stateInput, currentState);
		
		return stateModelAssembler.toModel(stateService.save(currentState));
	}
	
	@Override
	@DeleteMapping("/{stateId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long stateId) {
		stateService.delete(stateId);	
	}
	
}
