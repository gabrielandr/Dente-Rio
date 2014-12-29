package br.com.dentrio.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PACIENTE")
public class Paciente extends Pessoa implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Column(name = "PROFISSAO")
	private String profissao;
	@Column(name = "BAIRRO")
	private String bairro;
	@Column(name = "HEMORRAGIA")
	private Boolean hemorragia;
	@Column(name = "PRESSAO")
	private Boolean pressao;
	@Column(name = "CARDIACO")
	private Boolean cardiaco;
	@Column(name = "ALERGIA")
	private Boolean alergia;
	@Column(name = "DIABETES")
	private Boolean diabetes;
	@Column(name = "GESTANTE")
	private Boolean gestante;
	@Column(name = "MEDICO")
	private Boolean medico;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 10)
	private Set<Tratamento> tratamentos;

	@Column(name = "OBS_ANAMNESE")
	private String obsAnamnese;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Boolean getHemorragia() {
		return hemorragia;
	}

	public void setHemorragia(Boolean hemorragia) {
		this.hemorragia = hemorragia;
	}

	public Boolean getPressao() {
		return pressao;
	}

	public void setPressao(Boolean pressao) {
		this.pressao = pressao;
	}

	public Boolean getCardiaco() {
		return cardiaco;
	}

	public void setCardiaco(Boolean cardiaco) {
		this.cardiaco = cardiaco;
	}

	public Boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(Boolean alergia) {
		this.alergia = alergia;
	}

	public Boolean getGestante() {
		return gestante;
	}

	public void setGestante(Boolean gestante) {
		this.gestante = gestante;
	}

	public Boolean getMedico() {
		return medico;
	}

	public void setMedico(Boolean medico) {
		this.medico = medico;
	}

	public String getObsAnamnese() {
		return obsAnamnese;
	}

	public void setObsAnamnese(String obsAnamnese) {
		this.obsAnamnese = obsAnamnese;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (alergia == null ? 0 : alergia.hashCode());
		result = prime * result + (bairro == null ? 0 : bairro.hashCode());
		result = prime * result + (cardiaco == null ? 0 : cardiaco.hashCode());
		result = prime * result + (diabetes == null ? 0 : diabetes.hashCode());
		result = prime * result + (gestante == null ? 0 : gestante.hashCode());
		result = prime * result + (hemorragia == null ? 0 : hemorragia.hashCode());
		result = prime * result + (medico == null ? 0 : medico.hashCode());
		result = prime * result + (obsAnamnese == null ? 0 : obsAnamnese.hashCode());
		result = prime * result + (pressao == null ? 0 : pressao.hashCode());
		result = prime * result + (profissao == null ? 0 : profissao.hashCode());
		result = prime * result + (tratamentos == null ? 0 : tratamentos.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) obj;
		if (alergia == null) {
			if (other.alergia != null) {
				return false;
			}
		} else if (!alergia.equals(other.alergia)) {
			return false;
		}
		if (bairro == null) {
			if (other.bairro != null) {
				return false;
			}
		} else if (!bairro.equals(other.bairro)) {
			return false;
		}
		if (cardiaco == null) {
			if (other.cardiaco != null) {
				return false;
			}
		} else if (!cardiaco.equals(other.cardiaco)) {
			return false;
		}
		if (diabetes == null) {
			if (other.diabetes != null) {
				return false;
			}
		} else if (!diabetes.equals(other.diabetes)) {
			return false;
		}
		if (gestante == null) {
			if (other.gestante != null) {
				return false;
			}
		} else if (!gestante.equals(other.gestante)) {
			return false;
		}
		if (hemorragia == null) {
			if (other.hemorragia != null) {
				return false;
			}
		} else if (!hemorragia.equals(other.hemorragia)) {
			return false;
		}
		if (medico == null) {
			if (other.medico != null) {
				return false;
			}
		} else if (!medico.equals(other.medico)) {
			return false;
		}
		if (obsAnamnese == null) {
			if (other.obsAnamnese != null) {
				return false;
			}
		} else if (!obsAnamnese.equals(other.obsAnamnese)) {
			return false;
		}
		if (pressao == null) {
			if (other.pressao != null) {
				return false;
			}
		} else if (!pressao.equals(other.pressao)) {
			return false;
		}
		if (profissao == null) {
			if (other.profissao != null) {
				return false;
			}
		} else if (!profissao.equals(other.profissao)) {
			return false;
		}
		if (tratamentos == null) {
			if (other.tratamentos != null) {
				return false;
			}
		} else if (!tratamentos.equals(other.tratamentos)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the diabetes
	 */
	public Boolean getDiabetes() {
		return diabetes;
	}

	/**
	 * @param diabetes
	 *            the diabetes to set
	 */
	public void setDiabetes(Boolean diabetes) {
		this.diabetes = diabetes;
	}

	/**
	 * @return the tratamentos
	 */
	public Set<Tratamento> getTratamentos() {
		return tratamentos;
	}

	/**
	 * @param tratamentos
	 *            the tratamentos to set
	 */
	public void setTratamentos(Set<Tratamento> tratamentos) {
		this.tratamentos = tratamentos;
	}

}
