package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.model.Pagamento;
import br.com.dentrio.model.Tratamento;
import br.com.dentrio.pagamento.service.PagamentoService;
import br.com.dentrio.tratamento.service.TratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("pagamentoBean")
public class PagamentoBean implements Serializable {
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

	public void salvarPagamento() {
		try {
			String tratamento_id = FacesUtil.getRequestParam("tratamentoId");
			Tratamento tratamento = this.tratamentoService.getTratamento(Integer.valueOf(tratamento_id));
			setarTimestamps();
			this.pagamento.setTratamento(tratamento);
			this.pagamentoService.salvarPagamento(this.pagamento);
			FacesUtil.addSuccessMessage("Sucesso!", "Pagamento adicionado com Sucesso!");
			inicializar();
			FacesUtil.redirect("dadosTratamento.xhtml?tratamento_id=" + tratamento.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Ocoreu um erro ao tentar salvar!");
		}
	}

	public String editarPagamento(Integer pagamentoId) {
		try {
			this.pagamento = this.pagamentoService.getPagamento(pagamentoId);
			return "formPagamento?faces-redirect=true&pagamentoId=" + pagamentoId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	public String deletarPagamento(Integer pagamentoId) {
		try {
			Pagamento pagamento = this.pagamentoService.getPagamento(pagamentoId);
			this.pagamentoService.deletarPagamento(pagamento);
			FacesUtil.addSuccessMessage("Sucesso!", "Pagamento deletado com Sucesso!");
			inicializar();
			return "listarPagamentos?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Ocorreu um erro ao deletar!");
		}
		return null;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getListaPagamentos() {
		return this.pagamentoService.listPagamentos();
	}

	public void setListaPagamentos(List<Pagamento> listaPagamentos) {
		this.listaPagamentos = listaPagamentos;
	}

	public PagamentoService getPagamentoService() {
		return this.pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	public void setarTimestamps() {
		if (this.pagamento.createdAt == null) {
			this.pagamento.setCreatedAt(new Date());
			this.pagamento.setUpdatedAt(new Date());
		}
		this.pagamento.setUpdatedAt(new Date());
	}

	public String getTratamentoId() {
		return this.tratamentoId;
	}

	public void setTratamentoId(String tratamentoId) {
		this.tratamentoId = tratamentoId;
	}

	public TratamentoService getTratamentoService() {
		return this.tratamentoService;
	}

	public void setTratamentoService(TratamentoService tratamentoService) {
		this.tratamentoService = tratamentoService;
	}
}
