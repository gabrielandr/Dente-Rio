package br.com.dentrio.funcionario.service;

import java.util.List;

import br.com.dentrio.model.Funcionario;

public interface FuncionarioService {

	public void addFuncionario(Funcionario funcionario);

	public void editarFuncionario(Funcionario funcionario);

	public List<Funcionario> getListaFuncionarios();

	public Funcionario getFuncionario(Integer funcionarioId);

	public void deletarFuncionario(Funcionario funcionario);

	public Funcionario retornaUsuarioPeloLogin(String login);

	public List<Funcionario> retornaListaDentistas();
}
