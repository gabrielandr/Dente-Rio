package br.com.dentrio.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.model.Pagamento;
import br.com.dentrio.pagamento.service.PagamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;

@Component("relatorioBean")
public class RelatorioBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	PagamentoService pagamentoService;

	private Date dataInicio = null;
	private Date dataFim = null;
	private List<Pagamento> listaPagamentosCartao = new ArrayList<Pagamento>();
	private List<Pagamento> listaPagamentosDinheiro = new ArrayList<Pagamento>();

	@PostConstruct
	public void inicializar() {
		dataInicio = null;
		dataFim = null;
		listaPagamentosCartao = new ArrayList<Pagamento>();
		listaPagamentosDinheiro = new ArrayList<Pagamento>();
	}

	public void filtrarRelatorioPagamentoCartao() {
		String dataIni = "";
		String dataFinal = "";
		dataIni = formataData(dataInicio);
		dataFinal = formataData(dataFim);

		if ("".equalsIgnoreCase(dataIni)) {
			dataIni = "1900-01-01";
		}
		if ("".equalsIgnoreCase(dataFinal)) {
			dataFinal = "2099-01-01";
		}
		setListaPagamentosCartao(pagamentoService.filtrarPagamentosCartao(dataIni, dataFinal));
		FacesUtil.redirect("/pages/relatorio/relatorioPagamentosCartao.xhtml");
	}

	public void filtrarRelatorioPagamentoDinheiro() {
		String dataIni = "";
		String dataFinal = "";
		dataIni = formataData(dataInicio);
		dataFinal = formataData(dataFim);

		if ("".equalsIgnoreCase(dataIni)) {
			dataIni = "1900-01-01";
		}
		if ("".equalsIgnoreCase(dataFinal)) {
			dataFinal = "2099-01-01";
		}
		setListaPagamentosDinheiro(pagamentoService.filtrarPagamentosDinheiro(dataIni, dataFinal));
		FacesUtil.redirect("/pages/relatorio/relatorioPagamentosDinheiro.xhtml");
	}

	public void preProcessarRelatorioPdf(Object documento) throws IOException, BadElementException, DocumentException {
		Document document = (Document) documento;
		document.open();
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens"
				+ File.separator + "dentriologopdf.png";
		document.add(Image.getInstance(logo));
	}

	/**
	 * @return the pagamentoService
	 */
	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	/**
	 * @param pagamentoService
	 *            the pagamentoService to set
	 */
	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio
	 *            the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim
	 *            the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the listaPagamentosCartao
	 */
	public List<Pagamento> getListaPagamentosCartao() {
		return listaPagamentosCartao;
	}

	/**
	 * @param listaPagamentosCartao
	 *            the listaPagamentosCartao to set
	 */
	public void setListaPagamentosCartao(List<Pagamento> listaPagamentosCartao) {
		this.listaPagamentosCartao = listaPagamentosCartao;
	}

	/**
	 * @return the listaPagamentosDinheiro
	 */
	public List<Pagamento> getListaPagamentosDinheiro() {
		return listaPagamentosDinheiro;
	}

	/**
	 * @param listaPagamentosDinheiro
	 *            the listaPagamentosDinheiro to set
	 */
	public void setListaPagamentosDinheiro(List<Pagamento> listaPagamentosDinheiro) {
		this.listaPagamentosDinheiro = listaPagamentosDinheiro;
	}

}
