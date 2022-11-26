package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.GroupNotFoundException;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.model.Permissao;
import com.github.fa2bio.domain.repository.GroupRepository;

@Service
public class GroupService {
	
	private static final String MSG_GRUPO_EM_USO 
	= "Grupo de código %d não pode ser removido, pois está em uso";

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return groupRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			groupRepository.deleteById(id);
			groupRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GroupNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_GRUPO_EM_USO, id));
		}
	}
	
	@Transactional
	public void associatePermission(Long grupoId, Long permissaoId) {
		Grupo grupo = fetchOrFail(grupoId);
		Permissao permissao = permissionService.fetchOrFail(permissaoId);
		grupo.adicionarPermissao(permissao);
	}
	
	@Transactional
	public void disassociatePermission(Long grupoId, Long permissaoId) {
		Grupo grupo = fetchOrFail(grupoId);
		Permissao permissao = permissionService.fetchOrFail(permissaoId);
		grupo.removerPermissao(permissao);
	}
	
	public Grupo fetchOrFail(Long grupoId) {
		return groupRepository.findById(grupoId)
			.orElseThrow(() -> new GroupNotFoundException(grupoId));
	}
}
