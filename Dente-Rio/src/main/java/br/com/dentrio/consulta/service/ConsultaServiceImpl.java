package br.com.dentrio.consulta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.consulta.dao.ConsultaDao;
import br.com.dentrio.model.Consulta;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	public ConsultaDao consultaDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addConsulta(Consulta consulta) {
		consultaDao.addConsulta(consulta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Consulta> listConsultas() {
		return consultaDao.listConsultas();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Consulta getConsulta(Integer consultaId) {
		return consultaDao.getConsulta(consultaId);
	}

	//
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarConsulta(Consulta consulta) {
		consultaDao.deletarConsulta(consulta);

	}

	public ConsultaDao getConsultaDao() {
		return consultaDao;
	}

	public void setConsultaDao(ConsultaDao consultaDao) {
		this.consultaDao = consultaDao;
	}

}
