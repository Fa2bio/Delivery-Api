package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.PaymentMethodNotFoundException;
import com.github.fa2bio.domain.model.FormaPagamento;
import com.github.fa2bio.domain.repository.PaymentMethodsRepository;

@Service
public class PaymentMethodsService {

	private static final String MSG_FORMAPAGAMENTO_EM_USO 
	= "Forma de pagamento de código %d não pode ser removida, pois está em uso";

	@Autowired
	private PaymentMethodsRepository formaPagamentoRepository;
	
	@Transactional
	public FormaPagamento salvar (FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	@Transactional
	public void excluir (Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
			formaPagamentoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PaymentMethodNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_FORMAPAGAMENTO_EM_USO, id));
		}
	}
	
	public FormaPagamento fetchOrFail(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
			.orElseThrow(() -> new PaymentMethodNotFoundException(formaPagamentoId));
	}
}
