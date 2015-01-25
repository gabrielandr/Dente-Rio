package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import br.com.dentrio.areaespecializada.service.AreaEspecializadaService;
import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.comum.RolesEnum;
import br.com.dentrio.comum.TipoFuncionarioEnum;
import br.com.dentrio.funcionario.service.FuncionarioService;
import br.com.dentrio.model.AreaEspecializada;
import br.com.dentrio.model.Funcionario;
import br.com.dentrio.paciente.service.PacienteService;
import br.com.dentrio.util.jsf.FacesUtil;

@Controller("funcionarioBean")
public class FuncionarioBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	FuncionarioService funcionarioService;
	@Autowired
	PacienteService pacienteService;
	@Autowired
	AreaEspecializadaService areaEspecializadaService;

	private Funcionario funcionario;
	private AreaEspecializada areaEspecializada;
	private List<TipoFuncionarioEnum> listaTiposFuncionario;
	private List<RolesEnum> listaRoles;
	private List<Funcionario> listFuncionarios;

	@PostConstruct
	private void inicializar() {
		limpar();
		this.listFuncionarios = null;
		this.listFuncionarios = funcionarioService.getListaFuncionarios();
		carregarCombos();
	}

	private void carregarCombos() {
		this.listaTiposFuncionario = Arrays.asList(TipoFuncionarioEnum.values());
		// this.listaAreasEspecializadas =
		// areaEspecializadaService.listAreaEspecializadas();
	}

	public void limpar() {
		this.funcionario = new Funcionario();
		this.areaEspecializada = new AreaEspecializada();
		getFuncionario().setTipoFuncionario(null);
	}

	public String novoFuncionario() {
		inicializar();
		return "formFuncionario?faces-redirect=true";
	}

	public String salvarFuncionario() {
		try {
			setarTimestamps();
			if (funcionario.getTipoFuncionario() != TipoFuncionarioEnum.DENTISTA) {
				funcionario.setCro("");
				funcionario.setSocio(false);
			}
			funcionarioService.addFuncionario(funcionario);
			FacesUtil.addSuccessMessage("Sucesso", "Funcionario adicionado com Sucesso!");
			inicializar();
			return "listarFuncionarios?faces-redirect=true";

		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Ocorreu um problema ao acessar o banco de dados!");
			return null;
		}
	}

	public String editarFuncionario(Integer funcionarioId) {
		try {
			funcionario = funcionarioService.getFuncionario(funcionarioId);
			// carregarCombos();
			return "formFuncionario?faces-redirect=true&funcionarioId=" + funcionarioId;
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro!", "Erro ao tentar buscar o funcionario selecionado, tente novamente.");
			return null;
		}
	}

	public String deletarFuncionario(Integer funcionarioId) {
		try {
			// deletarDependenciasPaciente(funcionarioId);
			Funcionario funcionario = funcionarioService.getFuncionario(funcionarioId);
			funcionarioService.deletarFuncionario(funcionario);
			FacesUtil.addSuccessMessage("Sucesso", "Funcionario deletado com Sucesso!");
			inicializar();
			return "listarFuncionarios?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Ocorreu um erro ao deletar, tente novamente!");
			return null;
		}
	}

	// /**
	// * Seta o campo FK dos pacientes vinculados ao funcionario a ser deletado
	// * para null antes de deletar o funcionario para não dar erro de Foreign
	// Key
	// * Constraint.
	// *
	// * @param funcionarioId
	// */
	// private void deletarDependenciasPaciente(Integer funcionarioId) {
	// List<Paciente> pacientes =
	// pacienteService.getPacientesVinculadosFuncionario(funcionarioId);
	//
	// if (!pacientes.isEmpty()) {
	// for (Paciente paciente : pacientes) {
	// paciente.setFuncionario(null);
	// pacienteService.salvarPaciente(paciente);
	// }
	// }
	// }

	public String retornaTipoFuncionario() {
		return funcionario.getSocio() == true ? "Sócio" : funcionario.getTipoFuncionario().getDescricao();
	}

	public void resetForm() {
		inicializar();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<TipoFuncionarioEnum> getListaTiposFuncionario() {
		return listaTiposFuncionario;
	}

	public void setListaTiposFuncionario(List<TipoFuncionarioEnum> listaTiposFuncionario) {
		this.listaTiposFuncionario = listaTiposFuncionario;
	}

	public List<Funcionario> getListFuncionarios() {
		return listFuncionarios;
	}

	public void setListFuncionarios(List<Funcionario> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}

	/**
	 * @return the listaAreasEspecializadas
	 */
	public List<AreaEspecializada> getListaAreasEspecializadas() {
		return areaEspecializadaService.listAreaEspecializadas();
	}

	/**
	 * @return the areaEspecializadaService
	 */
	public AreaEspecializadaService getAreaEspecializadaService() {
		return areaEspecializadaService;
	}

	/**
	 * @param areaEspecializadaService
	 *            the areaEspecializadaService to set
	 */
	public void setAreaEspecializadaService(AreaEspecializadaService areaEspecializadaService) {
		this.areaEspecializadaService = areaEspecializadaService;
	}

	/**
	 * @return the areaEspecializada
	 */
	public AreaEspecializada getAreaEspecializada() {
		return areaEspecializada;
	}

	/**
	 * @param areaEspecializada
	 *            the areaEspecializada to set
	 */
	public void setAreaEspecializada(AreaEspecializada areaEspecializada) {
		this.areaEspecializada = areaEspecializada;
	}

	public void setarTimestamps() {
		if (funcionario.getCreatedAt() == null) {
			funcionario.setCreatedAt(new Date());
			funcionario.setUpdatedAt(new Date());
		}
		funcionario.setUpdatedAt(new Date());
	}

	/**
	 * @return the listaRoles
	 */
	public List<RolesEnum> getListaRoles() {
		return RolesEnum.listaTodos();
	}
}
