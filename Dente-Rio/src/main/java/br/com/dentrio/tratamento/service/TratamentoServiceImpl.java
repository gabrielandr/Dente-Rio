package br.com.dentrio.tratamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.Tratamento;
import br.com.dentrio.tratamento.dao.TratamentoDao;

@Service
public class TratamentoServiceImpl implements TratamentoService {

	@Autowired
	public TratamentoDao tratamentoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvarTratamento(Tratamento tratamento) {
		tratamentoDao.salvarTratamento(tratamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void editarTratamento(Tratamento tratamento) {
		tratamentoDao.editarTratamento(tratamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tratamento> listTratamentos() {
		return tratamentoDao.listTratamentos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tratamento getTratamento(Integer tratamentoId) {
		return tratamentoDao.getTratamento(tratamentoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarTratamento(Tratamento tratamento) {
		tratamentoDao.deletarTratamento(tratamento);
	}

	public TratamentoDao getTratamentoDao() {
		return tratamentoDao;
	}

	public void setTratamentoDao(TratamentoDao tratamentoDao) {
		this.tratamentoDao = tratamentoDao;
	}

	@Override
	public Tratamento getLastInsertedRecord() {
		return tratamentoDao.getLastInsertedRecord();
	}

}
