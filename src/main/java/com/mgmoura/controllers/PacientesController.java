package com.mgmoura.controllers;

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

import com.mgmoura.dtos.PacientesPostRequestDto;
import com.mgmoura.dtos.PacientesPutRequestDto;
import com.mgmoura.entities.Paciente;
import com.mgmoura.repositories.PacienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid PacientesPostRequestDto dto) {
		
		try {
			
			Paciente paciente = new Paciente();
			paciente.setNome(dto.getNome());
			paciente.setDataNascimento(dto.getDataNascimento());
			paciente.setTelefone(dto.getTelefone());
			
			pacienteRepository.save(paciente);
			
			return ResponseEntity.status(201).body("Paciente cadastrado com sucesso.");
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("Erro ao cadastrar paciente" + e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid PacientesPutRequestDto dto) {
		try {
			Optional<Paciente> paciente = pacienteRepository.findById(dto.getIdPaciente());
			
			if(paciente.isEmpty()) {
				return ResponseEntity.status(400).body("Paciente não encontrado");
				
			}else {
				
				Paciente item = paciente.get();
				item.setNome(dto.getNome());
				item.setTelefone(dto.getTelefone());
				item.setDataNascimento(dto.getDataNascimento());
				
				pacienteRepository.save(item);
				
				return ResponseEntity.status(200).body("Dados atualizados com sucesso.");
			} 
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body("Falha ao atualizar: " + e.getMessage());
		}
		
	}
	
	@DeleteMapping("{idPaciente}")
	public ResponseEntity<String> delete(@PathVariable("idPaciente") Integer idPaciente) {
		
		try {
			
			Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
			
			if(paciente.isEmpty()) {
				return ResponseEntity.status(400).body("Paciente não encontrado.");
			}else {
				pacienteRepository.delete(paciente.get());
				return ResponseEntity.status(200).body("Paciente excluído com sucesso");
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500)
					.body("Falha ao excluir: " 	+ e.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> getAll() {
		
		try {
			List<Paciente> pacientes = pacienteRepository.findAll();
			return ResponseEntity.status(200).body(pacientes);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	@GetMapping("{idPaciente}")
	public ResponseEntity<Paciente> getById(@PathVariable("idPaciente") Integer idPaciente) {
		
		try {
			Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
			
			if(paciente.isPresent()) {
				return ResponseEntity.status(200).body(paciente.get());
				
			}else {
				return ResponseEntity.status(204).body(null);
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
	}

}
