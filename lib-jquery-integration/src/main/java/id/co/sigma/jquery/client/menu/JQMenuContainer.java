package id.co.sigma.jquery.client.menu;

/**
 * interface untuk parent menu container
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface JQMenuContainer<MENUDATA> {

	/**
	 * append sub menu ke dalam current node
	 * @param childMenu child menu untuk di append ke menu
	 **/
	public void appendChildMenu (JQBaseMenuWidget<MENUDATA> childMenu); 
}
