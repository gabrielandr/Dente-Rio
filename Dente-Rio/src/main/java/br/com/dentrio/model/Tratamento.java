package br.com.dentrio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.cache.annotation.Cacheable;

import br.com.dentrio.comum.TiposOrcamentoEnum;

@Entity
@Table(name = "tratamento")
public class Tratamento extends Timestampable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_alta")
	private Date dataAlta;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "status_tratamento")
	@Enumerated(EnumType.STRING)
	private TiposOrcamentoEnum statusTratamento;

	@Column(name = "obs_alta")
	private String obsAlta;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tratamento")
	@OrderBy("id")
	private Set<Pagamento> pagamentos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.tratamento", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OrderBy("dataInicio")
	private Set<ProcedimentoTratamento> procedimentoTratamentos = new HashSet<ProcedimentoTratamento>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	public Tratamento() {
	}

	public Tratamento(Funcionario funcionario, Date dataInicio, Date dataAlta, BigDecimal valorTotal,
			TiposOrcamentoEnum statusTratamento, String obsAlta, Paciente paciente) {
		this.funcionario = funcionario;
		this.dataInicio = dataInicio;
		this.dataAlta = dataAlta;
		this.valorTotal = valorTotal;
		this.statusTratamento = statusTratamento;
		this.obsAlta = obsAlta;
		this.paciente = paciente;
	}

	public Tratamento(Funcionario funcionario, Date dataInicio, Date dataAlta, BigDecimal valorTotal,
			TiposOrcamentoEnum statusTratamento, String obsAlta, Set<Pagamento> pagamentos,
			Set<ProcedimentoTratamento> procedimentoTratamentos, Paciente paciente) {
		this.funcionario = funcionario;
		this.dataInicio = dataInicio;
		this.dataAlta = dataAlta;
		this.valorTotal = valorTotal;
		this.statusTratamento = statusTratamento;
		this.obsAlta = obsAlta;
		this.pagamentos = pagamentos;
		this.procedimentoTratamentos = procedimentoTratamentos;
		this.paciente = paciente;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio
	 *            the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataAlta
	 */
	public Date getDataAlta() {
		return dataAlta;
	}

	/**
	 * @param dataAlta
	 *            the dataAlta to set
	 */
	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal
	 *            the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the obsAlta
	 */
	public String getObsAlta() {
		return obsAlta;
	}

	/**
	 * @param obsAlta
	 *            the obsAlta to set
	 */
	public void setObsAlta(String obsAlta) {
		this.obsAlta = obsAlta;
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
	 * @return the paciente
	 */
	@Cacheable("")
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the funcionario
	 */
	@Cacheable("")
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario
	 *            the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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

	/**
	 * @return the pagamentos
	 */
	public Set<Pagamento> getPagamentos() {
		return pagamentos;
	}

	/**
	 * @param pagamentos
	 *            the pagamentos to set
	 */
	public void setPagamentos(Set<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
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
		result = prime * result + (id == null ? 0 : id.hashCode());
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
		if (!(obj instanceof Tratamento)) {
			return false;
		}
		Tratamento other = (Tratamento) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the statusTratamento
	 */
	public TiposOrcamentoEnum getStatusTratamento() {
		return statusTratamento;
	}

	/**
	 * @param statusTratamento
	 *            the statusTratamento to set
	 */
	public void setStatusTratamento(TiposOrcamentoEnum statusTratamento) {
		this.statusTratamento = statusTratamento;
	}

}