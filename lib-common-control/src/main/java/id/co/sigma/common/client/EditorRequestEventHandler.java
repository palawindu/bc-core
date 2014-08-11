package id.co.sigma.common.client;



/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class EditorRequestEventHandler<DATA> {
	
	/**
	 * class yang di handler 
	 */
	public abstract Class<DATA> getHandledClass () ;
	
	
	/**
	 * handle Edit request
	 */
	public abstract boolean handleEditRequest (EditorRequestEvent<DATA> dataRequest  , DataEditRequestCallback<DATA> dataEditCompleteCallback) ; 

}
