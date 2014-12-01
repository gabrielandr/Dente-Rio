package br.com.dentrio.sugestao.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Sugestao;

@Repository
@Transactional
public class SugestaoDaoImpl implements SugestaoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addSugestao(Sugestao sugestao) {
		getSessionFactory().getCurrentSession().merge(sugestao);
		getSessionFactory().getCurrentSession().flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sugestao> listSugestaos() {
		return getSessionFactory().getCurrentSession().createCriteria(Sugestao.class).list();
	}

	@Override
	public Sugestao getSugestao(Integer sugestaoId) {
		return (Sugestao) getSessionFactory().getCurrentSession().get(Sugestao.class, sugestaoId);
		// return entityManager.find(Sugestao.class, sugestaoId);
		// return null;
	}

	@Override
	public void deletarSugestao(Sugestao sugestao) {
		getSessionFactory().getCurrentSession().delete(sugestao);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
