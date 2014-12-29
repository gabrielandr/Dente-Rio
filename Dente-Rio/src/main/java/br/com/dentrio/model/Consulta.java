package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONSULTA")
public class Consulta extends Timestampable implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="result_avaliacao", columnDefinition = "TEXT")
	private String resultAvaliacao;

	/**
	 * @return the resultAvaliacao
	 */
	public String getResultAvaliacao() {
		return resultAvaliacao;
	}

	/**
	 * @param resultAvaliacao the resultAvaliacao to set
	 */
	public void setResultAvaliacao(String resultAvaliacao) {
		this.resultAvaliacao = resultAvaliacao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (resultAvaliacao == null ? 0 : resultAvaliacao.hashCode());
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
		if (!(obj instanceof Consulta)) {
			return false;
		}
		Consulta other = (Consulta) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (resultAvaliacao == null) {
			if (other.resultAvaliacao != null) {
				return false;
			}
		} else if (!resultAvaliacao.equals(other.resultAvaliacao)) {
			return false;
		}
		return true;
	}


}
