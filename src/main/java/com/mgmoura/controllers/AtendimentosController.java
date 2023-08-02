package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<String> post(@RequestBody @Valid AtendimentosPostRequestDto dto) {
		
		try {
			Optional<Paciente> paciente = pacienteRepository.findById(dto.getIdPaciente());
			
			if(paciente.isEmpty()) {
				return ResponseEntity.status(400).body("Paciente não localizado.");
				
			}else {
				Atendimento atendimento = new Atendimento();
				atendimento.setPaciente(paciente.get());
				atendimento.setDataAtendimento(dto.getDataAtendimento());
				atendimento.setObservacoes(dto.getObservacoes());
				
				atendimentoRepository.save(atendimento);
				
				return ResponseEntity.status(201).body("Atendimento cadastrado com sucesso.");
				
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("Falha ao cadastrar: " +e.getMessage());
			
		}
	}
	
	@PutMapping  // @Valid nao esquecer
	public void put() {
		
		
	}
	
	@DeleteMapping
	public void delete() {
		
		
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
