package br.com.dentrio.pagamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.Pagamento;
import br.com.dentrio.pagamento.dao.PagamentoDao;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	@Autowired
	public PagamentoDao pagamentoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvarPagamento(Pagamento pagamento) {
		pagamentoDao.salvarPagamento(pagamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pagamento> listPagamentos() {
		return pagamentoDao.listPagamentos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pagamento getPagamento(Integer pagamentoId) {
		return pagamentoDao.getPagamento(pagamentoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarPagamento(Pagamento pagamento) {
		pagamentoDao.deletarPagamento(pagamento);
	}

	public PagamentoDao getPagamentoDao() {
		return pagamentoDao;
	}

	public void setPagamentoDao(PagamentoDao pagamentoDao) {
		this.pagamentoDao = pagamentoDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pagamento getLastInsertedRecord() {
		return pagamentoDao.getLastInsertedRecord();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pagamento> listarPagamentosDinheiro() {
		return pagamentoDao.listarPagamentosDinheiro();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pagamento> listarPagamentosCartao() {
		return pagamentoDao.listarPagamentosCartao();
	}

	@Override
	public List<Pagamento> filtrarPagamentosCartao(String dataInicio, String dataFim) {
		return pagamentoDao.filtrarPagamentosCartao(dataInicio, dataFim);
	}

	@Override
	public List<Pagamento> filtrarPagamentosDinheiro(String dataIni, String dataFinal) {
		return pagamentoDao.filtrarPagamentosDinheiro(dataIni, dataFinal);
	}

}
