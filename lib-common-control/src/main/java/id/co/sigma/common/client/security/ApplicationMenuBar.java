package id.co.sigma.common.client.security;

import java.util.List;

import id.co.sigma.common.client.app.ApplicationMenu;
import id.co.sigma.jquery.client.menu.JQLeafMenuWidget;
import id.co.sigma.jquery.client.menu.JQMenuBar;
import id.co.sigma.jquery.client.menu.MenuClickHandler;




/**
 * application menubar sigma
 **/
public class ApplicationMenuBar<KEY> extends JQMenuBar<ApplicationMenu<KEY>>{
	
	public ApplicationMenuBar(){
		super();
	}
	
	
	/**
	 * worker untuk render menu. di berikan di sini level 0. data di render dengan rekursif
	 **/
	public void renderMenu( MenuClickHandler<ApplicationMenu<KEY>> clickHandler,  List<ApplicationMenu<KEY>> level0Menus){
		for ( ApplicationMenu<KEY> menu: level0Menus){
			if ( menu.isHaveChildren()){
				ExtendedBranchMenu<KEY> carang = new ExtendedBranchMenu<KEY>(clickHandler, menu);
				this.appendMenu(carang);
			}else{
				JQLeafMenuWidget<ApplicationMenu<KEY>> mnu = new JQLeafMenuWidget<ApplicationMenu<KEY>>(menu.getLabel());
				mnu.setMenuData(menu);
				mnu.assignClickHandler(clickHandler);
				this.appendMenu(mnu);
				
			}
		}
		this.renderMenu();
	}

}
