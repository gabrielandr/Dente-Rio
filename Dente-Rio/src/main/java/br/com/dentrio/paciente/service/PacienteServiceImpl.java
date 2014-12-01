package br.com.dentrio.paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.model.Paciente;
import br.com.dentrio.paciente.dao.PacienteDao;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	public PacienteDao pacienteDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvarPaciente(Paciente paciente) {
		pacienteDao.salvarPaciente(paciente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Paciente> listPacientes() {
		return pacienteDao.listPacientes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Paciente getPaciente(Integer pacienteId) {
		return pacienteDao.getPaciente(pacienteId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarPaciente(Paciente paciente) {
		pacienteDao.deletarPaciente(paciente);
	}

	@Override
	public List<Paciente> getPacientesVinculadosFuncionario(Integer funcionarioId) {
		return pacienteDao.getPacientesVinculadosDentista(funcionarioId);
	}

	public PacienteDao getPacienteDao() {
		return pacienteDao;
	}

	public void setPacienteDao(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	@Override
	public Paciente getLastInsertedRecord() {
		return pacienteDao.getLastInsertedRecord();
	}

}
