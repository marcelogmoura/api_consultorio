package com.mgmoura.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class PacientesPutRequestDto {
	
	private Integer idPaciente;
	private String nome;
	private Date dataNascimento;
	private String telefone;
	private Integer idAtendimento;

}
