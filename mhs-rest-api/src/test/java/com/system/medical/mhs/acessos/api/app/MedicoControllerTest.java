package com.system.medical.mhs.acessos.api.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("development")
public class MedicoControllerTest {
	@Autowired
	private MedicoController controller;
	
	@Test
	public void getMedicosDetalhesShouldReturn200CodeWithValidCrmAndUf(){
		ResponseEntity response = controller.buscarDetalhes(123456L, "SP", 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getMedicosDetalhesShouldReturn400CodeWithInvalidCrm(){
		ResponseEntity response = controller.buscarDetalhes(null, "", null);
		assertNotEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getMedicosDetalhesShouldReturn400CodeWithInvalidUf(){
		ResponseEntity response = controller.buscarDetalhes(123456L, null, 1L);
		assertNotEquals(HttpStatus.OK, response.getStatusCode());		
	}
	
}
