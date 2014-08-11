package id.co.sigma.common.client;

import com.google.gwt.core.client.GWT;

import id.co.sigma.common.client.control.MainPanelStackControl;
import id.co.sigma.common.client.control.ViewScreenMode;
import id.co.sigma.common.client.widget.BaseEditorPanel;
import id.co.sigma.common.client.widget.EditorState;

/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class EditorRequestEventHandlerWithCacheableEditor<DATA> extends EditorRequestEventHandler<DATA>{

	private BaseEditorPanel<DATA> viewReadonlyPanel ; 
	private BaseEditorPanel<DATA> editExistingPanel ;
	private BaseEditorPanel<DATA> addNewDataPanel ;
	
	
	@Override
	public boolean handleEditRequest(EditorRequestEvent<DATA> dataRequest,
			DataEditRequestCallback<DATA> dataEditCompleteCallback) {
		GWT.log("handled class : " + getHandledClass() +", data class : " + dataRequest.getDataToEdit().getClass());
		
		if (! getHandledClass().equals(  dataRequest.getDataToEdit().getClass() ))
			return false;
		if ( EditorState.viewReadonly.equals(dataRequest.getEditorState())){
			if ( viewReadonlyPanel== null)
				viewReadonlyPanel = instantiateEditor(); 
			viewReadonlyPanel.setAfterEditOrViewCallback(dataEditCompleteCallback);
			viewReadonlyPanel.viewDataAsReadOnly( dataRequest.getDataToEdit());
			MainPanelStackControl.getInstance().putPanel(viewReadonlyPanel, getViewScreenMode ());
		}
		else if ( EditorState.edit.equals(dataRequest.getEditorState())){
			//if ( editExistingPanel== null)
			editExistingPanel = instantiateEditor(); 
			editExistingPanel.setAfterEditOrViewCallback(dataEditCompleteCallback);
			editExistingPanel.editExistingData(dataRequest.getDataToEdit());
			MainPanelStackControl.getInstance().putPanel(editExistingPanel, getViewScreenMode ());
		}
		else  {
			if ( addNewDataPanel== null)
				addNewDataPanel = instantiateEditor(); 
			addNewDataPanel.setAfterEditOrViewCallback(dataEditCompleteCallback);
			addNewDataPanel.addAndEditNewData(dataRequest.getDataToEdit());
			MainPanelStackControl.getInstance().putPanel(addNewDataPanel, getViewScreenMode ());
		}
		return true ; 
	}
	
	
	protected abstract BaseEditorPanel<DATA> instantiateEditor()  ;
	
	
	/**
	 * mode screen
	 */
	protected abstract ViewScreenMode getViewScreenMode () ; 
}
