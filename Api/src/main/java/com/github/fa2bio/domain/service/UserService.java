package com.github.fa2bio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.PasswordNotMatchExceptiom;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.exception.UserNotFoundException;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.UserRepository;

@Service
public class UserService {

	private static final String MSG_USUARIO_EM_USO 
	= "Usuario de código %d não pode ser removida, pois está em uso";

	@Autowired
	private UserRepository usuarioRepository;
	
	@Autowired
	private GroupService cadastroGrupoService;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new BusinessException(String.format("Já existe um usuário cadastrado com o email %s", usuario.getEmail()));
		}
				
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluir(Long usuarioId) {
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(usuarioId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_USUARIO_EM_USO, usuarioId));
		}
		
	}
	
	@Transactional
	public void associate(Long usuarioId, Long grupoId) {
		Usuario usuario = fetchOrFail(usuarioId);
		Grupo grupo = cadastroGrupoService.fetchOrFail(grupoId);
		usuario.adicionarGrupo(grupo);
		
	}
	
	@Transactional
	public void disassociate(Long usuarioId, Long grupoId) {
		Usuario usuario = fetchOrFail(usuarioId);
		Grupo grupo = cadastroGrupoService.fetchOrFail(grupoId);
		usuario.removerGrupo(grupo);
		
	}
	
	public Usuario fetchOrFail(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
			.orElseThrow(() -> new UserNotFoundException(usuarioId));
	}

	public void updatePassword(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario user = fetchOrFail(usuarioId);
		if(user.getSenha().equals(senhaAtual)) {
			user.setSenha(novaSenha);
			salvar(user);
		}else throw new PasswordNotMatchExceptiom("Could not change password because the password entered is incorrect. Change the field senhaAtual and try again");
				
	}
}
