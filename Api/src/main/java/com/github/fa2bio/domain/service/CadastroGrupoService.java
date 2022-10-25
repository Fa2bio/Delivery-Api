package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntidadeEmUsoException;
import com.github.fa2bio.domain.exception.GrupoNaoEncontradoException;
import com.github.fa2bio.domain.model.Grupo;
import com.github.fa2bio.domain.model.Permissao;
import com.github.fa2bio.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	
	private static final String MSG_GRUPO_EM_USO 
	= "Grupo de código %d não pode ser removido, pois está em uso";

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}
	}
	
	@Transactional
	public void associar(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		grupo.associar(permissao);
	}
	
	@Transactional
	public void desassociar(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		grupo.desassociar(permissao);
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId)
			.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
}
