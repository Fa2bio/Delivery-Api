package com.github.fa2bio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.api.model.input.PasswordInput;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.PasswordNotMatchExceptiom;
import com.github.fa2bio.domain.exception.UserNotFoundException;
import com.github.fa2bio.domain.model.Cluster;
import com.github.fa2bio.domain.model.User;
import com.github.fa2bio.domain.repository.UserRepository;

@Service
public class UserService {

	private static final String MSG_USER_IN_USE  
	= "The user with code %d cannot be removed because it is in use";

	@Autowired
	private UserRepository usuarioRepository;
	
	@Autowired
	private ClusterService clusterService;
	
	@Transactional
	public User save(User user) {
		
		usuarioRepository.detach(user);
		
		Optional<User> existingUser = usuarioRepository.findByEmail(user.getEmail());
		if(existingUser.isPresent() && !existingUser.get().equals(user)) {
			throw new BusinessException(String.format("There is already a registered user with email %s", user.getEmail()));
		}
				
		return usuarioRepository.save(user);
	}
	
	@Transactional
	public void delete(Long userId) {
		try {
			usuarioRepository.deleteById(userId);
			usuarioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_USER_IN_USE, userId));
		}
		
	}
	
	@Transactional
	public void associate(Long userId, Long groupId) {
		User user = fetchOrFail(userId);
		Cluster cluster = clusterService.fetchOrFail(groupId);
		user.addGroup(cluster);
		
	}
	
	@Transactional
	public void disassociate(Long userId, Long groupId) {
		User user = fetchOrFail(userId);
		Cluster cluster = clusterService.fetchOrFail(groupId);
		user.removeGroup(cluster);
		
	}
	
	public User fetchOrFail(Long userId) {
		return usuarioRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	public void updatePassword(Long userId, PasswordInput passwordInput) {
		User user = fetchOrFail(userId);
		if(user.passwordCoincide(passwordInput.getCurrentPassword())) {
			user.setPassword(passwordInput.getNewPassword());
			save(user);
		}else throw new PasswordNotMatchExceptiom("Could not change password because the password entered is incorrect. Change the field senhaAtual and try again");
				
	}
}
