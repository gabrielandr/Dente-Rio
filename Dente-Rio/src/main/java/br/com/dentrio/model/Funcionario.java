package br.com.dentrio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.dentrio.comum.RolesEnum;
import br.com.dentrio.comum.TipoFuncionarioEnum;

@Entity
@Table(name = "FUNCIONARIO", uniqueConstraints = {
@UniqueConstraint(columnNames = {"LOGIN"})})
public class Funcionario extends Pessoa implements Serializable {

	private static final long serialVersionUID = -5988625296699742692L;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_FUNCIONARIO")
	private TipoFuncionarioEnum tipoFuncionario;

	@OneToOne
	@JoinColumn(name = "AREA_ESPECIALIZADA_ID")
	private AreaEspecializada areaEspecializada;

	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Tratamento> tratamentos = new HashSet<Tratamento>();

	@Column(name = "LOGIN")
	private String login;
	@Column(name = "SENHA")
	private String senha;
	@Column(name = "CRO")
	private String cro;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private RolesEnum role;

	@Column(name = "SOCIO")
	private Boolean socio = false;

	@Column(name = "HORA_ENTRADA")
	private Date horaEntrada;

	@Column(name = "HORA_SAIDA")
	private Date horaSaida;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}

	public Boolean getSocio() {
		return socio;
	}

	public void setSocio(Boolean socio) {
		this.socio = socio;
	}

	public TipoFuncionarioEnum getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionarioEnum tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	/**
	 * @return the areaEspecializada
	 */
	public AreaEspecializada getAreaEspecializada() {
		return areaEspecializada;
	}

	/**
	 * @param areaEspecializada
	 *            the areaEspecializada to set
	 */
	public void setAreaEspecializada(AreaEspecializada areaEspecializada) {
		this.areaEspecializada = areaEspecializada;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (areaEspecializada == null ? 0 : areaEspecializada.hashCode());
		result = prime * result + (cro == null ? 0 : cro.hashCode());
		result = prime * result + (horaEntrada == null ? 0 : horaEntrada.hashCode());
		result = prime * result + (horaSaida == null ? 0 : horaSaida.hashCode());
		result = prime * result + (login == null ? 0 : login.hashCode());
		result = prime * result + (senha == null ? 0 : senha.hashCode());
		result = prime * result + (socio == null ? 0 : socio.hashCode());
		result = prime * result + (tipoFuncionario == null ? 0 : tipoFuncionario.hashCode());
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
		if (!(obj instanceof Funcionario)) {
			return false;
		}
		Funcionario other = (Funcionario) obj;
		if (areaEspecializada == null) {
			if (other.areaEspecializada != null) {
				return false;
			}
		} else if (!areaEspecializada.equals(other.areaEspecializada)) {
			return false;
		}
		if (cro == null) {
			if (other.cro != null) {
				return false;
			}
		} else if (!cro.equals(other.cro)) {
			return false;
		}
		if (horaEntrada == null) {
			if (other.horaEntrada != null) {
				return false;
			}
		} else if (!horaEntrada.equals(other.horaEntrada)) {
			return false;
		}
		if (horaSaida == null) {
			if (other.horaSaida != null) {
				return false;
			}
		} else if (!horaSaida.equals(other.horaSaida)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (senha == null) {
			if (other.senha != null) {
				return false;
			}
		} else if (!senha.equals(other.senha)) {
			return false;
		}
		if (socio == null) {
			if (other.socio != null) {
				return false;
			}
		} else if (!socio.equals(other.socio)) {
			return false;
		}
		if (tipoFuncionario != other.tipoFuncionario) {
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
	 * @return the role
	 */
	public RolesEnum getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(RolesEnum role) {
		this.role = role;
	}

}
