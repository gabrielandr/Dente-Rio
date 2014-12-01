package br.com.dentrio.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.funcionario.dao.FuncionarioDao;
import br.com.dentrio.model.Funcionario;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    public FuncionarioDao funcionarioDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFuncionario(Funcionario funcionario) {
	funcionarioDao.addFuncionario(funcionario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editarFuncionario(Funcionario funcionario) {
	funcionarioDao.editarFuncionario(funcionario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Funcionario> listFuncionarios() {
	return funcionarioDao.listFuncionarios();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Funcionario getFuncionario(Integer funcionarioId) {
	return funcionarioDao.getFuncionario(funcionarioId);
    }

    //
    /**
     * {@inheritDoc}
     */
    @Override
    public void deletarFuncionario(Funcionario funcionario) {
	funcionarioDao.deletarFuncionario(funcionario);

    }

    public FuncionarioDao getFuncionarioDao() {
	return funcionarioDao;
    }

    public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
	this.funcionarioDao = funcionarioDao;
    }

}
