package br.com.dentrio.controller;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.stereotype.Component;

import br.com.dentrio.comum.BaseBean;
import br.com.dentrio.comum.MenuMapEnum;

@Component("tabbedView")
public class TabbedView extends BaseBean {

	private static final long serialVersionUID = 1L;

	public void onTabChange(TabChangeEvent event) {
		TabView tabView = (TabView) event.getComponent();
		getSessionMap().put("ABA_ATIVA", Integer.valueOf(tabView.getActiveIndex()));
		getSessionMap().put("NOME_ABA_ATIVA", event.getTab().getId());
	}

	public void onMenuSelect(String link) {
		String nomeMenu = MenuMapEnum.getByLink(link).getNomeMenuAtivo();
		getSessionMap().put("MENU_ATIVO", nomeMenu);
	}
}
