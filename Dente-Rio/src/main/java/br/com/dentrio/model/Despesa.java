package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.dentrio.comum.TipoDespesaEnum;

@Entity
@Table(name = "movimento")
@DiscriminatorValue("D")
public class Despesa extends Movimento implements Serializable {
	private static final long serialVersionUID = -5988625296699742692L;
	@Column(name = "descricao_despesa")
	private String descricaoDespesa;
	@Column(name = "tipo_despesa")
	@Enumerated(EnumType.STRING)
	private TipoDespesaEnum tipoDespesa;

	public String getDescricaoDespesa() {
		return this.descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (this.descricaoDespesa == null ? 0 : this.descricaoDespesa.hashCode());
		result = 31 * result + (this.tipoDespesa == null ? 0 : this.tipoDespesa.hashCode());
		return result;
	}

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
		if (this.descricaoDespesa == null) {
			if (other.descricaoDespesa != null) {
				return false;
			}
		} else if (!this.descricaoDespesa.equals(other.descricaoDespesa)) {
			return false;
		}
		if (this.tipoDespesa != other.tipoDespesa) {
			return false;
		}
		return true;
	}

	public TipoDespesaEnum getTipoDespesa() {
		return this.tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesaEnum tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
}
