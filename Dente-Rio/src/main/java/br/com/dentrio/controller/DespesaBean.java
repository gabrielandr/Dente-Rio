package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.TipoDespesaEnum;
import br.com.dentrio.despesa.service.DespesaService;
import br.com.dentrio.model.Despesa;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("despesaBean")
public class DespesaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	DespesaService despesaService;
	private Date data;
	private Despesa despesa;
	private Despesa despesaSelecionado;
	List<Despesa> listaDespesas;

	@PostConstruct
	private void inicializar() {
		limpar();
		this.listaDespesas = null;
		this.listaDespesas = this.despesaService.listDespesas();
	}

	public void limpar() {
		this.despesa = new Despesa();
	}

	public String novaDespesa() {
		limpar();
		return "formDespesa?faces-redirect=true";
	}

	public String salvarDespesa() {
		try {
			setarTimestamps();
			this.despesaService.addDespesa(this.despesa);
			FacesUtil.addSuccessMessage("Sucesso!", "Despesa adicionada com Sucesso!");
			inicializar();
			return "listarDespesas?faces-redirect=true";
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
		}
		return null;
	}

	public String editarDespesa(Integer despesaId) {
		try {
			this.despesa = this.despesaService.getDespesa(despesaId);
			return "formDespesa?faces-redirect=true&despesaId=" + despesaId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deletarDespesa(Integer despesaId) {
		try {
			Despesa despesa = this.despesaService.getDespesa(despesaId);
			this.despesaService.deletarDespesa(despesa);
			FacesUtil.addSuccessMessage("Sucesso!", "Despesa deletada com Sucesso!");
			inicializar();
			return "listarDespesas?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Ocorreu um erro ao deletar!");
		}
		return null;
	}

	public void resetForm() {
		this.despesa = new Despesa();
	}

	public Despesa getDespesa() {
		return this.despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Despesa> getListaDespesas() {
		return this.listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public Despesa getDespesaSelecionado() {
		return this.despesaSelecionado;
	}

	public void setDespesaSelecionado(Despesa despesaSelecionado) {
		this.despesaSelecionado = despesaSelecionado;
	}

	public DespesaService getDespesaService() {
		return this.despesaService;
	}

	public void setDespesaService(DespesaService despesaService) {
		this.despesaService = despesaService;
	}

	public void setarTimestamps() {
		if (this.despesa.createdAt == null) {
			this.despesa.setCreatedAt(new Date());
			this.despesa.setUpdatedAt(new Date());
		}
		this.despesa.setUpdatedAt(new Date());
	}

	public List<TipoDespesaEnum> getListaTiposDespesa() {
		return TipoDespesaEnum.listaTodos();
	}

}
