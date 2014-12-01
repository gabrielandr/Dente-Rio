package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movimento")
@DiscriminatorValue("D")
public class Despesa extends Movimento implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Column(name = "descricao_despesa")
	private String descricaoDespesa;

	/**
	 * @return the descricaoDespesa
	 */
	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	/**
	 * @param descricaoDespesa the descricaoDespesa to set
	 */
	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricaoDespesa == null) ? 0 : descricaoDespesa.hashCode());
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
		if (!(obj instanceof Despesa)) {
			return false;
		}
		Despesa other = (Despesa) obj;
		if (descricaoDespesa == null) {
			if (other.descricaoDespesa != null) {
				return false;
			}
		} else if (!descricaoDespesa.equals(other.descricaoDespesa)) {
			return false;
		}
		return true;
	}

}
