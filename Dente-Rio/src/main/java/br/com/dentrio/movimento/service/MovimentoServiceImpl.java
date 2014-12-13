package br.com.dentrio.movimento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.Movimento;
import br.com.dentrio.movimento.dao.MovimentoDao;

@Service
public class MovimentoServiceImpl implements MovimentoService {

	@Autowired
	public MovimentoDao movimentoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void estornarMovimento(Integer movimentoId) {
		movimentoDao.estornarMovimento(movimentoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Movimento> listMovimentos() {
		return movimentoDao.listMovimentos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Movimento getMovimento(Integer movimentoId) {
		return movimentoDao.getMovimento(movimentoId);
	}

	public MovimentoDao getMovimentoDao() {
		return movimentoDao;
	}

	public void setMovimentoDao(MovimentoDao movimentoDao) {
		this.movimentoDao = movimentoDao;
	}

	@Override
	public Movimento getLastInsertedRecord() {
		return movimentoDao.getLastInsertedRecord();
	}

}
