package id.co.sigma.common.client.security.menu;

import id.co.sigma.common.security.menu.ApplicationMenuSecurity;
import id.co.sigma.jquery.client.menu.MenuClickHandler;

/**
 * Link Menu
 * @author Gede Sutarsa
 * @author I Gede Mahendra
 * @since Apr 12, 2013, 3:46:04 PM
 * @version $Id
 */
public interface ILinkMenu extends IBaseMenu{
	
	
	
	/**
	 * menaruh menu click handler ke dalam menu lowest level
	 **/
	public void setMenuClickHandler(MenuClickHandler<ApplicationMenuSecurity> clickHandler);

}
