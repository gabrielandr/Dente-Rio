package br.com.dentrio.util.jsf;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.dentrio.comum.Constantes;

public class FacesUtil {

	public static void addSuccessMessage(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(Constantes.SEVERIDADE_INFO, summary, detail));
		context.getExternalContext().getFlash().setKeepMessages(true);

	}

	public static void addErrorMessage(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(Constantes.SEVERIDADE_ERROR, summary, detail));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public static void throwErroValidacao(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", mensagem));
		context.validationFailed();
	}

	/**
	 * Método para redirecionar página. Ao colocar a URL no parametro, clocar
	 * .xhtml no final(ex: listagem.xhtml)
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static void redirect(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(Constantes.ERRO, "Erro ao redirecionar a pagina!");
		}
	}

	public static String getRequestParam(String parametro) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.get(parametro);
	}

	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}

	public static void callJavaScript(String metodo, String str) {
		RequestContext context = RequestContext.getCurrentInstance();
		if (str == null) {
			context.execute(metodo + "()");
		} else {
			context.execute(metodo + "('" + str + "');");
		}
	}

	public static void resetForm(String tagId) {
		RequestContext.getCurrentInstance().reset(tagId);
	}

}