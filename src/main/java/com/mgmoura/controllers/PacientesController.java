package com.mgmoura.controllers;

import java.text.SimpleDateFormat;
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

import com.mgmoura.dtos.PacientesPostRequestDto;
import com.mgmoura.dtos.PacientesPutRequestDto;
import com.mgmoura.dtos.PacientesResponseDto;
import com.mgmoura.entities.Paciente;
import com.mgmoura.repositories.PacienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public ResponseEntity<PacientesResponseDto> post(@RequestBody @Valid PacientesPostRequestDto dto) {
		
		PacientesResponseDto response = new PacientesResponseDto();
		
		try {
			
			Paciente paciente = new Paciente();
			paciente.setNome(dto.getNome());
			paciente.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataNascimento()));
			// paciente.setDataNascimento(dto.getDataNascimento());
			paciente.setTelefone(dto.getTelefone());
			
			pacienteRepository.save(paciente);
			
			response.setStatus(HttpStatus.CREATED); //201
			response.setMensagem("Paciente cadastrado com sucesso.");
			response.setPaciente(paciente);
					
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}
	
	@PutMapping
	public ResponseEntity<PacientesResponseDto> put(@RequestBody @Valid PacientesPutRequestDto dto) {
		
		PacientesResponseDto response = new PacientesResponseDto();
		
		try {
			Optional<Paciente> paciente = pacienteRepository.findById(dto.getIdPaciente());
						
			if(paciente.isEmpty()) {
				
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Paciente não encontrado");
				
			}else {
				
				Paciente item = paciente.get();
				item.setNome(dto.getNome());
				item.setTelefone(dto.getTelefone());
				item.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataNascimento()));
				//item.setDataNascimento(dto.getDataNascimento());
				
				pacienteRepository.save(item);
				
				response.setStatus(HttpStatus.OK);// 200
				response.setMensagem("Dados atualizados com sucesso.");
				response.setPaciente(item);
			} 
			
		}catch (Exception e) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // 500
				response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}
	
	@DeleteMapping("{idPaciente}")
	public ResponseEntity<PacientesResponseDto> delete(@PathVariable("idPaciente") Integer idPaciente) {
		
		PacientesResponseDto response = new PacientesResponseDto();
		
		try {
			
			Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
			
			if(paciente.isEmpty()) {
				response.setStatus(HttpStatus.BAD_REQUEST); // 400
				response.setMensagem("Paciente não encontrado.");
				
			}else {
				pacienteRepository.delete(paciente.get());
				
				response.setStatus(HttpStatus.OK); // 200
				response.setMensagem("Paciente excluído com sucesso.");
				response.setPaciente(paciente.get());
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // 500

		}
		return ResponseEntity.status(response.getStatus().value()).body(response);
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
