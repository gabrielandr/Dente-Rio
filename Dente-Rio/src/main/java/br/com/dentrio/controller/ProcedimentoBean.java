package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.Constantes;
import br.com.dentrio.comum.PosicaoDenteEnum;
import br.com.dentrio.model.Procedimento;
import br.com.dentrio.model.ProcedimentoTratamento;
import br.com.dentrio.procedimento.service.ProcedimentoService;
import br.com.dentrio.tratamento.service.TratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("procedimentoBean")
public class ProcedimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ERROR = "error";

	@Autowired
	ProcedimentoService procedimentoService;
	@Autowired
	TratamentoService tratamentoService;

	private Date data;
	private Procedimento procedimento;
	private Procedimento procedimentoSelecionado;
	private List<ProcedimentoTratamento> listaProcedimentosTratamentos;

	@PostConstruct
	private void inicializar() {
		limpar();
		// this.listaProcedimentos = null;
		// this.listaProcedimentos = procedimentoService.listProcedimentos();
	}

	public void limpar() {
		this.procedimento = new Procedimento();
	}

	public String novoProcedimento() {
		limpar();
		return "formProcedimento?faces-redirect=true";
	}

	public void atualizarListaProcedimentos() {
		getListaProcedimentos().remove(0);
	}

	public String salvarProcedimento() {
		try {
			setarTimestamps();
			procedimentoService.salvarProcedimento(procedimento);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Procedimento adicionado com Sucesso!");
			inicializar();
			return "listarProcedimentos?faces-redirect=true";

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
			return null;
		}
	}

	public String editarProcedimento(Procedimento proc) {
		try {
			procedimento = proc;
			return "formProcedimento?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String deletarProcedimento(Procedimento procedimento) {
		try {
			procedimentoService.deletarProcedimento(procedimento);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Procedimento deletado com Sucesso!");
			inicializar();
			return "listarProcedimentos?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar!");
			return null;
		}
	}

	public void resetForm() {
		procedimento = new Procedimento();
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Procedimento getProcedimentoSelecionado() {
		return procedimentoSelecionado;
	}

	public void setProcedimentoSelecionado(Procedimento procedimentoSelecionado) {
		this.procedimentoSelecionado = procedimentoSelecionado;
	}

	public ProcedimentoService getProcedimentoService() {
		return procedimentoService;
	}

	public void setProcedimentoService(ProcedimentoService procedimentoService) {
		this.procedimentoService = procedimentoService;
	}

	public void setarTimestamps() {
		if (procedimento.getCreatedAt() == null) {
			procedimento.setCreatedAt(new Date());
			procedimento.setUpdatedAt(new Date());
		}
		procedimento.setUpdatedAt(new Date());
	}

	/**
	 * @return the listaProcedimentosTratamentos
	 */
	public List<ProcedimentoTratamento> getListaProcedimentosTratamentos() {
		return listaProcedimentosTratamentos;
	}

	public void carregarListaProcedimentosTratamentos(Integer tratamentoId) {
		listaProcedimentosTratamentos = new ArrayList<ProcedimentoTratamento>();
		for (Procedimento proc : getListaProcedimentos()) {
			ProcedimentoTratamento procTrat = new ProcedimentoTratamento();
			procTrat.setProcedimento(proc);
			procTrat.setValorReal(proc.getValorProcedimento());
			procTrat.setPosicaoDente(null);
			listaProcedimentosTratamentos.add(procTrat);
		}
	}

	public List<PosicaoDenteEnum> getPosicaoDentes() {
		return PosicaoDenteEnum.listaTodos();
	}

	/**
	 * @param listaProcedimentosTratamentos
	 *            the listaProcedimentosTratamentos to set
	 */
	public void setListaProcedimentosTratamentos(List<ProcedimentoTratamento> listaProcedimentosTratamentos) {
		this.listaProcedimentosTratamentos = listaProcedimentosTratamentos;
	}

	/**
	 * @return the listaProcedimentos
	 */
	public List<Procedimento> getListaProcedimentos() {
		return procedimentoService.listProcedimentos();
	}

	/**
	 * @return the tratamentoService
	 */
	public TratamentoService getTratamentoService() {
		return tratamentoService;
	}

	/**
	 * @param tratamentoService
	 *            the tratamentoService to set
	 */
	public void setTratamentoService(TratamentoService tratamentoService) {
		this.tratamentoService = tratamentoService;
	}
}
