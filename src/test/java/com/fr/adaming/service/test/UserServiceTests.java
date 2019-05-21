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

import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;

// Préciser la version de Junit avec laquelle on veut faire les tests
@RunWith(SpringRunner.class)
// Appeler le coontexte spring pour faire l'injection des dépendances
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// Ordre de test des méthodes par ordre alphabétique
public class UserServiceTests {

	@Autowired
	private IUserService service;
	
	private static User result;
	
	@Before
	public void before() {
		System.out.println("************************Before Method ******************************");
	}
	
	@After
	public void deleteItems() {
		System.out.println("************************After Method******************************");
		if(result != null && result.getId() != null) {
			System.out.println(result.getId());
			service.deleteById(result.getId());
		}
	}
	
	@Test
	public void a_createValidUser() {
		User testUser = new User(null, "nom", "email@email.fr", "pwd", null);
		
		 result = service.create(testUser);
		assertNotNull(result.getId());
	}
	
	@Test
	public void ab_createValidUserIdIsNull() {
		User testUser = new User(null, "nom", "email@email1.fr", "pwd", null);
		
		result = service.create(testUser);
		assertNotNull(result.getId());
		
		//Y'aura une insertion d'un user ==> result != null
	}
	
	@Test
	public void ac_createValidUserIdZero() {
		// 
		User testUser = new User(0L, "nom", "email@email2.fr", "pwd", null);
		
		result = service.create(testUser);
		assertNotNull(result.getId());
	}
	
	@Test
	public void ad_createUserIdExist() {
		// Doit renvoyer null puisque c'est un create, on ne peut pas faire un getId sur un assertNull
		User testUser = new User(null, "nom", "email@email3.fr", "pwd", null);
		testUser = service.create(testUser);
		
		User testUser2 = new User(testUser.getId(), "nom", "email@email31.fr", "pwd", null);
		result = service.create(testUser2);
		
		service.deleteById(testUser.getId());
		assertNull(result);
	}

	@Test
	public void ae_createUserEmailExist() {
		// Doit générer une exception 
		User testUser = new User(null, "nom", "email@email4.fr", "pwd", null);
		User testUser2 = new User(null, "nom", "email@email4.fr", "pwd", null);
		
		service.create(testUser);
		result = service.create(testUser2);
		assertNull(result);
		service.deleteById(testUser.getId());
		//Y'aura pas d'enregistrement ==> result == null
	}
	
	@Test
	public void b_updateValidUser() {
		User testUser = new User(null, "nom", "email@email.fr", "pwd", null);
		testUser= service.create(testUser);
		User testUser2 = new User(testUser.getId(), "nomUp", "email@email.fr", "pwd", null);
		
		result = service.update(testUser2);
		assertNotNull(result.getId());
	}
	
	@Test
	public void bb_updateNotValidUserIdIsNull() {
		// Doit générer un null puisque update
		User testUser = new User(null, "nom", "email@email6.fr", "pwd", null);
		testUser= service.create(testUser);
		
		User testUser2 = new User(null, "nom", "email@email6.fr", "pwd", null);
		result = service.update(testUser2);
		
		service.deleteById(testUser.getId());
		assertNull(result);
	}
	
	@Test
	public void bc_updateNotValidUserIdZero() {
		// Doit générer un null puisque update
		User testUser = new User(null, "nom", "email@email7.fr", "pwd", null);
		testUser= service.create(testUser);
		
		User testUser2 = new User(0L, "nom", "email@email7.fr", "pwd", null);
		result = service.update(testUser2);
		
		service.deleteById(testUser.getId());
		assertNull(result);
	}
	
	@Test
	public void bd_updateUserIdExistEmailExist() {
		// Doit générer un null contrainte unique email
		User testUser = new User(null, "nom", "email@email8.fr", "pwd", null);
		testUser= service.create(testUser);
		
		User testUser3 = new User(null, "nom", "email@email9.fr", "pwd", null);
		testUser3 = service.create(testUser3);
				
		User testUser2 = new User(testUser.getId(), "nom", "email@email9.fr", "pwd", null);
		result = service.update(testUser2);
		
		service.deleteById(testUser.getId());
		service.deleteById(testUser3.getId());
		assertNull(result);
	}

	@Test
	public void be_updateUserNotValidIdAndEmailExist() {
		User testUser = new User(null, "nom", "email@email10.fr", "pwd", null);
		testUser= service.create(testUser);
		
		User testUser3 = new User(null, "nom", "email@email11.fr", "pwd", null);
		testUser3 = service.create(testUser3);
				
		User testUser2 = new User(null, "nom", "email@email11.fr", "pwd", null);
		result = service.update(testUser2);
		
		service.deleteById(testUser.getId());
		service.deleteById(testUser3.getId());
		assertNull(result);
	}

	@Test
	public void c_readByIdValid() {
		User testUser = new User(null, "nom", "email@email12.fr", "pwd", null);
		testUser= service.create(testUser);
		
		result = service.findById(testUser.getId());
		assertNotNull(result.getId());
	}
	
	@Test
	public void c_readByIdNull() {
		User testUser = new User(null, "nom", "email@email13.fr", "pwd", null);
		testUser= service.create(testUser);
		
		result = service.findById(null);
		service.deleteById(testUser.getId());
		assertNull(result);
	}
	
	@Test
	public void c_readByIdZero() {
		User testUser = new User(null, "nom", "email@email14.fr", "pwd", null);
		testUser= service.create(testUser);
		
		result = service.findById(0L);
		service.deleteById(testUser.getId());
		assertNull(result);
	}
	
	@Test
	public void d_deleteValid() {
	a_createValidUser();
	Long id = result.getId();
	
	assertTrue(service.deleteById(id));
	result=null;
	}
	
	
	@Test
	public void db_deleteInvalid() {
	a_createValidUser();
	Long id = null;
		
	assertFalse(service.deleteById(id));
	result=null;
	}
	
//	@Test
//	public void b_deleteUser() {
//		boolean resultat = service.delete(result.getId());
//		assertTrue(resultat);
//	}
	
	
	
}


