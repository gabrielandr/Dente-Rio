package br.com.dentrio.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		try{
		funcionarioDao.addFuncionario(funcionario);
		} catch(DataIntegrityViolationException e){
			throw new DataIntegrityViolationException("dupl login");
		}
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
	public List<Funcionario> getListaFuncionarios() {
		return funcionarioDao.getListaFuncionarios();
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

	@Override
	public Funcionario retornaUsuarioPeloLogin(String login) {
		return this.funcionarioDao.retornaUsuarioPeloLogin(login);
	}

	@Override
	public List<Funcionario> retornaListaDentistas() {
		return this.funcionarioDao.retornaListaDentistas();
	}
}
