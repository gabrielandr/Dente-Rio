package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import br.com.dentrio.areaespecializada.service.AreaEspecializadaService;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.model.AreaEspecializada;
import br.com.dentrio.util.jsf.FacesUtil;

@Component("areaEspecializadaBean")
public class AreaEspecializadaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ERROR = "error";

	@Autowired
	AreaEspecializadaService areaService;

	private AreaEspecializada area;
	private List<AreaEspecializada> listaAreasEspecializadas;

	@PostConstruct
	private void inicializar() {
		limpar();
		this.listaAreasEspecializadas = null;
		this.listaAreasEspecializadas = areaService.listAreaEspecializadas();
	}

	public void limpar() {
		this.area = new AreaEspecializada();
	}

	public String novaAreaEspecializada() {
		limpar();
		return "formAreaEspecializada?faces-redirect=true";
	}

	public String salvarAreaEspecializada() {
		try {
			setarTimestamps();
			areaService.addAreaEspecializada(area);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "AreaEspecializada adicionada com Sucesso!");
			inicializar();
			return "listarAreasEspecializadas?faces-redirect=true";

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Ocoreu um erro ao tentar salvar, por favor tente novamente!");
			return null;
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Uma área especializada com o nome '" + area.getNomeAreaEspecializada()
					+ "' já existe no banco de dados!");
			return null;
		}
	}

	public String editarAreaEspecializada(Integer areaId) {
		try {
			area = areaService.getAreaEspecializada(areaId);
			return "formAreaEspecializada?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String deletarAreaEspecializada(Integer areaId) {
		try {
			AreaEspecializada area = areaService.getAreaEspecializada(areaId);
			areaService.deletarAreaEspecializada(area);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "AreaEspecializada deletada com Sucesso!");
			inicializar();
			return "listarAreasEspecializadas?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar!");
			return null;
		}
	}

	public void resetForm() {
		area = new AreaEspecializada();
	}

	/**
	 * @return the areaService
	 */
	public AreaEspecializadaService getAreaService() {
		return areaService;
	}

	/**
	 * @param areaService
	 *            the areaService to set
	 */
	public void setAreaService(AreaEspecializadaService areaService) {
		this.areaService = areaService;
	}

	/**
	 * @return the area
	 */
	public AreaEspecializada getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(AreaEspecializada area) {
		this.area = area;
	}

	/**
	 * @return the listaAreasEspecializadas
	 */
	public List<AreaEspecializada> getListaAreasEspecializadas() {
		return listaAreasEspecializadas;
	}

	/**
	 * @param listaAreasEspecializadas
	 *            the listaAreasEspecializadas to set
	 */
	public void setListaAreasEspecializadas(List<AreaEspecializada> listaAreasEspecializadas) {
		this.listaAreasEspecializadas = listaAreasEspecializadas;
	}

	public void setarTimestamps() {
		if (area.createdAt == null) {
			area.setCreatedAt(new Date());
			area.setUpdatedAt(new Date());
		}
		area.setUpdatedAt(new Date());
	}
}
