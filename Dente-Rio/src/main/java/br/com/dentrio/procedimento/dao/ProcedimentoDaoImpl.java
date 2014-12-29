package br.com.dentrio.procedimento.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Procedimento;

@Repository
@Transactional
public class ProcedimentoDaoImpl implements ProcedimentoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvarProcedimento(Procedimento area) {
		sessionFactory.getCurrentSession().merge(area);
		sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Procedimento> getListaProcedimentos() {
		return sessionFactory.getCurrentSession().createCriteria(Procedimento.class).list();
	}

	@Override
	public Procedimento getProcedimento(Integer areaId) {
		Procedimento procedimento = (Procedimento) sessionFactory.getCurrentSession().get(Procedimento.class, areaId);
		Hibernate.initialize(procedimento.getProcedimentoTratamentos());
		return procedimento;
	}

	@Override
	public void deletarProcedimento(Procedimento area) {
		sessionFactory.getCurrentSession().delete(area);
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
