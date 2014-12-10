package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.dentrio.comum.FormaPagamentoEnum;

@Entity
@Table(name = "movimento")
@DiscriminatorValue("P")
public class Pagamento extends Movimento implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Enumerated(EnumType.STRING)
	private FormaPagamentoEnum formaPagamento;

	@Column(name = "numero_parcela", nullable = true, columnDefinition = "int DEFAULT null")
	private Integer numeroParcela;

	@Column(name = "codigo_estorno")
	private Integer codigoEstorno;

	@ManyToOne
	@JoinColumn(name = "tratamento_id")
	private Tratamento tratamento;

	@Column(name = "estornado", nullable = false, columnDefinition = "tinyint(1) default false")
	private Boolean estornado = false;

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

}
