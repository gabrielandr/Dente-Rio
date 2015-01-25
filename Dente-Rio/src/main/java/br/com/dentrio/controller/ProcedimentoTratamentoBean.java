package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.model.Paciente;
import br.com.dentrio.model.Procedimento;
import br.com.dentrio.model.ProcedimentoTratamento;
import br.com.dentrio.procedimentotratamento.service.ProcedimentoTratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("procedimentoTratamentoBean")
public class ProcedimentoTratamentoBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProcedimentoTratamentoService procedimentoTratamentoService;

	private ProcedimentoTratamento procedimentoTratamento = new ProcedimentoTratamento();
	private Paciente paciente = new Paciente();
	private Procedimento procedimento;

	public String novoProcedimento() {
		return "formProcedimento?faces-redirect=true";
	}

	public String salvarProcedimentoTratamento() {
		try {
			setarTimestamps();
			procedimentoTratamentoService.salvarProcedimentoTratamento(procedimentoTratamento);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Procedimento adicionado com Sucesso!");
			return "listarProcedimentos?faces-redirect=true";

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
			return null;
		}
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	/**
	 * @return the procedimentoTratamentoService
	 */
	public ProcedimentoTratamentoService getProcedimentoTratamentoService() {
		return procedimentoTratamentoService;
	}

	/**
	 * @param procedimentoTratamentoService
	 *            the procedimentoTratamentoService to set
	 */
	public void setProcedimentoTratamentoService(ProcedimentoTratamentoService procedimentoTratamentoService) {
		this.procedimentoTratamentoService = procedimentoTratamentoService;
	}

	/**
	 * @return the procedimentoTratamento
	 */
	public ProcedimentoTratamento getProcedimentoTratamento() {
		return procedimentoTratamento;
	}

	/**
	 * @param procedimentoTratamento
	 *            the procedimentoTratamento to set
	 */
	public void setProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		this.procedimentoTratamento = procedimentoTratamento;
	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setarTimestamps() {
		if (procedimentoTratamento.getCreatedAt() == null) {
			procedimentoTratamento.setCreatedAt(new Date());
			procedimentoTratamento.setUpdatedAt(new Date());
		}
		procedimentoTratamento.setUpdatedAt(new Date());
	}
}
