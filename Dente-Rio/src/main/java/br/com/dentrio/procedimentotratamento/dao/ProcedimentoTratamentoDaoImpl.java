package br.com.dentrio.procedimentotratamento.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.ProcedimentoTratamento;

@Repository
@Transactional
public class ProcedimentoTratamentoDaoImpl implements ProcedimentoTratamentoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		getSessionFactory().getCurrentSession().saveOrUpdate(procedimentoTratamento);
		Hibernate.initialize(procedimentoTratamento.getPk().getTratamento());
	}

	@Override
	public void editarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		// ProcedimentoTratamento procedimentoTratamentoToUpdate =
		// getProcedimentoTratamento(procedimentoTratamento.getId());
		// procedimentoTratamentoToUpdate.setFirstName(procedimentoTratamento.getFirstName());
		// procedimentoTratamentoToUpdate.setLastName(procedimentoTratamento.getLastName());
		// procedimentoTratamentoToUpdate.setTelefone(procedimentoTratamento.getTelefone());
		// procedimentoTratamentoToUpdate.setEmail(procedimentoTratamento.getEmail());
		// sessionFactory.getCurrentSession().update(procedimentoTratamentoToUpdate);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProcedimentoTratamento> listProcedimentoTratamentos() {
		return getSessionFactory().getCurrentSession().createCriteria(ProcedimentoTratamento.class).list();
	}

	@Override
	public ProcedimentoTratamento getProcedimentoTratamento(Integer procedimentoTratamentoId) {
		ProcedimentoTratamento procedimentoTratamento = (ProcedimentoTratamento) getSessionFactory()
				.getCurrentSession().get(ProcedimentoTratamento.class, procedimentoTratamentoId);
		Hibernate.initialize(procedimentoTratamento.getProcedimento());
		Hibernate.initialize(procedimentoTratamento.getTratamento());
		return procedimentoTratamento;
	}

	@Override
	public void deletarProcedimentoTratamento(ProcedimentoTratamento procedimentoTratamento) {
		getSessionFactory().getCurrentSession().delete(procedimentoTratamento);
		getSessionFactory().getCurrentSession().flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
