package br.com.dentrio.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELException;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.Constantes;
import br.com.dentrio.funcionario.service.FuncionarioService;
import br.com.dentrio.model.Funcionario;
import br.com.dentrio.model.Paciente;
import br.com.dentrio.model.ProcedimentoTratamento;
import br.com.dentrio.model.Tratamento;
import br.com.dentrio.procedimentotratamento.service.ProcedimentoTratamentoService;
import br.com.dentrio.tratamento.service.TratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("tratamentoBean")
public class TratamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ERROR = "error";

	@Autowired
	TratamentoService tratamentoService;
	@Autowired
	ProcedimentoTratamentoService procedimentoTratamentoService;
	@Autowired
	FuncionarioService funcionarioService;

	private Tratamento tratamento;
	private Tratamento tratamentoSelecionado;
	private List<ProcedimentoTratamento> procedimentosSelecionados;
	List<Tratamento> listaTratamentos;
	List<ProcedimentoTratamento> listaProcedimentos;
	private ProcedimentoTratamento procedimentoTratamento = new ProcedimentoTratamento();
	private Integer func_id;
	private List<String> listaProcedimentosDuplicados = new ArrayList<String>();
	private BigDecimal valorTotalTrat;

	@PostConstruct
	private void inicializar() {
		limpar();
	}

	public void limpar() {
		this.tratamento = new Tratamento();
		this.tratamento.setDataInicio(new Date());
	}

	public String novaTratamento() {
		limpar();
		return "formTratamento?faces-redirect=true";
	}

	public void cadastrarNovoTratamento() {
		RequestContext.getCurrentInstance().openDialog("tratamento");
	}

	public String salvarTratamento(Paciente paciente) {
		try {
			setarTimestamps();
			tratamento.setPaciente(paciente);
			tratamento.setFuncionario(funcionarioService.getFuncionario(func_id));
			tratamentoService.salvarTratamento(tratamento);
			Tratamento trat = tratamentoService.getLastInsertedRecord();
			salvarProcedimentosTratamento(trat);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Tratamento adicionado com Sucesso!");
			inicializar();
			return "dadosTratamento?faces-redirect=true&tratamento_id=" + trat.getId();

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
			return null;
		}
	}

	public String editarTratamento(Integer tratamentoId) {
		try {
			tratamento = tratamentoService.getTratamento(tratamentoId);
			return "formTratamento?faces-redirect=true&tratamentoId=" + tratamentoId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public void mostrarDadosTratamento() {
		try {
			// redireciona para a pagina pages/paciente/dadosTratamento.xhtml
			FacesUtil.redirect("dadosTratamento.xhtml?tratamento_id=" + getTratamentoSelecionado().getId());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao pesquisar o tratamento!");
			FacesUtil.redirect(null);
		}
	}

	public String deletarTratamento(Integer tratamentoId) {
		try {
			Tratamento tratamento = tratamentoService.getTratamento(tratamentoId);
			tratamentoService.deletarTratamento(tratamento);
			FacesUtil.addErrorMessage(Constantes.SUCESSO, "Tratamento deletada com Sucesso!");
			inicializar();
			return "listarTratamentos?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar!");
			return null;
		}
	}

	public void buscarTatamento(Integer tratam_id) {
		tratamento = tratamentoService.getTratamento(tratam_id);
	}

	public void gravarProcedimentosTratamento(String codigo_tratamento) {
		if ("".equalsIgnoreCase(codigo_tratamento) || null == codigo_tratamento) {
			codigo_tratamento = FacesUtil.getRequestParam("tratamentoId");
		}

		Boolean duplicateEntry = false;
		try {
			duplicateEntry = checarDuplicateEntry(duplicateEntry);

			if (duplicateEntry) {
				setarMensagemDuplicidade();
				setProcedimentosSelecionados(new ArrayList<ProcedimentoTratamento>());
				FacesUtil.redirect(null);
			} else {
				Tratamento trata = tratamentoService.getTratamento(Integer.valueOf(codigo_tratamento));
				salvarProcedimentosTratamento(trata);
			}
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Procedimento adicionado com Sucesso!");
			FacesUtil.redirect("dadosTratamento.xhtml?tratamento_id=" + tratamento.getId());

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
			FacesUtil.redirect(null);
			;
		}
	}

	private void setarMensagemDuplicidade() {
		if (listaProcedimentosDuplicados.size() > 1) {
			String stringProcedimentosDuplicados = StringUtils.join(listaProcedimentosDuplicados, ',');
			FacesUtil.addErrorMessage(Constantes.ERRO, "Os procedimentos (" + stringProcedimentosDuplicados
					+ ") já foram cadastrados para este tratamento, selecione outro(os) procedimentos!");
		} else {
			FacesUtil.addErrorMessage(Constantes.ERRO, "O procedimento de # " + listaProcedimentosDuplicados.get(0)
					+ " já foi cadastrado para este tratamento, selecione outro procedimento!");
		}
	}

	private void salvarProcedimentosTratamento(Tratamento trat) {
		valorTotalTrat = BigDecimal.ZERO;
		for (ProcedimentoTratamento procedimentoTrat : getProcedimentosSelecionados()) {
			procedimentoTrat.setTratamento(trat);
			setarTimestampsProcedimentoTratamento(procedimentoTrat);
			procedimentoTratamentoService.salvarProcedimentoTratamento(procedimentoTrat);
			setProcedimentosSelecionados(new ArrayList<ProcedimentoTratamento>());
		}
		atualizaValorTotalTratamento(trat.getId());
	}

	private Boolean checarDuplicateEntry(Boolean duplicateEntry) {

		for (ProcedimentoTratamento procedimentoTratSelecionado : getProcedimentosSelecionados()) {
			for (ProcedimentoTratamento procedimentoTratamento : tratamento.getProcedimentoTratamentos()) {
				if (procedimentoTratSelecionado.equals(procedimentoTratamento)) {
					duplicateEntry = true;
					listaProcedimentosDuplicados.add(procedimentoTratSelecionado.getProcedimento()
							.getCodigoProcedimento().toString());
				}
			}
		}
		return duplicateEntry;
	}

	/*
	 * Método que atualiza o valor total do tratamento, somando os valores reais
	 * dos procedimentos cadastrados.
	 */
	private void atualizaValorTotalTratamento(Integer trat_id) {
		Tratamento trat = tratamentoService.getTratamento(trat_id);
		setarValorTotalTratamento(trat);
		tratamento.setValorTotal(valorTotalTrat);
		tratamentoService.salvarTratamento(tratamento);

		try {
			tratamentoService.salvarTratamento(tratamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setarValorTotalTratamento(Tratamento trat) {
		for (ProcedimentoTratamento procTrat : trat.getProcedimentoTratamentos()) {
			valorTotalTrat = valorTotalTrat.add(procTrat.getValorReal());
		}
	}

	public String retornaStatusTratamento(Tratamento trat) {
		if (trat.getProcedimentoTratamentos().isEmpty()) {
			return "Nenhum Procedimento cadastrado ainda";
		} else {
			return "Tratamento Orçado";
		}
	}

	/**
	 * Edita a celula editada, neste caso o Valor Real do Procedimento
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void onCellEdit(CellEditEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Object valorAnterior;
		Object valorNovo;
		valorTotalTrat = BigDecimal.ZERO;
		try {
			// pega o objeto do contexto, a celula que está sendo editada no
			// momento
			ProcedimentoTratamento procedimentoTrat = context.getApplication().evaluateExpressionGet(context,
					"#{procedimentoTratamento}", ProcedimentoTratamento.class);
			valorAnterior = event.getOldValue();
			valorNovo = event.getNewValue();
			if ((valorNovo != null) && !valorNovo.equals(valorAnterior)) {
				procedimentoTrat.setValorReal((BigDecimal) valorNovo);
				procedimentoTratamentoService.salvarProcedimentoTratamento(procedimentoTrat);
				atualizaValorTotalTratamento(procedimentoTrat.getTratamento().getId());
				FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Valor do tratamento alterado de R$" + valorAnterior
						+ " para R$" + valorNovo);
				FacesUtil.redirect("dadosTratamento.xhtml?tratamento_id=" + tratamento.getId());
			}
		} catch (ELException e) {
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao salvar o valor.");
			e.printStackTrace();
		}

	}

	/* Getters and setters */

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public TratamentoService getTratamentoService() {
		return tratamentoService;
	}

	public void setTratamentoService(TratamentoService tratamentoService) {
		this.tratamentoService = tratamentoService;
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
	 * @return the listaProcedimentos
	 */
	public List<ProcedimentoTratamento> getListaProcedimentos() {
		return procedimentoTratamentoService.listProcedimentoTratamentos();
	}

	/**
	 * @param listaProcedimentos
	 *            the listaProcedimentos to set
	 */
	public void setListaProcedimentos(List<ProcedimentoTratamento> listaProcedimentos) {
		this.listaProcedimentos = listaProcedimentos;
	}

	public void setarTimestamps() {
		if (tratamento.getCreatedAt() == null) {
			tratamento.setCreatedAt(new Date());
			tratamento.setUpdatedAt(new Date());
		}
		tratamento.setUpdatedAt(new Date());
	}

	public void setarTimestampsProcedimentoTratamento(ProcedimentoTratamento procTrat) {
		if (procTrat.getCreatedAt() == null) {
			procTrat.setCreatedAt(new Date());
			procTrat.setUpdatedAt(new Date());
		}
		procTrat.setUpdatedAt(new Date());
	}

	/**
	 * @return the listaTratamentos
	 */
	public List<Tratamento> getListaTratamentos() {
		return listaTratamentos;
	}

	/**
	 * @param listaTratamentos
	 *            the listaTratamentos to set
	 */
	public void setListaTratamentos(List<Tratamento> listaTratamentos) {
		this.listaTratamentos = listaTratamentos;
	}

	/**
	 * @return the listaFuncionarios
	 */
	public List<Funcionario> getListaFuncionarios() {
		return funcionarioService.listFuncionarios();
	}

	/**
	 * @return the funcionarioService
	 */
	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	/**
	 * @param funcionarioService
	 *            the funcionarioService to set
	 */
	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
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
	 * @return the func_id
	 */
	public Integer getFunc_id() {
		return func_id;
	}

	/**
	 * @param func_id
	 *            the func_id to set
	 */
	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}

	/**
	 * @return the procedimentosSelecionados
	 */
	public List<ProcedimentoTratamento> getProcedimentosSelecionados() {
		return procedimentosSelecionados;
	}

	/**
	 * @param procedimentosSelecionados
	 *            the procedimentosSelecionados to set
	 */
	public void setProcedimentosSelecionados(List<ProcedimentoTratamento> procedimentosSelecionados) {
		this.procedimentosSelecionados = procedimentosSelecionados;
	}

	/**
	 * @return the tratamentoSelecionado
	 */
	public Tratamento getTratamentoSelecionado() {
		return tratamentoSelecionado;
	}

	/**
	 * @param tratamentoSelecionado
	 *            the tratamentoSelecionado to set
	 */
	public void setTratamentoSelecionado(Tratamento tratamentoSelecionado) {
		this.tratamentoSelecionado = tratamentoSelecionado;
	}

	/**
	 * @return the listaTratamentosDuplicados
	 */
	public List<String> getListaTratamentosDuplicados() {
		return listaProcedimentosDuplicados;
	}

	/**
	 * @param listaTratamentosDuplicados
	 *            the listaTratamentosDuplicados to set
	 */
	public void setListaTratamentosDuplicados(List<String> listaTratamentosDuplicados) {
		this.listaProcedimentosDuplicados = listaTratamentosDuplicados;
	}

	/**
	 * @return the valorTotalTrat
	 */
	public BigDecimal getValorTotalTrat() {
		return valorTotalTrat;
	}

	/**
	 * @param valorTotalTrat
	 *            the valorTotalTrat to set
	 */
	public void setValorTotalTrat(BigDecimal valorTotalTrat) {
		this.valorTotalTrat = valorTotalTrat;
	}
}
