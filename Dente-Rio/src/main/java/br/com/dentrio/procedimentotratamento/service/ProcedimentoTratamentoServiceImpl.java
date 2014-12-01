package br.com.dentrio.procedimentotratamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.ProcedimentoTratamento;
import br.com.dentrio.procedimentotratamento.dao.ProcedimentoTratamentoDao;

@Service
public class ProcedimentoTratamentoServiceImpl implements ProcedimentoTratamentoService {

	@Autowired
	public ProcedimentoTratamentoDao procedimentoTratamentoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		procedimentoTratamentoDao.salvarProcedimentoTratamento(procedimentoTratamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void editarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		procedimentoTratamentoDao.editarProcedimentoTratamento(procedimentoTratamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProcedimentoTratamento> listProcedimentoTratamentos() {
		return procedimentoTratamentoDao.listProcedimentoTratamentos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcedimentoTratamento getProcedimentoTratamento(Integer procedimentoTratamentoId) {
		return procedimentoTratamentoDao.getProcedimentoTratamento(procedimentoTratamentoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		procedimentoTratamentoDao.deletarProcedimentoTratamento(procedimentoTratamento);
	}

	public ProcedimentoTratamentoDao getProcedimentoTratamentoDao() {
		return procedimentoTratamentoDao;
	}

	public void setProcedimentoTratamentoDao(ProcedimentoTratamentoDao procedimentoTratamentoDao) {
		this.procedimentoTratamentoDao = procedimentoTratamentoDao;
	}

}
