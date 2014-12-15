package br.com.dentrio.despesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.despesa.dao.DespesaDao;
import br.com.dentrio.model.Despesa;

@Service
public class DespesaServiceImpl implements DespesaService {

	@Autowired
	public DespesaDao despesaDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addDespesa(Despesa despesa) {
		despesaDao.addDespesa(despesa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Despesa> listDespesas() {
		return despesaDao.listDespesas();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Despesa getDespesa(Integer despesaId) {
		return despesaDao.getDespesa(despesaId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarDespesa(Despesa despesa) {
		despesaDao.deletarDespesa(despesa);

	}

	public DespesaDao getDespesaDao() {
		return despesaDao;
	}

	public void setDespesaDao(DespesaDao despesaDao) {
		this.despesaDao = despesaDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Despesa> listarDespesasOutros() {
		return despesaDao.listarDespesasOutros();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Despesa> listarDespesasOrtodontista() {
		return despesaDao.listarDespesasOrtodontista();
	}

}
