package br.com.dentrio.funcionario.dao;

import java.util.List;

import br.com.dentrio.model.Funcionario;

public interface FuncionarioDao {

    public void addFuncionario(Funcionario funcionario);

    public void editarFuncionario(Funcionario funcionario);

    public List<Funcionario> listFuncionarios();

    public Funcionario getFuncionario(Integer funcionarioId);

    public void deletarFuncionario(Funcionario funcionario);

}
