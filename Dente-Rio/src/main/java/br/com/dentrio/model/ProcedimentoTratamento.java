package br.com.dentrio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.dentrio.comum.StatusProcedimentoEnum;

@Entity
@Table(name = "procedimento_tratamento")
@AssociationOverrides({
		@AssociationOverride(name = "pk.tratamento", joinColumns = @JoinColumn(name = "tratamento_id")),
		@AssociationOverride(name = "pk.procedimento", joinColumns = @JoinColumn(name = "procedimento_id")) })
public class ProcedimentoTratamento extends Timestampable implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProcedimentoTratamentoId pk = new ProcedimentoTratamentoId();

	@Column(name = "valor_real")
	private BigDecimal valorReal;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	private Date dataFim;

	@Column(name = "status_procedimento", columnDefinition = "varchar(255) DEFAULT 'Não Iniciado'")
	@Enumerated(EnumType.STRING)
	private StatusProcedimentoEnum statusProcedimento;

	private String detalhes;

	public ProcedimentoTratamento() {
	}

	/**
	 * @return the pk
	 */
	public ProcedimentoTratamentoId getPk() {
		return pk;
	}

	@Transient
	public Tratamento getTratamento() {
		return getPk().getTratamento();
	}

	public void setTratamento(Tratamento tratamento) {
		getPk().setTratamento(tratamento);
	}

	@Transient
	public Procedimento getProcedimento() {
		return getPk().getProcedimento();
	}

	public void setProcedimento(Procedimento procedimento) {
		getPk().setProcedimento(procedimento);
	}

	@Transient
	public Integer getPosicaoDente() {
		return getPk().getPosicaoDente();
	}

	public void setPosicaoDente(Integer posDente) {
		getPk().setPosicaoDente(posDente);
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(ProcedimentoTratamentoId pk) {
		this.pk = pk;
	}

	/**
	 * @return the valorReal
	 */
	public BigDecimal getValorReal() {
		return valorReal;
	}

	/**
	 * @param valorReal
	 *            the valorReal to set
	 */
	public void setValorReal(BigDecimal valorReal) {
		this.valorReal = valorReal;
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
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim
	 *            the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the detalhes
	 */
	public String getDetalhes() {
		return detalhes;
	}

	/**
	 * @param detalhes
	 *            the detalhes to set
	 */
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
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
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((detalhes == null) ? 0 : detalhes.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((valorReal == null) ? 0 : valorReal.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProcedimentoTratamento)) {
			return false;
		}
		ProcedimentoTratamento other = (ProcedimentoTratamento) obj;
		if (pk == null) {
			if (other.pk != null) {
				return false;
			}
		} else if (!pk.equals(other.pk)) {
			return false;
		}
		return true;
	}

}
