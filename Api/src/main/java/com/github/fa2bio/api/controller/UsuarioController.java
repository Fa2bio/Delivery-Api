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

import com.github.fa2bio.api.assembler.UsuarioInputDisassembler;
import com.github.fa2bio.api.assembler.UsuarioModelAssembler;
import com.github.fa2bio.api.model.UsuarioModel;
import com.github.fa2bio.api.model.input.SenhaInput;
import com.github.fa2bio.api.model.input.UsuarioInput;
import com.github.fa2bio.api.model.input.UsuarioInputComSenha;
import com.github.fa2bio.domain.exception.NegocioException;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.UsuarioRepository;
import com.github.fa2bio.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@GetMapping
	public List<UsuarioModel> listar(){
		return usuarioModelAssembler.toCollectionModel(UsuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInputComSenha usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {

		Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtual));
	}
	
	@PutMapping("/{usuarioId}-atualizarSenha")
	public UsuarioModel atualizarComSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		if(senhaInput.getSenhaAtual().equalsIgnoreCase(usuarioAtual.getSenha())) {
			usuarioAtual.setSenha(senhaInput.getSenhaNova());
			return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtual));
		}else	throw new NegocioException("Senha incorreta");
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long usuarioId) {
		cadastroUsuarioService.excluir(usuarioId);
	}
}
