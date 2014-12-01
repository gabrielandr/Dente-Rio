package br.com.dentrio.areaespecializada.dao;

import java.util.List;

import br.com.dentrio.model.AreaEspecializada;

public interface AreaEspecializadaDao {

	public void addAreaEspecializada(AreaEspecializada area);

	public List<AreaEspecializada> listAreaEspecializadas();

	public AreaEspecializada getAreaEspecializada(Integer areaId);

	public void deletarAreaEspecializada(AreaEspecializada area);

}
