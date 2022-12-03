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
public class KitchenIntegrationTests {

	@Autowired
	private KitchenService kitchenService;
	
	@Test
	public void testKitchenRegistrationSuccessfully() {
		
		//scenery
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("Chinesa");
		
		//action
		newKitchen = kitchenService.save(newKitchen);
		
		//validation
		assertThat(newKitchen).isNotNull();
		assertThat(newKitchen.getId()).isNotNull();

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void shouldFallWhenCadastrarKitchen_WhenWithoutName() {
		//scenery
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName(null);
		
		//action
		newKitchen = kitchenService.save(newKitchen);

	}
	
	@Test(expected = EntityInUseException.class)
	public void mustFail_WhenDeleteKitchenInUse() {
		kitchenService.delete(1L);
	}
	
	@Test(expected = KitchenNotFoundException.class)
	public void mustFail_WhenDeleteKitchenNonexistent() {
		kitchenService.delete(100L);
	}
}