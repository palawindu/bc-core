package id.co.sigma.common.client.common;

import id.co.sigma.common.client.control.SimpleSearchFilterHandler;
import id.co.sigma.common.client.widget.BaseCommonControlComposite;

/**
 * base template untuk panel title dan search criteria. ini untuk penyeragaman search grid
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public abstract class BaseTitleAndSearchFilterPanel extends BaseCommonControlComposite implements ITitleAndSearchPanelFilter{
	
	
	
	/**
	 * search filter. ini untuk mentrigger apa yang hendak kita cari
	 **/
	private SimpleSearchFilterHandler searchFilterHandler ; 
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see id.co.sigma.common.client.common.ITitleAndSearchPanelFilter#getSearchFilterHandler()
	 */
	@Override
	public SimpleSearchFilterHandler getSearchFilterHandler() {
		return searchFilterHandler;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.client.common.ITitleAndSearchPanelFilter#setSearchFilterHandler(id.co.sigma.common.client.control.SimpleSearchFilterHandler)
	 */
	@Override
	public void setSearchFilterHandler(
			SimpleSearchFilterHandler searchFilterHandler) {
		this.searchFilterHandler = searchFilterHandler;
	}
	

}
