package br.com.dentrio.paciente.service;

import java.util.List;

import br.com.dentrio.model.Paciente;

public interface PacienteService {

	public void salvarPaciente(Paciente paciente);

	public List<Paciente> listPacientes();

	public Paciente getPaciente(Integer pacienteId);

	public void deletarPaciente(Paciente paciente);

	public List<Paciente> getPacientesVinculadosFuncionario(Integer funcionarioId);

	public Paciente getLastInsertedRecord();

}
