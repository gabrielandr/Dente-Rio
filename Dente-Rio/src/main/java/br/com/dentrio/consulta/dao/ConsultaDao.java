package br.com.dentrio.consulta.dao;

import java.util.List;

import br.com.dentrio.model.Consulta;

public interface ConsultaDao {

	public void addConsulta(Consulta consulta);

	public List<Consulta> listConsultas();

	public Consulta getConsulta(Integer consultaId);

	public void deletarConsulta(Consulta consulta);

}
