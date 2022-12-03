package com.github.fa2bio;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.KitchenNotFoundException;
import com.github.fa2bio.domain.model.Kitchen;
import com.github.fa2bio.domain.service.KitchenService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private KitchenService cadastroCozinha;
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		
		//cenário
		Kitchen novaCozinha = new Kitchen();
		novaCozinha.setName("Chinesa");
		
		//ação
		novaCozinha = cadastroCozinha.save(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalharAoCadastrarCozinha_QuandoSemNome() {
		//cenário
		Kitchen novaCozinha = new Kitchen();
		novaCozinha.setName(null);
		
		//ação
		novaCozinha = cadastroCozinha.save(novaCozinha);

	}
	
	@Test(expected = EntityInUseException.class)
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		cadastroCozinha.delete(1L);
	}
	
	@Test(expected = KitchenNotFoundException.class)
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		cadastroCozinha.delete(100L);
	}
}