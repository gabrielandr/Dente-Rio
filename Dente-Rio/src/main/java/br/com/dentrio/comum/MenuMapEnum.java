package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum MenuMapEnum {

	LINK_HOME("linkHome", "menuHome", "/Home.xhtml"),
	/* Menu Paciente */
	LINK_PACIENTE("linkPaciente", "menuPaciente", "../funcionario/listarFuncionarios.xhtml"),
	LINK_AGENDAMENTO("linkAgendamento", "menuPaciente", "../consulta/formConsulta.xhtml"),
	/* Fim Menu Paciente */
	/* Menu de Movimento */
	LINK_DESPESA("linkDespesa", "menuMovimento", "../despesa/listarDespesas.xhtml"),
	// LINK_PAGAMENTO("linkPagamento", "menuMovimento",
	// "../pagamento/listarDespesas.xhtml"),
	/* Fim Movimento */
	LINK_CAIXA("linkCaixa", "menuMovimento", "../caixa/exibirInfoCaixa.xhtml"),
	LINK_FUNCIONARIO("linkFuncionario", "menuCadastros", "../funcionario/listarFuncionarios.xhtml"),
	LINK_AREA_ESPECIALIZADA("linkAreaEspecializada", "menuCadastros",
			"../areaEspecializada/listarAreasEspecializadas.xhtml"),
			LINK_PROCEDIMENTO("linkProcedimento", "menuCadastros", "../procedimento/listarProcedimentos.xhtml"),
			/* Fim Menu de Cadastros */
	LINK_SUGESTAO("linkSugestao", "menuSugestao", "../sugestao/listarSugestoes.xhtml"),
			LINK_RELATORIO_PAGAMENTO_CARTAO("linkRelatorioPagamentoCartao", "menuRelatorio",
			"../relatorio/relatorioPagamentoCartao.xhtml"),
			LINK_RELATORIO_PAGAMENTO_DINHEIRO("linkRelatorioPagamentoDinheiro", "menuRelatorio",
			"../relatorio/relatorioPagamentoDinheiro.xhtml");

	private String link;
	private String nomeMenuAtivo;
	private String outcome;

	private MenuMapEnum(String link, String nomeMenuAtivo, String outcome) {
		this.link = link;
		this.nomeMenuAtivo = nomeMenuAtivo;
		this.outcome = outcome;
	}

	public static List<MenuMapEnum> listaTodos() {
		return Arrays.asList(MenuMapEnum.values());
	}

	public static MenuMapEnum getByLink(String link) {
		MenuMapEnum[] itensEnum = MenuMapEnum.values();
		for (MenuMapEnum item : itensEnum) {
			if (item.getLink().equals(link)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * @return the nomeMenuAtivo
	 */
	public String getNomeMenuAtivo() {
		return nomeMenuAtivo;
	}

	/**
	 * @param nomeMenuAtivo
	 *            the nomeMenuAtivo to set
	 */
	public void setNomeMenuAtivo(String nomeMenuAtivo) {
		this.nomeMenuAtivo = nomeMenuAtivo;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome
	 *            the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

}
