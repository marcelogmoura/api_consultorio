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

import com.mgmoura.dtos.AtendimentosPostRequestDto;
import com.mgmoura.repositories.AtendimentoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/atendimentos")
public class AtendimentosController {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@PostMapping
	public void post(@RequestBody @Valid AtendimentosPostRequestDto dto) {
		
	}
	
	@PutMapping  // @Valid nao esquecer
	public void put() {
		
	}
	
	@DeleteMapping
	public void delete() {
		
	}
	
	@GetMapping("{dataInicio}/{dataFim}")
	public void getAll(
			@PathVariable() String dataInicio,
			@PathVariable() String dataFim
			) {
		
	}

}
