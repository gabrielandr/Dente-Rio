package br.com.dentrio.consulta.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Consulta;

@Repository
@Transactional
public class ConsultaDaoImpl implements ConsultaDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addConsulta(Consulta consulta) {
		getSessionFactory().getCurrentSession().merge(consulta);
		getSessionFactory().getCurrentSession().flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Consulta> listConsultas() {
		return getSessionFactory().getCurrentSession().createCriteria(Consulta.class).list();
	}

	@Override
	public Consulta getConsulta(Integer consultaId) {
		return (Consulta) getSessionFactory().getCurrentSession().get(Consulta.class, consultaId);
		// return entityManager.find(Consulta.class, consultaId);
		// return null;
	}

	@Override
	public void deletarConsulta(Consulta consulta) {
		getSessionFactory().getCurrentSession().delete(consulta);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
