package br.com.dentrio.pagamento.service;

import java.util.List;

import br.com.dentrio.model.Pagamento;

public interface PagamentoService {

	public void salvarPagamento(Pagamento pagamento);

	public List<Pagamento> listPagamentos();

	public Pagamento getPagamento(Integer pagamentoId);

	public void deletarPagamento(Pagamento pagamento);

	public Pagamento getLastInsertedRecord();

}
