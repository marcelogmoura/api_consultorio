package com.mgmoura.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class AtendimentosPostRequestDto {
	
	private Integer idPaciente;
	private Integer idAtendimento;
	private Date dataAtendimento;
	private String observacoes;

}
