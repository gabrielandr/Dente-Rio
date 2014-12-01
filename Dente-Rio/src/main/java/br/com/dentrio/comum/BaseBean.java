package br.com.dentrio.comum;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import com.sun.faces.context.SessionMap;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 750309574357642983L;

	private SessionMap sessionMap;

	public static Object getManagedBean(final String beanName) {
	    FacesContext fc = FacesContext.getCurrentInstance();
	    Object bean;
	    
	    try {
	        ELContext elContext = fc.getELContext();
	        bean = elContext.getELResolver().getValue(elContext, null, beanName);
	    } catch (RuntimeException e) {
	        throw new FacesException(e.getMessage(), e);
	    }

	    if (bean == null) {
	        throw new FacesException("Managed bean com o nome '" + beanName
	            + "' não foi encontrado.");
	    }

	    return bean;
	}

	/**
	 * @return the sessionMap
	 */
	public SessionMap getSessionMap() {
		sessionMap = (SessionMap) FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return sessionMap;
	}

	/**
	 * @param sessionMap
	 *            the sessionMap to set
	 */
	public void setSessionMap(SessionMap sessionMap) {
		this.sessionMap = sessionMap;
	}

}
