package com.mgmoura;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiConsultorioApplicationTests {

	@Test
	@Order(1)
	void testePacientePost() {
		fail ("implementar");
	}
	
	@Test
	@Order(2)
	void testePacientePut() {
		fail ("implementar");
	}
	
	@Test
	@Order(3)
	void testePacienteGetAll() {
		fail ("implementar");
	}
	
	@Test
	@Order(4)
	void testePacienteGetById() {
		fail ("implementar");
	}
	
	@Test
	@Order(5)
	void testePacienteDelete() {
		fail ("implementar");
	}
	
	
	@Test
	@Order(6)
	void testeAtendimentoPost() {
		fail ("implementar");
	}
	
	@Test
	@Order(7)
	void testeAtendimentoGetAll() {
		fail ("implementar");
	}
	

	

}
