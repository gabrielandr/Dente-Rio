package br.com.dentrio.movimento.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Movimento;

@Repository
@Transactional
public class MovimentoDaoImpl implements MovimentoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void estornarMovimento(Integer movimentoId) {
		getSessionFactory().getCurrentSession()
		.createQuery("UPDATE Movimento mov SET mov.estornado = true WHERE id=" + movimentoId).executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Movimento> listMovimentos() {
		return getSessionFactory().getCurrentSession().createCriteria(Movimento.class).list();
	}

	@Override
	public Movimento getMovimento(Integer movimentoId) {
		return (Movimento) getSessionFactory().getCurrentSession().get(Movimento.class, movimentoId);
	}

	@Override
	public void deletarMovimento(Movimento movimento) {
		getSessionFactory().getCurrentSession().delete(movimento);
		getSessionFactory().getCurrentSession().flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Movimento getLastInsertedRecord() {
		return (Movimento) getSessionFactory().getCurrentSession().createQuery("from Movimento ORDER BY id DESC")
				.setMaxResults(1).uniqueResult();
	}
}
