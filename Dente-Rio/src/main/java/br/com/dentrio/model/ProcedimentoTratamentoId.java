package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ProcedimentoTratamentoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Tratamento tratamento;

	@ManyToOne
	private Procedimento procedimento;

	@Column(name = "posicao_dente", columnDefinition = "int DEFAULT -1")
	private Integer posicaoDente;

	public ProcedimentoTratamentoId() {
	}

	public ProcedimentoTratamentoId(Tratamento tratamento, Procedimento procedimento, Integer posicaoDente) {
		this.tratamento = tratamento;
		this.procedimento = procedimento;
		this.posicaoDente = posicaoDente;
	}

	/**
	 * @return the tratamento
	 */
	public Tratamento getTratamento() {
		return tratamento;
	}

	/**
	 * @param tratamento
	 *            the tratamento to set
	 */
	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	/**
	 * @return the procedimento
	 */
	public Procedimento getProcedimento() {
		return procedimento;
	}

	/**
	 * @param procedimento
	 *            the procedimento to set
	 */
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((posicaoDente == null) ? 0 : posicaoDente.hashCode());
		result = prime * result + ((procedimento == null) ? 0 : procedimento.hashCode());
		result = prime * result + ((tratamento == null) ? 0 : tratamento.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProcedimentoTratamentoId)) {
			return false;
		}
		ProcedimentoTratamentoId other = (ProcedimentoTratamentoId) obj;
		if (posicaoDente == null) {
			if (other.posicaoDente != null) {
				return false;
			}
		} else if (!posicaoDente.equals(other.posicaoDente)) {
			return false;
		}
		if (procedimento == null) {
			if (other.procedimento != null) {
				return false;
			}
		} else if (!procedimento.equals(other.procedimento)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the posicaoDente
	 */
	public Integer getPosicaoDente() {
		return posicaoDente;
	}

	/**
	 * @param posicaoDente
	 *            the posicaoDente to set
	 */
	public void setPosicaoDente(Integer posicaoDente) {
		this.posicaoDente = posicaoDente;
	}

}
