package br.com.dentrio.tratamento.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Tratamento;

@Repository
@Transactional
public class TratamentoDaoImpl implements TratamentoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvarTratamento(Tratamento tratamento) {
		getSessionFactory().getCurrentSession().saveOrUpdate(tratamento);
	}

	@Override
	public void editarTratamento(Tratamento tratamento) {
		// Tratamento tratamentoToUpdate = getTratamento(tratamento.getId());
		// tratamentoToUpdate.setFirstName(tratamento.getFirstName());
		// tratamentoToUpdate.setLastName(tratamento.getLastName());
		// tratamentoToUpdate.setTelefone(tratamento.getTelefone());
		// tratamentoToUpdate.setEmail(tratamento.getEmail());
		// sessionFactory.getCurrentSession().update(tratamentoToUpdate);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tratamento> listTratamentos() {
		// return
		// getSessionFactory().getCurrentSession().createCriteria(Tratamento.class).list();
		return getSessionFactory().getCurrentSession()
				.createQuery("from Tratamento t join fetch t.procedimentoTratamentos").list();
	}

	@Override
	@Cacheable("")
	public Tratamento getTratamento(Integer tratamentoId) {
		Tratamento tratamento = (Tratamento) getSessionFactory().getCurrentSession()
				.get(Tratamento.class, tratamentoId);
		Hibernate.initialize(tratamento.getProcedimentoTratamentos());
		Hibernate.initialize(tratamento.getPagamentos());
		return tratamento;
	}

	@Override
	public void deletarTratamento(Tratamento tratamento) {
		getSessionFactory().getCurrentSession().delete(tratamento);
		getSessionFactory().getCurrentSession().flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Tratamento getLastInsertedRecord() {
		return (Tratamento) getSessionFactory().getCurrentSession().createQuery("from Tratamento ORDER BY id DESC")
				.setMaxResults(1).uniqueResult();
	}
}
