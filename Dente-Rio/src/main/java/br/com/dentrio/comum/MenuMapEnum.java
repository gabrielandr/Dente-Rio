package br.com.dentrio.comum;

import java.util.Arrays;
import java.util.List;

public enum MenuMapEnum {

	linkHome("linkHome", "menuHome", "/Home.xhtml"),
	linkPaciente("linkPaciente", "menuPaciente", "../funcionario/listarFuncionarios.xhtml"),
	/* Menu de Cadastros */
	linkFuncionario("linkFuncionario", "menuCadastros", "../funcionario/listarFuncionarios.xhtml"),
	linkDespesa("linkDespesa", "menuCadastros", "../despesa/listarDespesas.xhtml"),
	linkAreaEspecializada("linkAreaEspecializada", "menuCadastros", "../areaEspecializada/listarAreasEspecializadas.xhtml"),
	linkProcedimento("linkProcedimento", "menuCadastros", "../procedimento/listarProcedimentos.xhtml"),
	/* Fim Menu de Cadastros */
	linkSugestao("linkSugestao", "menuSugestao", "../sugestao/listarSugestoes.xhtml");
	
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
