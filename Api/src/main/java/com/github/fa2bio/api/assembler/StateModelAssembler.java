package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.StateController;
import com.github.fa2bio.api.model.StateModel;
import com.github.fa2bio.core.hypermedia.DeliveryLinks;
import com.github.fa2bio.domain.model.State;

@Component
public class StateModelAssembler 
	extends RepresentationModelAssemblerSupport<State, StateModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DeliveryLinks deliveryLinks;
	
	public StateModelAssembler() {
		super(StateController.class, StateModel.class);
	}
	
	@Override
	public StateModel toModel(State state) {
		StateModel stateModel = createModelWithId(state.getId(), state);
		modelMapper.map(state, stateModel);
		
		stateModel.add(deliveryLinks.linkToStates("states"));

		return stateModel;
	}
	
	@Override
	public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities){
		return super.toCollectionModel(entities)
				.add(deliveryLinks.linkToStates());
	}
}
