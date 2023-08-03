package com.mgmoura.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class AtendimentosPostRequestDto {
	
	private Integer idPaciente;
	private Integer idAtendimento;
	private String dataAtendimento;
	private String observacoes;

}
