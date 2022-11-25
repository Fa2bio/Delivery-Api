package com.github.fa2bio;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.fa2bio.domain.exception.CozinhaNaoEncontradaException;
import com.github.fa2bio.domain.exception.EntidadeEmUsoException;
import com.github.fa2bio.domain.model.Cozinha;
import com.github.fa2bio.domain.service.KitchenService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private KitchenService cadastroCozinha;
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		//ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalharAoCadastrarCozinha_QuandoSemNome() {
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		//ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);

	}
	
	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		cadastroCozinha.excluir(1L);
	}
	
	@Test(expected = CozinhaNaoEncontradaException.class)
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		cadastroCozinha.excluir(100L);
	}
}