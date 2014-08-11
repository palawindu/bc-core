package id.co.sigma.common.client;

import id.co.sigma.common.client.widget.EditorState;

/**
 * broad cast request edit. agar lepasan proses handler vs caller
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class EditorRequestEvent<DATA> {
	
	
	/**
	 * state, mau  edit, add , atau view readonly
	 */
	private EditorState editorState ;
	
	/**
	 * data yang di proses
	 */
	private DATA dataToEdit ; 

	/**
	 * data yang di proses
	 */
	public DATA getDataToEdit() {
		return dataToEdit;
	}
	/**
	 * data yang di proses
	 */
	public void setDataToEdit(DATA dataToEdit) {
		this.dataToEdit = dataToEdit;
	}
	/**
	 * state, mau  edit, add , atau view readonly
	 */
	public void setEditorState(EditorState editorState) {
		this.editorState = editorState;
	}

	/**
	 * state, mau  edit, add , atau view readonly
	 */
	public EditorState getEditorState() {
		return editorState;
	}
}
