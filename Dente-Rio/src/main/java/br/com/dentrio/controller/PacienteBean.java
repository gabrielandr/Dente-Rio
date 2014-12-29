package br.com.dentrio.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import br.com.dentrio.comum.Constantes;
import br.com.dentrio.funcionario.service.FuncionarioService;
import br.com.dentrio.model.Funcionario;
import br.com.dentrio.model.Paciente;
import br.com.dentrio.paciente.service.PacienteService;
import br.com.dentrio.tratamento.service.TratamentoService;
import br.com.dentrio.util.jsf.FacesUtil;

@Controller("pacienteBean")
@ViewScoped
public class PacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	PacienteService pacienteService;
	@Autowired
	FuncionarioService funcionarioService;
	@Autowired
	TratamentoService tratamentoService;

	private Date data;
	private Paciente paciente;

	@PostConstruct
	private void inicializar() {
		limpar();
		// this.listaPacientes = new ArrayList<Paciente>();
		// this.listaPacientes.addAll(pacienteService.listPacientes());
	}

	private void limpar() {
		this.paciente = new Paciente();
	}

	public String novoPaciente() {
		limpar();
		return "cadastroPaciente?faces-redirect=true";
	}

	public String salvarPaciente() {
		try {
			setarTimestamps();
			pacienteService.salvarPaciente(paciente);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Paciente adicionado com Sucesso!");
			inicializar();
			// relacionarTratamento();
			return "listarPacientes?faces-redirect=true";

		} catch (DataAccessException e) {
			FacesUtil.addErrorMessage(Constantes.ERRO, "Erro ao tentar salvar o registro!");
			return null;
		}
	}

	public String editarPaciente(Integer pacienteId) {
		try {
			paciente = pacienteService.getPaciente(pacienteId);
			return "editarPaciente?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO,
					"Erro ao tentar editar o paciente selecionado, atualize a página e tente novamente.");
			return null;
		}
	}

	public String deletarPaciente(Integer pacienteId) {
		try {
			Paciente paciente = pacienteService.getPaciente(pacienteId);
			pacienteService.deletarPaciente(paciente);
			FacesUtil.addSuccessMessage(Constantes.SUCESSO, "Paciente deletado com Sucesso!");
			inicializar();
			return "listarPacientes?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO,
					"Erro ao tentar deletar o paciente, atualize a página e tente novamente.");
			return null;
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Paciente> getListaPacientes() {
		return pacienteService.listPacientes();
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<Funcionario> getlistaFuncionarios() {
		return funcionarioService.getListaFuncionarios();
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public List<Funcionario> getListaFuncionarios() {
		return funcionarioService.getListaFuncionarios();
	}

	public void setarTimestamps() {
		if (paciente.getCreatedAt() == null) {
			paciente.setCreatedAt(new Date());
			paciente.setUpdatedAt(new Date());
		}
		paciente.setUpdatedAt(new Date());
	}
}
