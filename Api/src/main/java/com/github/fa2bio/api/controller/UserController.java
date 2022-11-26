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

import com.github.fa2bio.api.assembler.UserInputDisassembler;
import com.github.fa2bio.api.assembler.UserModelAssembler;
import com.github.fa2bio.api.model.UserModel;
import com.github.fa2bio.api.model.input.PasswordInput;
import com.github.fa2bio.api.model.input.UserInput;
import com.github.fa2bio.api.model.input.UserInputWithPassword;
import com.github.fa2bio.api.swaggeropenapi.controller.UserControllerSwagger;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.UserRepository;
import com.github.fa2bio.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerSwagger{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UserInputDisassembler usuarioInputDisassembler;
	
	@Override
	@GetMapping
	public List<UserModel> list(){
		return usuarioModelAssembler.toCollectionModel(userRepository.findAll());
	}
	
	@Override
	@GetMapping("/{userId}")
	public UserModel find(@PathVariable Long userId) {
		Usuario user = userService.fetchOrFail(userId);
		return usuarioModelAssembler.toModel(user);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserModel register(@RequestBody @Valid UserInputWithPassword userInput) {
		Usuario user = usuarioInputDisassembler.toDomainObject(userInput);
		return usuarioModelAssembler.toModel(userService.salvar(user));
	}
	
	@Override
	@PutMapping("/{userId}")
	public UserModel update(@PathVariable Long userId, @RequestBody @Valid UserInput userInput) {

		Usuario currentUser = userService.fetchOrFail(userId);	
		usuarioInputDisassembler.copyToDomainObject(userInput, currentUser);
		return usuarioModelAssembler.toModel(userService.salvar(currentUser));
	}
	
	@Override
	@PutMapping("/{userId}-updatePassword")
	@ResponseStatus(HttpStatus.OK)
	public void updatePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput passwordInput) {
		userService.alterarSenha(userId, passwordInput.getSenhaAtual(), passwordInput.getNovaSenha());
	}
	
	@Override
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long userId) {
		userService.excluir(userId);
	}
}
