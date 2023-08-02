package com.mgmoura.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAtendimento")
	private Integer idAtendimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataAtendimento" , nullable = false)
	private Date dataAtendimento;
	
	@Column(name = "observacoes", length = 140 , nullable = false)
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "idPaciente")
	private Paciente paciente;

}
