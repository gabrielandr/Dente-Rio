package br.com.dentrio.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.Constantes;
import br.com.dentrio.comum.MenuMapEnum;

@ManagedBean(name = "tabbedView")
@RequestScoped
public class TabbedView extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	public void onTabChange(TabChangeEvent event) {
		TabView tabView = (TabView) event.getComponent();
		getSessionMap().put(Constantes.ABA_ATIVA, tabView.getActiveIndex());
	}

	public void onMenuSelect(String link) {
		String nomeMenu = MenuMapEnum.getByLink(link).getNomeMenuAtivo();
		getSessionMap().put(Constantes.MENU_ATIVO, nomeMenu);
	}

}
