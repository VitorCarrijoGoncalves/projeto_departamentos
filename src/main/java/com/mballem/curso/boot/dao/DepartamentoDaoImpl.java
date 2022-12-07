package com.mballem.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {
	
	public PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao) {
		int tamanho = 5;
		int inicio = (pagina - 1) * 5;
		List<Departamento> departamentos = getEntityManager()
				.createQuery("select d from Departamento d order by d.nome " + direcao, Departamento.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, departamentos);
	}
	
	public long count() {
		return getEntityManager()
				.createQuery("select count(*) from Departamento", Long.class)
				.getSingleResult();
	}

}
