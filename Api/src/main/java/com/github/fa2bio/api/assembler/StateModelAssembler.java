package com.github.fa2bio.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.StateController;
import com.github.fa2bio.api.model.StateModel;
import com.github.fa2bio.domain.model.State;

@Component
public class StateModelAssembler 
	extends RepresentationModelAssemblerSupport<State, StateModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public StateModelAssembler() {
		super(StateController.class, StateModel.class);
	}
	
	@Override
	public StateModel toModel(State state) {
		StateModel stateModel = createModelWithId(state.getId(), state);
		modelMapper.map(state, stateModel);
		
		stateModel.add(linkTo(methodOn(StateController.class)
				.list()).withRel("states"));

		return stateModel;
	}
	
	@Override
	public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities){
		return super.toCollectionModel(entities)
				.add(linkTo(StateController.class).withSelfRel());
	}
}
