package br.com.dentrio.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.comum.FormaPagamentoEnum;
import br.com.dentrio.model.Pagamento;
import br.com.dentrio.model.Tratamento;
import br.com.dentrio.pagamento.service.PagamentoService;
import br.com.dentrio.tratamento.service.TratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("pagamentoBean")
public class PagamentoBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	PagamentoService pagamentoService;
	@Autowired
	TratamentoService tratamentoService;

	private Pagamento pagamento;
	List<Pagamento> listaPagamentos;
	private String tratamentoId;

	@PostConstruct
	private void inicializar() {
		limpar();
	}

	public void limpar() {
		this.pagamento = new Pagamento();
	}

	public String novaPagamento() {
		limpar();
		return "formPagamento?faces-redirect=true";
	}

	public String salvarPagamento() {
		try {
			String tratamento_id = FacesUtil.getRequestParam("tratamento_id");
			Tratamento tratamento = tratamentoService.getTratamento(Integer.valueOf(tratamento_id));
			BigDecimal valorTotalPago = retornaValorTotalPago(tratamento);
			BigDecimal valorRestTrat = retornaValorRestanteTratamento(tratamento, valorTotalPago);
			int resultadoTotalTrat = pagamento.getValor().compareTo(tratamento.getValorTotal());
			int resultadoValorRestante = pagamento.getValor().compareTo(valorRestTrat);
			if (resultadoTotalTrat == 1) {
				FacesUtil.addErrorMessage(Constantes.ERRO,
						"O valor do pagamento é maior que o valor total do tratamento! Escolha um valor menor.");
				return null;
			} else if (resultadoValorRestante == 1) {
				FacesUtil.addErrorMessage(Constantes.ERRO,
						"O valor do pagamento é maior que o valor restante a ser pago! Escolha um valor menor.");
				return null;
			} else if ("0.00".equalsIgnoreCase(pagamento.getValor().toString())) {
				FacesUtil.addErrorMessage(Constantes.ERRO, "Você deve informar um valor para o pagamento.");
				return null;
			}
			setarTimestamps();
			pagamento.setTratamento(tratamento);
			pagamentoService.salvarPagamento(pagamento);
			atualizaStatusTratamento(tratamento.getId(), pagamento);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Pagamento adicionado com Sucesso!");
			inicializar();
			return "dadosTratamento?faces-redirect=true&tratamento_id=" + tratamento.getId();

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar!");
			return null;
		}
	}

	public String editarPagamento(Integer pagamentoId) {
		try {
			pagamento = pagamentoService.getPagamento(pagamentoId);
			return "formPagamento?faces-redirect=true&pagamentoId=" + pagamentoId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deletarPagamento(Integer pagamentoId) {
		try {
			Pagamento pagamento = pagamentoService.getPagamento(pagamentoId);
			pagamentoService.deletarPagamento(pagamento);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Pagamento deletado com Sucesso!");
			inicializar();
			return "listarPagamentos?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar!");
			return null;
		}
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getListaPagamentos() {
		return pagamentoService.listPagamentos();
	}

	public void setListaPagamentos(List<Pagamento> listaPagamentos) {
		this.listaPagamentos = listaPagamentos;
	}

	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	public void setarTimestamps() {
		if (pagamento.createdAt == null) {
			pagamento.setCreatedAt(new Date());
			pagamento.setUpdatedAt(new Date());
		}
		pagamento.setUpdatedAt(new Date());
	}

	/**
	 * @return the tratamentoId
	 */
	public String getTratamentoId() {
		return tratamentoId;
	}

	/**
	 * @param tratamentoId
	 *            the tratamentoId to set
	 */
	public void setTratamentoId(String tratamentoId) {
		this.tratamentoId = tratamentoId;
	}

	/**
	 * @return the tratamentoService
	 */
	@Override
	public TratamentoService getTratamentoService() {
		return tratamentoService;
	}

	/**
	 * @param tratamentoService
	 *            the tratamentoService to set
	 */
	@Override
	public void setTratamentoService(TratamentoService tratamentoService) {
		this.tratamentoService = tratamentoService;
	}

	/**
	 * @return the listaFormasPagamento
	 */
	public List<FormaPagamentoEnum> getListaFormasPagamento() {
		return FormaPagamentoEnum.listaTodos();
	}

}
