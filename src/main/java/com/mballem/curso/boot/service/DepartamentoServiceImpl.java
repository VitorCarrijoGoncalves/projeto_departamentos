package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.DepartamentoDao;
import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	private DepartamentoDao dao; 	

	@Override
	@Transactional(readOnly = false)
	public void salvar(Departamento departamento) {
		dao.save(departamento);
	}

	@Override
	@Transactional(readOnly = false)
	public void editar(Departamento departamento) {
		dao.update(departamento);
	}

	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public boolean departamentoTemCargos(Long id) {
		if (buscarPorId(id).getCargos().isEmpty()) {
			return false;			
		}
		return true;
	}
	
	@Override
	public PaginacaoUtil<Departamento> buscaPorPagina(int pagina, String direcao) {
		return dao.buscaPaginada(pagina, direcao);
	}

}
