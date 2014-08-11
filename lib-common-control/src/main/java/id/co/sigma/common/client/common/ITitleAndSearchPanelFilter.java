package id.co.sigma.common.client.common;

import id.co.sigma.common.client.control.SimpleSearchFilterHandler;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ITitleAndSearchPanelFilter {
	public SimpleSearchFilterHandler getSearchFilterHandler();
	public void setSearchFilterHandler(
			SimpleSearchFilterHandler searchFilterHandler);

}
