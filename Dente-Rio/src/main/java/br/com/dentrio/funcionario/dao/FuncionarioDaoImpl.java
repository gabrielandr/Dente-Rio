package br.com.dentrio.funcionario.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dentrio.model.Funcionario;

@Repository
@Transactional
public class FuncionarioDaoImpl implements FuncionarioDao, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addFuncionario(Funcionario funcionario) {
	getSessionFactory().getCurrentSession().merge(funcionario);
    }

    @Override
    public void editarFuncionario(Funcionario funcionario) {
	// Funcionario funcionarioToUpdate =
	// getFuncionario(funcionario.getId());
	// funcionarioToUpdate.setFirstName(funcionario.getFirstName());
	// funcionarioToUpdate.setLastName(funcionario.getLastName());
	// funcionarioToUpdate.setTelefone(funcionario.getTelefone());
	// funcionarioToUpdate.setEmail(funcionario.getEmail());
	// sessionFactory.getCurrentSession().update(funcionarioToUpdate);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Funcionario> listFuncionarios() {
	return getSessionFactory().getCurrentSession().createCriteria(Funcionario.class)
		.list();
    }

    @Override
    public Funcionario getFuncionario(Integer funcionarioId) {
	return (Funcionario) getSessionFactory().getCurrentSession().get(
		Funcionario.class, funcionarioId);
	// return entityManager.find(Funcionario.class, funcionarioId);
	// return null;
    }

    @Override
    public void deletarFuncionario(Funcionario funcionario) {
	getSessionFactory().getCurrentSession().delete(funcionario);
	getSessionFactory().getCurrentSession().flush();
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

}
