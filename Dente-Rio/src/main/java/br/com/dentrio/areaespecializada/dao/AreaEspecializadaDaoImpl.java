package br.com.dentrio.areaespecializada.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.AreaEspecializada;

@Repository
@Transactional
public class AreaEspecializadaDaoImpl implements AreaEspecializadaDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAreaEspecializada(AreaEspecializada area) {
		getSessionFactory().getCurrentSession().merge(area);
		getSessionFactory().getCurrentSession().flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AreaEspecializada> listAreaEspecializadas() {
		return getSessionFactory().getCurrentSession().createCriteria(AreaEspecializada.class).list();
	}

	@Override
	public AreaEspecializada getAreaEspecializada(Integer areaId) {
		return (AreaEspecializada) getSessionFactory().getCurrentSession().get(AreaEspecializada.class, areaId);
	}

	@Override
	public void deletarAreaEspecializada(AreaEspecializada area) {
		getSessionFactory().getCurrentSession().delete(area);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
