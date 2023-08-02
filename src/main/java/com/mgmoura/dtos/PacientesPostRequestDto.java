package com.mgmoura.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacientesPostRequestDto {
	
	@NotBlank(message = "Informe o nome")
	private String nome;
	private Date dataNascimento;
	private String telefone;
	private Integer idAtendimento;
	
	

}
