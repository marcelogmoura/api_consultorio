package com.mgmoura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.PacientesPostRequestDto;
import com.mgmoura.dtos.PacientesPutRequestDto;
import com.mgmoura.repositories.PacienteRepository;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public void post(@RequestBody PacientesPostRequestDto dto) {
		
	}
	
	@PutMapping
	public void put(@RequestBody PacientesPutRequestDto dto) {
		
	}
	
	@DeleteMapping("{idPaciente}")
	public void delete(@PathVariable("idPaciente") Integer idPaciente) {
		
	}
	
	@GetMapping
	public void getAll() {
		
	}
	
	@GetMapping("{idPaciente}")
	public void getById(@PathVariable("idPaciente") Integer idPaciente) {
		
	}

}
