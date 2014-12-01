package br.com.dentrio.paciente.dao;

import java.util.List;

import br.com.dentrio.model.Paciente;

public interface PacienteDao {

	public void salvarPaciente(Paciente paciente);

	public List<Paciente> listPacientes();

	public Paciente getPaciente(Integer pacienteId);

	public void deletarPaciente(Paciente paciente);

	public List<Paciente> getPacientesVinculadosDentista(Integer funcionarioId);

	public Paciente getLastInsertedRecord();

}
