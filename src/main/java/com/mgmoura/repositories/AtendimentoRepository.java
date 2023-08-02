package com.mgmoura.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgmoura.entities.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{

	@Query("SELECT a FROM Atendimento a " +
		       "JOIN FETCH a.paciente p " +
		       "WHERE a.dataAtendimento >= :dataInicial " +
		       "AND a.dataAtendimento <= :dataFinal " +
		       "ORDER BY a.dataAtendimento DESC")
	
	List<Atendimento> findByDatas(
			@Param("dataInicial") Date dataMin, 
			@Param("dataFinal") Date dataMax);

}
