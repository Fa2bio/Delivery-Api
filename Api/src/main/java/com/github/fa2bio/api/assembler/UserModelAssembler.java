package com.github.fa2bio.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.controller.UserClusterController;
import com.github.fa2bio.api.controller.UserController;
import com.github.fa2bio.api.model.UserModel;
import com.github.fa2bio.domain.model.User;

@Component
public class UserModelAssembler 
	extends RepresentationModelAssemblerSupport<User, UserModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}
	
	@Override
	public UserModel toModel(User user) {
		UserModel userModel = createModelWithId(user.getId(), user);
		modelMapper.map(user, userModel);
		
		userModel.add(linkTo(UserController.class).withRel("users"));
		
		userModel.add(linkTo(methodOn(UserClusterController.class)
				.list(user.getId())).withRel("clusters-users"));
		
		return userModel;
	}
	
	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities){
		return super.toCollectionModel(entities)
				.add(linkTo(UserController.class).withSelfRel());
	}
}
