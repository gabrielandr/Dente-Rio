package br.com.dentrio.paciente.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Paciente;

@Repository
@Transactional
public class PacienteDaoImpl implements PacienteDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvarPaciente(Paciente paciente) {
		getSessionFactory().getCurrentSession().merge(paciente);
		getSessionFactory().getCurrentSession().flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Paciente> listPacientes() {
		return getSessionFactory().getCurrentSession().createCriteria(Paciente.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public Paciente getPaciente(Integer pacienteId) {
		Paciente paciente = (Paciente) getSessionFactory().getCurrentSession().get(Paciente.class, pacienteId);
		return paciente;
	}

	@Override
	public void deletarPaciente(Paciente paciente) {
		getSessionFactory().getCurrentSession().delete(paciente);
		getSessionFactory().getCurrentSession().flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Paciente> getPacientesVinculadosDentista(Integer funcionarioId) {
		return getSessionFactory().getCurrentSession()
				.createQuery("SELECT p FROM Paciente p WHERE p.funcionario.id = " + funcionarioId).list();
	}

	@Override
	public Paciente getLastInsertedRecord() {
		return (Paciente) getSessionFactory().getCurrentSession().createQuery("from Paciente ORDER BY id DESC")
				.setMaxResults(1).uniqueResult();
	}
}
