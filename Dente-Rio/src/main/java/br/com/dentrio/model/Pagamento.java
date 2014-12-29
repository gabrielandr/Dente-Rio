package br.com.dentrio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.dentrio.comum.FormaPagamentoEnum;

@Entity
@Table(name = "MOVIMENTO")
@DiscriminatorValue("P")
public class Pagamento extends Movimento implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Enumerated(EnumType.STRING)
	@Column(name = "FORMA_PAGAMENTO")
	private FormaPagamentoEnum formaPagamento;

	@Column(name = "NUMERO_PARCELA", nullable = true, columnDefinition = "int DEFAULT null")
	private Integer numeroParcela;

	@Column(name = "CODIGO_ESTORNO")
	private Integer codigoEstorno;

	@ManyToOne
	@JoinColumn(name = "TRATAMENTO_ID")
	private Tratamento tratamento;

	@Column(name = "ESTORNADO", nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean estornado = false;

	@OneToOne
	@JoinColumn(name = "PAGAMENTO_ESTORNADO")
	private Pagamento pagamentoEstornado;

	@Column(name = "SOMA")
	private BigDecimal soma;

	@Column(name = "RESTANTE")
	private BigDecimal restante;

	/**
	 * @return the formaPagamento
	 */
	public FormaPagamentoEnum getFormaPagamento() {
		return formaPagamento;
	}

	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	/**
	 * @return the numeroParcela
	 */
	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	/**
	 * @param numeroParcela the numeroParcela to set
	 */
	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (formaPagamento == null ? 0 : formaPagamento.hashCode());
		result = prime * result + (numeroParcela == null ? 0 : numeroParcela.hashCode());
		result = prime * result + (tratamento == null ? 0 : tratamento.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Pagamento)) {
			return false;
		}
		Pagamento other = (Pagamento) obj;
		if (formaPagamento != other.formaPagamento) {
			return false;
		}
		if (numeroParcela == null) {
			if (other.numeroParcela != null) {
				return false;
			}
		} else if (!numeroParcela.equals(other.numeroParcela)) {
			return false;
		}
		if (tratamento == null) {
			if (other.tratamento != null) {
				return false;
			}
		} else if (!tratamento.equals(other.tratamento)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the tratamento
	 */
	public Tratamento getTratamento() {
		return tratamento;
	}

	/**
	 * @param tratamento the tratamento to set
	 */
	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	/**
	 * @return the codigoEstorno
	 */
	public Integer getCodigoEstorno() {
		return codigoEstorno;
	}

	/**
	 * @param codigoEstorno
	 *            the codigoEstorno to set
	 */
	public void setCodigoEstorno(Integer codigoEstorno) {
		this.codigoEstorno = codigoEstorno;
	}

	/**
	 * @return the estornado
	 */
	public Boolean getEstornado() {
		return estornado;
	}

	/**
	 * @param estornado
	 *            the estornado to set
	 */
	public void setEstornado(Boolean estornado) {
		this.estornado = estornado;
	}

	/**
	 * @return the pagamentoEstornado
	 */
	public Pagamento getPagamentoEstornado() {
		return pagamentoEstornado;
	}

	/**
	 * @param pagamentoEstornado
	 *            the pagamentoEstornado to set
	 */
	public void setPagamentoEstornado(Pagamento pagamentoEstornado) {
		this.pagamentoEstornado = pagamentoEstornado;
	}

	/**
	 * @return the soma
	 */
	public BigDecimal getSoma() {
		return soma;
	}

	/**
	 * @param soma
	 *            the soma to set
	 */
	public void setSoma(BigDecimal soma) {
		this.soma = soma;
	}

	/**
	 * @return the restante
	 */
	public BigDecimal getRestante() {
		return restante;
	}

	/**
	 * @param restante
	 *            the restante to set
	 */
	public void setRestante(BigDecimal restante) {
		this.restante = restante;
	}

}
