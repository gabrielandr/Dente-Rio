package br.com.dentrio.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.comum.RolesEnum;
import br.com.dentrio.funcionario.service.FuncionarioService;
import br.com.dentrio.model.Funcionario;
import br.com.dentrio.util.jsf.FacesUtil;

@Controller("loginBean")
@Scope("session")
public class LoginBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private Funcionario currentUser;

	@Autowired
	FuncionarioService funcionarioService;

	private void limparFormLogin() {
		login = password = "";
	}

	@PostConstruct
	public void inicializar() {
		currentUser = null;
	}

	public void login() {
		Funcionario funcionario = funcionarioService.retornaUsuarioPeloLogin(login);
		if (null != funcionario) {
			if (password.equalsIgnoreCase(funcionario.getSenha())) {
				efetuarLogin(funcionario);
				currentUser = funcionario;
				limparFormLogin();
				FacesUtil.addSuccessMessage("Bem vindo!", "Logado como '" + funcionario.getNomePessoa() + "'");
				FacesUtil.redirect("/pages/paciente/listarPacientes.xhtml");
			} else {
				password = "";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha não está correta.", null));
			}
		} else {
			login = "";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "O login não existe ou foi digitado errado.", null));
		}
	}

	public void logout() {
		efetuarLogout();
		currentUser = null;
		FacesUtil.redirect(null);
	}

	public Boolean isLogado() {
		if (getSessionMap().get(Constantes.CURRENT_USER) != null) {
			return true;
		}
		return false;
	}

	public Boolean isAdmin() {
		Funcionario funcionario = (Funcionario) getSessionMap().get(Constantes.CURRENT_USER);
		if (funcionario != null && funcionario.getRole() == RolesEnum.ADMIN) {
			return true;
		}
		return false;
	}

	public Boolean isSocio() {
		Funcionario funcionario = (Funcionario) getSessionMap().get(Constantes.CURRENT_USER);
		if (funcionario != null && funcionario.getRole() == RolesEnum.SOCIO) {
			return true;
		}
		return false;
	}

	public Boolean isSecretaria() {
		Funcionario funcionario = (Funcionario) getSessionMap().get(Constantes.CURRENT_USER);
		if (funcionario != null && funcionario.getRole() == RolesEnum.SECRETARIA) {
			return true;
		}
		return false;
	}

	public Boolean isDentista() {
		Funcionario funcionario = (Funcionario) getSessionMap().get(Constantes.CURRENT_USER);
		if (funcionario != null && funcionario.getRole() == RolesEnum.DENTISTA) {
			return true;
		}
		return false;
	}

	public void temPermissao() {
		if (!isLogado()) {
			FacesUtil.addErrorMessage(Constantes.ERRO, "Você deve fazer o login para acessar esta página!");
			FacesUtil.redirect("/home.xhtml");

		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	/**
	 * @return the currentUser
	 */
	public Funcionario getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(Funcionario currentUser) {
		this.currentUser = currentUser;
	}

}
