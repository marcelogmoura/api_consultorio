package com.mgmoura.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.AtendimentosPostRequestDto;

@RestController
@RequestMapping("api/atendimentos")
public class AtendimentosController {
	
	@PostMapping
	public void post(@RequestBody AtendimentosPostRequestDto dto) {
		
	}
	
	@PutMapping
	public void put() {
		
	}
	
	@DeleteMapping
	public void delete() {
		
	}
	
	@GetMapping
	public void getAll() {
		
	}

}
