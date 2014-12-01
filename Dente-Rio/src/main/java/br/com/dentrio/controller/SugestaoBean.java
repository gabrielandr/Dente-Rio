package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.Constantes;
import br.com.dentrio.model.Sugestao;
import br.com.dentrio.sugestao.service.SugestaoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("sugestaoBean")
public class SugestaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ERROR = "error";

	@Autowired
	SugestaoService sugestaoService;

	private Sugestao sugestao;
	List<Sugestao> listaSugestoes;

	@PostConstruct
	private void inicializar() {
		limpar();
		this.listaSugestoes = null;
		this.listaSugestoes = sugestaoService.listSugestoes();
	}

	public void limpar() {
		this.sugestao = new Sugestao();
	}

	public String novaSugestao() {
		limpar();
		return "formSugestao?faces-redirect=true";
	}

	public String salvarSugestao() {
		try {
			setarTimestamps();
			sugestaoService.addSugestao(sugestao);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Sugestao adicionada com Sucesso!");
			inicializar();
			return "listarSugestoes?faces-redirect=true";

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
			return null;
		}
	}

	public String editarSugestao(Integer sugestaoId) {
		try {
			sugestao = sugestaoService.getSugestao(sugestaoId);
			return "formSugestao?faces-redirect=true&sugestaoId=" + sugestaoId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String deletarSugestao(Integer sugestaoId) {
		try {
			Sugestao sugestao = sugestaoService.getSugestao(sugestaoId);
			sugestaoService.deletarSugestao(sugestao);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Sugestao deletada com Sucesso!");
			inicializar();
			return "listarSugestoes?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar!");
			return null;
		}
	}

	public void resetForm() {
		sugestao = new Sugestao();
	}

	public Sugestao getSugestao() {
		return sugestao;
	}

	public void setSugestao(Sugestao sugestao) {
		this.sugestao = sugestao;
	}

	public List<Sugestao> getListaSugestoes() {
		return listaSugestoes;
	}

	public void setListaSugestoes(List<Sugestao> listaSugestoes) {
		this.listaSugestoes = listaSugestoes;
	}

	public SugestaoService getSugestaoService() {
		return sugestaoService;
	}

	public void setSugestaoService(SugestaoService sugestaoService) {
		this.sugestaoService = sugestaoService;
	}

	public void setarTimestamps() {
		if (sugestao.createdAt == null) {
			sugestao.setCreatedAt(new Date());
			sugestao.setUpdatedAt(new Date());
		}
		sugestao.setUpdatedAt(new Date());
	}

}
