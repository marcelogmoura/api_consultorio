package com.mgmoura.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacientesPutRequestDto {
	
	private Integer idPaciente;
	
	@NotBlank(message = "Informe o nome")
	private String nome;
	
	private String dataNascimento;
	private String telefone;
	private Integer idAtendimento;

}
