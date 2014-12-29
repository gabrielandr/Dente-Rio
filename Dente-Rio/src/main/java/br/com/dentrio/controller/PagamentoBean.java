package br.com.dentrio.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.comum.FormaPagamentoEnum;
import br.com.dentrio.comum.RolesEnum;
import br.com.dentrio.funcionario.service.FuncionarioService;
import br.com.dentrio.model.Funcionario;
import br.com.dentrio.model.Pagamento;
import br.com.dentrio.model.Tratamento;
import br.com.dentrio.movimento.service.MovimentoService;
import br.com.dentrio.pagamento.service.PagamentoService;
import br.com.dentrio.tratamento.service.TratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("pagamentoBean")
public class PagamentoBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PagamentoService pagamentoService;
	@Autowired
	private MovimentoService movimentoService;
	@Autowired
	private TratamentoService tratamentoService;
	@Autowired
	FuncionarioService funcionarioService;

	private Pagamento pagamento;
	private String tratamentoId;

	private String login;
	private String password;

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

	public String salvarPagamento() {
		try {
			Tratamento tratamento = recuperarTratamento();
			BigDecimal valorTotalPago = retornaValorTotalPago(tratamento);
			BigDecimal valorRestTrat = retornaValorRestanteTratamento(tratamento, valorTotalPago);
			int resultadoTotalTrat = pagamento.getValor().compareTo(tratamento.getValorTotal());
			int resultadoValorRestante = pagamento.getValor().compareTo(valorRestTrat);
			if (resultadoTotalTrat == 1) {
				FacesUtil.addErrorMessage(Constantes.ERRO,
						"O valor do pagamento é maior que o valor total do tratamento! Escolha um valor menor.");
				return null;
			} else if (resultadoValorRestante == 1) {
				FacesUtil.addErrorMessage(Constantes.ERRO,
						"O valor do pagamento é maior que o valor restante a ser pago! Escolha um valor menor.");
				return null;
			} else if ("0.00".equalsIgnoreCase(pagamento.getValor().toString())) {
				FacesUtil.addErrorMessage(Constantes.ERRO, "Você deve informar um valor para o pagamento.");
				return null;
			}
			setarTimestamps(pagamento);
			pagamento.setTratamento(tratamento);

			if (!tratamento.getPagamentos().isEmpty()) {
				pagamento.setSoma(valorTotalPago.add(pagamento.getValor()));
			}

			setarValorRestantePagamento(tratamento, valorTotalPago);

			pagamentoService.salvarPagamento(pagamento);
			atualizaStatusTratamento(tratamento.getId(), pagamento);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Pagamento adicionado com Sucesso!");
			inicializar();
			return "dadosTratamento?faces-redirect=true&tratamento_id=" + tratamento.getId();

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar!");
			return null;
		}
	}

	/**
	 * @param tratamento
	 * @param valorTotalPago
	 */
	private void setarValorRestantePagamento(Tratamento tratamento, BigDecimal valorTotalPago) {
		BigDecimal restante = retornaValorRestanteTratamento(tratamento, valorTotalPago).subtract(pagamento.getValor());
		pagamento.setRestante(restante);
	}

	public String estornarPagamento(Pagamento pagament) {
		try {
			Tratamento tratamento = recuperarTratamento();
			BigDecimal valorEstornado = pagament.getValor().negate();
			estornarMovimento(pagament);
			salvarPagamentoEstorno(pagament, tratamento, valorEstornado);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Pagamento estornado com Sucesso!");
			inicializar();
			return "dadosTratamento?faces-redirect=true&tratamento_id=" + tratamento.getId();
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao estornar o pagamento!");
			return null;
		}
	}

	/**
	 * @param pagament
	 */
	private void estornarMovimento(Pagamento pagament) {
		movimentoService.estornarMovimento(pagament.getId());
	}

	/**
	 * @param pagament
	 * @param tratamento
	 * @param valorEstornado
	 */
	private void salvarPagamentoEstorno(Pagamento pagament, Tratamento tratamento, BigDecimal valorEstornado) {
		try {
			Pagamento pag = new Pagamento();
			setarTimestamps(pag);
			pag.setValor(valorEstornado);
			pag.setTratamento(tratamento);
			pag.setCodigoEstorno(pagament.getCodigoEstorno());
			pag.setEstornado(true);
			pag.setPagamentoEstornado(pagament);
			pagamentoService.salvarPagamento(pag);
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocoreu um erro ao tentar salvar!");
		}
	}

	/**
	 * @return
	 */
	private Tratamento recuperarTratamento() {
		String tratamento_id = FacesUtil.getRequestParam("tratamento_id");
		return tratamentoService.getTratamento(Integer.valueOf(tratamento_id));
	}

	public String editarPagamento(Integer pagamentoId) {
		try {
			pagamento = pagamentoService.getPagamento(pagamentoId);
			return "formPagamento?faces-redirect=true&pagamentoId=" + pagamentoId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deletarPagamento(Integer pagamentoId) {
		try {
			Funcionario funcionario = funcionarioService.retornaUsuarioPeloLogin(login);
			if (null != funcionario) {
				if (password.equalsIgnoreCase(funcionario.getSenha()) && funcionario.getRole() == RolesEnum.SOCIO) {
					Tratamento tratamento = recuperarTratamento();
					Pagamento pagamento = pagamentoService.getPagamento(pagamentoId);
					pagamentoService.deletarPagamento(pagamento);
					FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Pagamento deletado com Sucesso!");
					inicializar();
					FacesUtil.redirect("/pages/paciente/dadosTratamento.xhtml?tratamento_id=" + tratamento.getId());
				} else if (funcionario.getRole() != RolesEnum.SOCIO) {
					setLogin("");
					setPassword("");
					FacesUtil.addErrorMessage(Constantes.ERRO, "Você não tem permissão para deletar pagamentos!");
				}
			} else {
				login = "";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "O login não existe ou foi digitado errado.",
								null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar!");
		}
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	public void setarTimestamps(Pagamento pag) {
		if (pag.createdAt == null) {
			pag.setCreatedAt(new Date());
			pag.setUpdatedAt(new Date());
		}
		pag.setUpdatedAt(new Date());
	}

	/**
	 * @return the tratamentoId
	 */
	public String getTratamentoId() {
		return tratamentoId;
	}

	/**
	 * @param tratamentoId
	 *            the tratamentoId to set
	 */
	public void setTratamentoId(String tratamentoId) {
		this.tratamentoId = tratamentoId;
	}

	/**
	 * @return the tratamentoService
	 */
	@Override
	public TratamentoService getTratamentoService() {
		return tratamentoService;
	}

	/**
	 * @param tratamentoService
	 *            the tratamentoService to set
	 */
	@Override
	public void setTratamentoService(TratamentoService tratamentoService) {
		this.tratamentoService = tratamentoService;
	}

	/**
	 * @return the listaFormasPagamento
	 */
	public List<FormaPagamentoEnum> getListaFormasPagamento() {
		return FormaPagamentoEnum.listaTodos();
	}

	/**
	 * @return the movimentoService
	 */
	public MovimentoService getMovimentoService() {
		return movimentoService;
	}

	/**
	 * @param movimentoService
	 *            the movimentoService to set
	 */
	public void setMovimentoService(MovimentoService movimentoService) {
		this.movimentoService = movimentoService;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
