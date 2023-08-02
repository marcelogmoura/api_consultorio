package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.AtendimentosPostRequestDto;
import com.mgmoura.dtos.AtendimentosResponseDto;
import com.mgmoura.entities.Atendimento;
import com.mgmoura.entities.Paciente;
import com.mgmoura.repositories.AtendimentoRepository;
import com.mgmoura.repositories.PacienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/atendimentos")
public class AtendimentosController {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public ResponseEntity<AtendimentosResponseDto> post(@RequestBody @Valid AtendimentosPostRequestDto dto) {
		
		AtendimentosResponseDto response = new AtendimentosResponseDto();
		
		try {
			Optional<Paciente> paciente = pacienteRepository.findById(dto.getIdPaciente());
			
			if(paciente.isEmpty()) {
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Paciente não localizado.");
				
				
			}else {
				Atendimento atendimento = new Atendimento();
				atendimento.setPaciente(paciente.get());
				atendimento.setDataAtendimento(dto.getDataAtendimento());
				atendimento.setObservacoes(dto.getObservacoes());
				
				atendimentoRepository.save(atendimento);
				
				response.setStatus(HttpStatus.CREATED); // 201
				response.setMensagem("Atendimento cadastrado com sucesso");
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); 
			response.setMensagem(e.getMessage());
			
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}
	
	@PutMapping  // @Valid nao esquecer
	public void put() {
		
		
	}
	
	@DeleteMapping
	public void delete() {
		
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<Atendimento>> getAll() {
		try {
			List<Atendimento> atendimentos = atendimentoRepository.findAll();
			return ResponseEntity.status(200).body(atendimentos);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
		
	}
	
	
	
	@GetMapping("{dataInicio}/{dataFim}")
	public ResponseEntity<List<Atendimento>> getAll(
			@PathVariable() String dataInicio,
			@PathVariable() String dataFim) {
		
		try {
			
			Date dataMin = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
			Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
			
			List<Atendimento> atendimentos = atendimentoRepository.findByDatas(dataMin, dataMax);
			
			return ResponseEntity.status(200).body(atendimentos);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
	}

}
