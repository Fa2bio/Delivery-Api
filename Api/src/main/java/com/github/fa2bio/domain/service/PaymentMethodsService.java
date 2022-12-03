package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.PaymentMethodNotFoundException;
import com.github.fa2bio.domain.model.PaymentMethod;
import com.github.fa2bio.domain.repository.PaymentMethodsRepository;

@Service
public class PaymentMethodsService {

	private static final String MSG_PAYMENTMETHOD_EM_USO 
	= "The payment method with code %d cannot be removed because it is in useo";

	@Autowired
	private PaymentMethodsRepository paymentMethodsRepository;
	
	@Transactional
	public PaymentMethod save (PaymentMethod paymentMethod) {
		return paymentMethodsRepository.save(paymentMethod);
	}
	
	@Transactional
	public void delete (Long id) {
		try {
			paymentMethodsRepository.deleteById(id);
			paymentMethodsRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PaymentMethodNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_PAYMENTMETHOD_EM_USO , id));
		}
	}
	
	public PaymentMethod fetchOrFail(Long paymentMethodId) {
		return paymentMethodsRepository.findById(paymentMethodId)
			.orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
	}
}
