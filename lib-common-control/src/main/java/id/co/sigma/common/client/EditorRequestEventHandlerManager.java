package id.co.sigma.common.client;

import id.co.sigma.common.client.widget.EditorState;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;

/**
 * handler manager untuk register event
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final class EditorRequestEventHandlerManager {
	
	
	private static EditorRequestEventHandlerManager instance ; 
	
	@SuppressWarnings("rawtypes")
	private ArrayList<EditorRequestEventHandler> handlers ;
	
	
	@SuppressWarnings("rawtypes")
	private EditorRequestEventHandlerManager() {
		handlers = new ArrayList<EditorRequestEventHandler>();
	}
	 
	/**
	 * submit data edit reqeust
	 */
	public <DATA> void submitEditDataRequest (DATA dataToEditOrView , EditorState editorState   ) {
		submitEditDataRequest(dataToEditOrView, editorState , new DataEditRequestCallback<DATA>(){
			@Override
			public void onEditComplete(DATA afterEditedData) {
				
			}
		});
	}
	/**
	 * submit data edit reqeust
	 */
	public <DATA> void submitEditDataRequest (DATA dataToEditOrView , EditorState editorState   , DataEditRequestCallback<DATA> dataEditCompleteCallback) {
		EditorRequestEvent<DATA>  d = new EditorRequestEvent<DATA>(); 
		d.setDataToEdit(dataToEditOrView);
		d.setEditorState(editorState);
		submitEditDataRequest(d  , dataEditCompleteCallback);
		
	}
	/**
	 * request agar data di edit
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <DATA> void submitEditDataRequest (EditorRequestEvent<DATA> dataRequest  , DataEditRequestCallback<DATA> dataEditCompleteCallback) {
		for ( EditorRequestEventHandler scn : handlers) {
			if ( scn.handleEditRequest(dataRequest, dataEditCompleteCallback))
				return  ; 
		}
		GWT.log("tidak ada handle yang match untuk : " + dataRequest.getDataToEdit().getClass().getName());
		
	}
	
	
	/**
	 * register handler ke dalam manager. agar editor ada yang nyaut
	 */
	public<DATA> void register (EditorRequestEventHandler<DATA> handler) {
		this.handlers.add(handler); 
	}
	
	public static EditorRequestEventHandlerManager getInstance() {
		if ( instance == null)
			instance = new EditorRequestEventHandlerManager(); 
		return instance;
	}

}
