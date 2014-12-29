package br.com.dentrio.comum;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.dentrio.model.Funcionario;
import br.com.dentrio.model.Pagamento;
import br.com.dentrio.model.Tratamento;
import br.com.dentrio.tratamento.service.TratamentoService;

import com.sun.faces.context.SessionMap;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 750309574357642983L;

	private SessionMap sessionMap;

	@Autowired
	private TratamentoService tratamentoService;

	public static Object getManagedBean(final String beanName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Object bean;

		try {
			ELContext elContext = fc.getELContext();
			bean = elContext.getELResolver().getValue(elContext, null, beanName);
		} catch (RuntimeException e) {
			throw new FacesException(e.getMessage(), e);
		}

		if (bean == null) {
			throw new FacesException("Managed bean com o nome '" + beanName + "' não foi encontrado.");
		}

		return bean;
	}

	/**
	 * @return the sessionMap
	 */
	public SessionMap getSessionMap() {
		sessionMap = (SessionMap) FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return sessionMap;
	}

	/**
	 * @param sessionMap
	 *            the sessionMap to set
	 */
	public void setSessionMap(SessionMap sessionMap) {
		this.sessionMap = sessionMap;
	}

	public BigDecimal retornaValorRestanteTratamento(Tratamento tratam, BigDecimal valorTotalPago) {
		BigDecimal valorRestrat = BigDecimal.ZERO;
		if (valorTotalPago == BigDecimal.ZERO) {
			valorRestrat = tratam.getValorTotal();
		} else {
			if (tratam.getValorTotal() != null) {
				valorRestrat = tratam.getValorTotal().subtract(valorTotalPago);
			}
		}
		return valorRestrat;
	}

	public BigDecimal retornaValorTotalPago(Tratamento tratamento) {
		BigDecimal valorTotalPago = BigDecimal.ZERO;
		for (Pagamento pagamento : tratamento.getPagamentos()) {
			valorTotalPago = valorTotalPago.add(pagamento.getValor());
		}
		return valorTotalPago;
	}

	/**
	 * 0 - Valores iguais / 1 - Primeiro valor maior que o segundo / 2 - Segundo
	 * valor maior que o primeiro
	 * 
	 * @param valor1
	 * @param valor2
	 */
	public int retornaComparacaoBigDecimal(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2);
	}

	public void atualizaStatusTratamento(Integer tratamentoId, Pagamento pagamento) {
		Tratamento tratamento = tratamentoService.getTratamento(tratamentoId);
		int retornoValorPago = retornaComparacaoBigDecimal(pagamento.getValor(), tratamento.getValorTotal());
		BigDecimal valorTotalPago = retornaValorTotalPago(tratamento);
		int retornoValorTotalTratamentoPago = retornaComparacaoBigDecimal(valorTotalPago, tratamento.getValorTotal());
		if (tratamento.getPagamentos().size() == 1 && retornoValorPago == 0) {
			tratamento.setStatusTratamento(TiposOrcamentoEnum.ORCAMENTO_CONTRATADO_FICHA_LIQUIDADA);
		} else if (retornoValorTotalTratamentoPago == 0) {
			tratamento.setStatusTratamento(TiposOrcamentoEnum.FICHA_LIQUIDADA);
		} else {
			tratamento.setStatusTratamento(TiposOrcamentoEnum.ORCAMENTO_CONTRATADO);
		}
		tratamentoService.salvarTratamento(tratamento);
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

	public void efetuarLogin(Funcionario funcionario) {
		getSessionMap().put(Constantes.CURRENT_USER, funcionario);
		getSessionMap().put(Constantes.NAME_CURRENT_USER, StringUtils.split(funcionario.getNomePessoa())[0]);
	}

	public void efetuarLogout() {
		getSessionMap().remove(Constantes.CURRENT_USER);
	}

}
