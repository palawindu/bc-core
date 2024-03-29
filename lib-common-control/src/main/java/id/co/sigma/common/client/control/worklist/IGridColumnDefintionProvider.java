package id.co.sigma.common.client.control.worklist;

import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;

/**
 * column definition provider
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IGridColumnDefintionProvider<DATA> {
	
	
	/**
	 * column defs<br/>
	 * Ini dipergunakan dalam grid
	 **/
	public abstract BaseColumnDefinition<DATA, ?>[] getColumnDefinitions(); 

}
