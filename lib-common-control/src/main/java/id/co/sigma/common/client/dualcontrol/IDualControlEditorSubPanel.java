package id.co.sigma.common.client.dualcontrol;

import id.co.sigma.common.client.control.ITransformableToReadonlyLabel;
import id.co.sigma.common.data.app.DualControlEnabledData;

/**
 * sub panel dari dual control editor panel. 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IDualControlEditorSubPanel<DATA extends DualControlEnabledData<?, ?>> extends ITransformableToReadonlyLabel {

	
	/**
	 * render data ke dalam control. untuk proses edit
	 * @param dataToEdit data yang di edit
	 * @param editorState editor state. approval, edit atau view readonly
	 * @param addNew flag data add new. ini di perhatikan kalau value : id.co.sigma.common.client.dualcontrol.DualControlEditorState.CREATE_FOR_APPROVAL
	 */
	public void renderData ( DATA dataToEdit  ,    DualControlEditorState editorState , boolean addNew ); 
	
	
	/**
	 * memaca data dari control
	 */
	public void fetchDataFromControl (DATA dataToEdit) ;
	
	
	/**
	 * memvalidasi entry
	 * @return null = no error. not null berarti ada validasi yang gagal
	 */
	public String[] runMandatoryValidation () ; 
}
