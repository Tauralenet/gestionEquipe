package com.fr.adaming.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Equipe;
import com.fr.adaming.service.IEquipeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EquipeServiceTest {

	@Autowired
	private IEquipeService service;

	private static Equipe result;

	@Before
	public void before() {
		System.out.println("************************Before Method ******************************");
	}

	@After
	public void deleteItems() {
		System.out.println("************************After Method******************************");
		if (result != null && result.getId() != null) {
			System.out.println(result.getId());
			service.deleteById(result.getId());
		}
	}

	@Test
	public void a_createValidEquipe() {
		Equipe testEquipe = new Equipe(null, "nomeq", "ville");

		result = service.create(testEquipe);
		assertNotNull(result.getId());
	}

	@Test
	public void ab_createValidEquipeIdIsNull() {
		Equipe testEquipe = new Equipe(null, "nomeq2", "ville");

		result = service.create(testEquipe);
		assertNotNull(result.getId());
	}

	@Test
	public void ac_createValidEquipeIdZero() {

		Equipe testEquipe = new Equipe(0L, "nomeq3", "ville");

		result = service.create(testEquipe);
		assertNotNull(result.getId());
	}

	@Test
	public void ad_createEquipeIdExist() {
		Equipe testEquipe = new Equipe(null, "nomeq4", "ville");
		testEquipe = service.create(testEquipe);

		Equipe testEquipe2 = new Equipe(testEquipe.getId(), "nomeq5", "ville");
		result = service.create(testEquipe2);

		service.deleteById(testEquipe.getId());
		assertNull(result);
	}


	@Test
	public void b_updateValidEquipe() {
		Equipe testEquipe = new Equipe(null, "nomeq", "ville");
		testEquipe = service.create(testEquipe);
		Equipe testEquipe2 = new Equipe(testEquipe.getId(), "nomeqUp", "ville");

		result = service.update(testEquipe2);
		assertNotNull(result.getId());
	}

	@Test
	public void bb_updateNotValidEquipeIdIsNull() {
		Equipe testEquipe = new Equipe(null, "nomeq6", "ville");
		testEquipe = service.create(testEquipe);

		Equipe testEquipe2 = new Equipe(null, "nomeq7", "ville");
		result = service.update(testEquipe2);

		service.deleteById(testEquipe.getId());
		assertNull(result);
	}

	@Test
	public void bc_updateNotValidEquipeIdZero() {
		// Doit générer un null puisque update
		Equipe testEquipe = new Equipe(null, "nomeq8", "ville");
		testEquipe = service.create(testEquipe);

		Equipe testEquipe2 = new Equipe(0L, "nomeq9", "ville");
		result = service.update(testEquipe2);

		service.deleteById(testEquipe.getId());
		assertNull(result);
	}

	@Test
	public void c_readByIdValidEquipe() {
		Equipe testEquipe = new Equipe(null, "nomeq10", "ville");
		testEquipe = service.create(testEquipe);

		result = service.findById(testEquipe.getId());
		assertNotNull(result.getId());
	}

	@Test
	public void c_readByIdNullEquipe() {
		Equipe testEquipe = new Equipe(null, "nomeq11", "ville");
		testEquipe = service.create(testEquipe);

		result = service.findById(null);
		service.deleteById(testEquipe.getId());
		assertNull(result);
	}

	@Test
	public void c_readByIdZeroEquipe() {
		Equipe testEquipe = new Equipe(null, "nomeq12", "ville");
		testEquipe = service.create(testEquipe);

		result = service.findById(0L);
		service.deleteById(testEquipe.getId());
		assertNull(result);
	}

	@Test
	public void d_deleteValidEquipe() {
		a_createValidEquipe();
		Long id = result.getId();

		assertTrue(service.deleteById(id));
		result = null;
	}

	@Test
	public void db_deleteInvalidEquipe() {
		a_createValidEquipe();
		Long id = null;

		assertFalse(service.deleteById(id));
		result = null;
	}

}
