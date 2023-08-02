package com.mgmoura.dtos;

import org.springframework.http.HttpStatus;

import com.mgmoura.entities.Atendimento;

import lombok.Data;

@Data
public class AtendimentosResponseDto {
	
	private HttpStatus status;
	private String mensagem;
	private Atendimento atendimento;

}
