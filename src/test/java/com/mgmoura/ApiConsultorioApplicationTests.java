package com.mgmoura;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.mgmoura.dtos.PacientesPostRequestDto;
import com.mgmoura.dtos.PacientesPutRequestDto;
import com.mgmoura.dtos.PacientesResponseDto;
import com.mgmoura.entities.Paciente;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiConsultorioApplicationTests {
	
	@Autowired // executar as requisições para a API
	private MockMvc mockMvc;

	@Autowired // serializar e deserializar os dados enviados para API
	private ObjectMapper mapper;
	
	private static Paciente paciente;  // atributo para guardar o produto cadastrado no teste

	
	@Test
	@Order(1)
	public void testePacientePost() throws Exception{
		
		Faker faker = new Faker();

		PacientesPostRequestDto dto = new PacientesPostRequestDto();
		
		dto.setNome(faker.name().fullName());
		dto.setTelefone(faker.number().digits(8));
		dto.setDataNascimento("2023-08-04");
		
		MvcResult result = mockMvc.perform(post("/api/pacientes")
				.contentType("application/json") 
				.content(mapper.writeValueAsString(dto))) 
				.andExpect(status().isCreated()) 
				.andReturn(); 

		String responseBody = result.getResponse().getContentAsString();
		PacientesResponseDto response = mapper.readValue(responseBody, PacientesResponseDto.class);
		
		paciente = response.getPaciente();
		
	}
	
	
	
	@Test
	@Order(2)
	public void testePacientePut() throws Exception{
		
		Faker faker = new Faker();
		
		PacientesPutRequestDto dto = new PacientesPutRequestDto();
		
		dto.setIdPaciente(paciente.getIdPaciente());
		dto.setNome(faker.name().fullName());
		dto.setTelefone(faker.number().digits(8));
		dto.setDataNascimento("2023-08-14");
		
		mockMvc.perform(put("/api/pacientes") 
				.contentType("application/json") 
				.content(mapper.writeValueAsString(dto))) 
				.andExpect(status().isOk());

	}
		
	
	@Test
	@Order(3)
	public void testePacienteGetAll() throws Exception{
		mockMvc.perform(get("/api/pacientes")).andExpect(status().isOk());

	}
		
	
	@Test
	@Order(4)
	public void testePacienteGetById() throws Exception {
		
		mockMvc.perform(get("/api/pacientes/" + paciente.getIdPaciente())).andExpect(status().isOk());
	}
	
	
	@Test
	@Order(5)
	void testeAtendimentoPost() {
		fail ("implementar");
	}
	
	@Test
	@Order(7)
	void testeAtendimentoGetAll() {
		fail ("implementar");
	}
	

	@Test
	@Order(7)
	public void testePacienteDelete() throws Exception{
		
		testePacientePost(); // novo paciente 
		mockMvc.perform(delete("/api/pacientes/" + paciente.getIdPaciente())).andExpect(status().isOk());

		
	}
	

}
