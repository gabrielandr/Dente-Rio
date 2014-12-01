package br.com.dentrio.procedimento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.Procedimento;
import br.com.dentrio.procedimento.dao.ProcedimentoDao;

@Service
public class ProcedimentoServiceImpl implements ProcedimentoService {

	@Autowired
	public ProcedimentoDao procedimentoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvarProcedimento(Procedimento area) {
		procedimentoDao.salvarProcedimento(area);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Procedimento> listProcedimentos() {
		return procedimentoDao.listProcedimentos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Procedimento getProcedimento(Integer areaId) {
		return procedimentoDao.getProcedimento(areaId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarProcedimento(Procedimento area) {
		procedimentoDao.deletarProcedimento(area);

	}

	public ProcedimentoDao getProcedimentoDao() {
		return procedimentoDao;
	}

	public void setProcedimentoDao(ProcedimentoDao areaDao) {
		this.procedimentoDao = areaDao;
	}
}
