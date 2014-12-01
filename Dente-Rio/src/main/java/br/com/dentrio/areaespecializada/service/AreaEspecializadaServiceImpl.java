package br.com.dentrio.areaespecializada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dentrio.areaespecializada.dao.AreaEspecializadaDao;
import br.com.dentrio.model.AreaEspecializada;

@Service
public class AreaEspecializadaServiceImpl implements AreaEspecializadaService {

	@Autowired
	public AreaEspecializadaDao areaEspecializadaDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addAreaEspecializada(AreaEspecializada area) {
		areaEspecializadaDao.addAreaEspecializada(area);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AreaEspecializada> listAreaEspecializadas() {
		return areaEspecializadaDao.listAreaEspecializadas();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AreaEspecializada getAreaEspecializada(Integer areaId) {
		return areaEspecializadaDao.getAreaEspecializada(areaId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarAreaEspecializada(AreaEspecializada area) {
		areaEspecializadaDao.deletarAreaEspecializada(area);

	}

	public AreaEspecializadaDao getAreaEspecializadaDao() {
		return areaEspecializadaDao;
	}

	public void setAreaEspecializadaDao(AreaEspecializadaDao areaDao) {
		this.areaEspecializadaDao = areaDao;
	}

}
