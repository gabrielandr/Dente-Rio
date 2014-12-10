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
	@Column(name = "posicao_dente", columnDefinition = "int DEFAULT '-1'")
	private Integer posicaoDente;

	public ProcedimentoTratamentoId() {
	}

	public ProcedimentoTratamentoId(Tratamento tratamento, Procedimento procedimento, Integer posicaoDente) {
		this.tratamento = tratamento;
		this.procedimento = procedimento;
		this.posicaoDente = posicaoDente;
	}

	public Tratamento getTratamento() {
		return this.tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public Procedimento getProcedimento() {
		return this.procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.posicaoDente == null ? 0 : this.posicaoDente.hashCode());
		result = 31 * result + (this.procedimento == null ? 0 : this.procedimento.hashCode());
		result = 31 * result + (this.tratamento == null ? 0 : this.tratamento.hashCode());
		return result;
	}

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
		if (this.posicaoDente == null) {
			if (other.posicaoDente != null) {
				return false;
			}
		} else if (!this.posicaoDente.equals(other.posicaoDente)) {
			return false;
		}
		if (this.procedimento == null) {
			if (other.procedimento != null) {
				return false;
			}
		} else if (!this.procedimento.equals(other.procedimento)) {
			return false;
		}
		return true;
	}

	public Integer getPosicaoDente() {
		return this.posicaoDente;
	}

	public void setPosicaoDente(Integer posicaoDente) {
		this.posicaoDente = posicaoDente;
	}
}