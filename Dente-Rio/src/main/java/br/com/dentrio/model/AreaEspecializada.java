package br.com.dentrio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.bytecode.internal.javassist.FieldHandled;
import org.hibernate.bytecode.internal.javassist.FieldHandler;

@Entity
@Table(name = "area_especializada", uniqueConstraints = { @UniqueConstraint(columnNames = "nome_area_especializada") })
public class AreaEspecializada extends Timestampable implements Serializable, FieldHandled {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "area_id")
	private Integer id;
	@Column(name = "nome_area_especializada", unique = true)
	private String nomeAreaEspecializada;

	private FieldHandler handler;

	@OneToOne(mappedBy = "areaEspecializada", fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Funcionario funcionario;

	public String getNomeAreaEspecializada() {
		return nomeAreaEspecializada;
	}

	public void setNomeAreaEspecializada(String nomeAreaEspecializada) {
		this.nomeAreaEspecializada = nomeAreaEspecializada;
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
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeAreaEspecializada == null) ? 0 : nomeAreaEspecializada.hashCode());
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
		if (!(obj instanceof AreaEspecializada)) {
			return false;
		}
		AreaEspecializada other = (AreaEspecializada) obj;
		if (funcionario == null) {
			if (other.funcionario != null) {
				return false;
			}
		} else if (!funcionario.equals(other.funcionario)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nomeAreaEspecializada == null) {
			if (other.nomeAreaEspecializada != null) {
				return false;
			}
		} else if (!nomeAreaEspecializada.equals(other.nomeAreaEspecializada)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		if (this.handler != null) {
			return (Funcionario) this.handler.readObject(this, "funcionario", funcionario);
		}
		return funcionario;
	}

	/**
	 * @param funcionario
	 *            the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		if (this.handler != null) {
			this.funcionario = (Funcionario) this.handler.writeObject(this, "funcionario", this.funcionario,
					funcionario);
		}
		this.funcionario = funcionario;
	}

	@Override
	public void setFieldHandler(FieldHandler handler) {
		this.handler = handler;

	}

	@Override
	public FieldHandler getFieldHandler() {
		return this.handler;
	}
}
