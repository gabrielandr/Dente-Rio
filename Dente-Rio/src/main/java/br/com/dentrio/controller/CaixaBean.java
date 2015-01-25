package br.com.dentrio.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.despesa.service.DespesaService;
import br.com.dentrio.model.Despesa;
import br.com.dentrio.model.Pagamento;
import br.com.dentrio.pagamento.service.PagamentoService;

@Component("caixaBean")
public class CaixaBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -8355672474994291811L;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private DespesaService despesaService;

	/**
	 * @return the pagamentoService
	 */
	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	/**
	 * @param pagamentoService the pagamentoService to set
	 */
	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	/**
	 * @return the despesaService
	 */
	public DespesaService getDespesaService() {
		return despesaService;
	}

	/**
	 * @param despesaService the despesaService to set
	 */
	public void setDespesaService(DespesaService despesaService) {
		this.despesaService = despesaService;
	}

	/**
	 * @return the listPagamentosDinheiro
	 */
	public List<Pagamento> getListPagamentosDinheiro() {
		return pagamentoService.listarPagamentosDinheiro();
	}


	/**
	 * @return the listPagamentosCartao
	 */
	public List<Pagamento> getListPagamentosCartao() {
		return pagamentoService.listarPagamentosCartao();
	}

	/**
	 * @return the listDespesasOutros
	 */
	public List<Despesa> getListDespesasOutros() {
		return despesaService.listarDespesasOutros();
	}


	/**
	 * @return the listDespesasOrtodontista
	 */
	public List<Despesa> getListDespesasOrtodontista() {
		return despesaService.listarDespesasOrtodontista();
	}

	/**
	 * @return the totalPagamentoDinheiro
	 */
	public BigDecimal getTotalPagamentoDinheiro() {
		BigDecimal totalPagamentoDinheiro = BigDecimal.ZERO;
		for (Pagamento pagamentoDinheiro : getListPagamentosDinheiro()) {
			totalPagamentoDinheiro = totalPagamentoDinheiro.add(pagamentoDinheiro.getValor());
		}
		return totalPagamentoDinheiro;
	}

	/**
	 * @return the totalPagamentoCartao
	 */
	public BigDecimal getTotalPagamentoCartao() {
		BigDecimal totalPagamentoCartao = BigDecimal.ZERO;
		for (Pagamento pagamentoCartao : getListPagamentosCartao()) {
			totalPagamentoCartao = totalPagamentoCartao.add(pagamentoCartao.getValor());
		}
		return totalPagamentoCartao;
	}

	/**
	 * @return the totalPagamentoDinheiro
	 */
	public BigDecimal getTotalDespesasOutros() {
		BigDecimal totalDespesasOutros = BigDecimal.ZERO;
		for (Despesa despesaOutro : getListDespesasOutros()) {
			totalDespesasOutros = totalDespesasOutros.add(despesaOutro.getValor());
		}
		return totalDespesasOutros;
	}

	/**
	 * @return the totalPagamentoDinheiro
	 */
	public BigDecimal getTotalDespesasOrto() {
		BigDecimal totalDespesasOrto = BigDecimal.ZERO;
		for (Despesa despesaOrtto : getListDespesasOrtodontista()) {
			totalDespesasOrto = totalDespesasOrto.add(despesaOrtto.getValor());
		}
		return totalDespesasOrto;
	}

}
