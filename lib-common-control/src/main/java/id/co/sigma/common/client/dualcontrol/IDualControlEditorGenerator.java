package id.co.sigma.common.client.dualcontrol;

import id.co.sigma.common.data.app.DualControlEnabledData;



/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public interface IDualControlEditorGenerator <DATA extends DualControlEnabledData<?, ?>>{
	
	/**
	 * ini untuk instantiate editor panel
	 */
	public IDualControlEditor<DATA> instantiateEditorPanel () ; 
	
	/**
	 * class yang di handle generator ini
	 */
	public Class<DATA> getHandledClass () ; 

}
