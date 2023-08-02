package com.mgmoura.dtos;

import org.springframework.http.HttpStatus;

import com.mgmoura.entities.Paciente;

import lombok.Data;

@Data
public class PacientesResponseDto {
	
	private HttpStatus status;
	private String mensagem;
	private Paciente paciente;


}
