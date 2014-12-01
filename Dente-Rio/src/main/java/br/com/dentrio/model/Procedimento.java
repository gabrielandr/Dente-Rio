package br.com.dentrio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.cache.annotation.Cacheable;

@Entity
public class Procedimento extends Timestampable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "codigo_procedimento")
	private Integer codigoProcedimento;
	@Column(name = "nome_procedimento")
	private String nomeProcedimento;
	@Column(name = "valor_procedimento")
	private BigDecimal valorProcedimento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.procedimento")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<ProcedimentoTratamento> procedimentoTratamentos = new HashSet<ProcedimentoTratamento>(0);

	public Procedimento() {
	}

	public Procedimento(Integer codigoProcedimento, String nomeProcedimento, BigDecimal valorProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
		this.nomeProcedimento = nomeProcedimento;
		this.valorProcedimento = valorProcedimento;
	}

	public Procedimento(Integer codigoProcedimento, String nomeProcedimento, BigDecimal valorProcedimento,
			Set<ProcedimentoTratamento> procedimentoTratamentos) {
		super();
		this.codigoProcedimento = codigoProcedimento;
		this.nomeProcedimento = nomeProcedimento;
		this.valorProcedimento = valorProcedimento;
		// this.procedimentoTratamentos = procedimentoTratamentos;
	}

	public Integer getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Integer codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	public BigDecimal getValorProcedimento() {
		return valorProcedimento;
	}

	public void setValorProcedimento(BigDecimal valorProcedimento) {
		this.valorProcedimento = valorProcedimento;
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
	 * @return the procedimentoTratamentos
	 */
	@Cacheable("")
	public Set<ProcedimentoTratamento> getProcedimentoTratamentos() {
		return procedimentoTratamentos;
	}

	/**
	 * @param procedimentoTratamentos
	 *            the procedimentoTratamentos to set
	 */
	public void setProcedimentoTratamentos(Set<ProcedimentoTratamento> procedimentoTratamentos) {
		this.procedimentoTratamentos = procedimentoTratamentos;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Procedimento)) {
			return false;
		}
		Procedimento other = (Procedimento) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
