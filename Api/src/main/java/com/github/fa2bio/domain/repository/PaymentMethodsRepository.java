package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.FormaPagamento;

@Repository
public interface PaymentMethodsRepository extends CustomJpaRepository<FormaPagamento, Long> {

}
