package id.co.sigma.common.client.form;

import id.co.sigma.common.client.control.ITransformableToReadonlyLabel;
import id.co.sigma.jquery.client.container.JQDialog;
import id.co.sigma.jquery.client.util.JQueryUtils;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;

/**
 * texbox untuk password
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ExtendedPasswordTextBox extends PasswordTextBox implements ITransformableToReadonlyLabel{
	
	
	private Element readonlyLabel ; 
	
	
	
	/**
	 * tombol validasi  password
	 */
	private Element validatePasswordButton ;
	
	 
	
	/**
	 * ID element(dom)
	 */
	public void setDomId(String domId) {
		getElement().setAttribute("id", domId);
		
	}
	
	
	
	/**
	 * ID element(dom)
	 */
	public String getDomId() {
		return getElement().getAttribute("id");
	}
	
	
	@Override
	protected void onAttach() {
		super.onAttach();
		readonlyLabel = DOM.createElement("span");
		getElement().getParentElement().insertAfter(readonlyLabel, getElement()); 
		readonlyLabel.getStyle().setProperty("display", "none");
		renderReadonlyMarkerContent();
		validatePasswordButton = DOM.createElement("button"); 
		getElement().getParentElement().insertAfter(validatePasswordButton, readonlyLabel);
		validatePasswordButton.setId(DOM.createUniqueId());
		Command cmd = new Command() {
			@Override
			public void execute() {
				final FlexTable tbl = new FlexTable(); 
				final PasswordTextBox txt = new PasswordTextBox(); 
				tbl.setText(0, 0, "Password");
				tbl.setText(0, 1, ":");
				tbl.setWidget(0, 2, txt);
				
				
				final JQDialog dlg = new JQDialog(dialogTitle , tbl);
				dlg.appendButton(validateButtonTitle, new Command() {
					@Override
					public void execute() {
						String chlg =  getValue() ;
						if ( chlg== null)
							chlg="" ; 
						if ( chlg.equals(txt.getValue())){
							dlg.close();
							JQueryUtils.getInstance().growlUI(validationResult, validationDoneMesssage);
							return ; 
						}
						Window.alert(validationFailedMessage);
							
					}
				}); 
				dlg.appendButton(closeButtonTitle, new Command() {
					
					@Override
					public void execute() {
						dlg.close();
					}
					
				}); 
				dlg.show();
				
				
			}
		};
		renderButton(validatePasswordButton.getId(), cmd);
		JQueryUtils.getInstance().hideElement(validatePasswordButton.getId());
	}
	
	@Override
	protected void onDetach() {
		super.onDetach();
		readonlyLabel.removeFromParent();
		readonlyLabel = null  ; 
		validatePasswordButton.removeFromParent();
		validatePasswordButton = null ;
	}
	
	
	
	
	
	
	
	/**
	 * render button untuk verify password
	 */
	private native void renderButton (String elementId , Command clickHandler ) /*-{
		$wnd.$("#" +elementId ).button ({
				icons:{
					primary: "ui-icon-circle-zoomin"
				} ,
            	text: false
			}).click(function (){
				clickHandler.@com.google.gwt.user.client.Command::execute()();
			})
	
	}-*/;
	
	
	
	/**
	 * place holder dari data
	 */
	public void setPlaceHolder(String placeHolder) {
		getElement().setPropertyString("placeHolder", placeHolder);
	}
	
	/**
	 * place holder dari data
	 */
	public String getPlaceHolder() {
		return getElement().getPropertyString("placeHolder");
	}

	@Override
	public void restoreControl() {
		getElement().getStyle().setProperty("display", "");
		if ( readonlyLabel!= null)
			readonlyLabel.getStyle().setDisplay(Display.NONE);
	}

	@Override
	public void switchToReadonlyText() {
		getElement().getStyle().setProperty("display", "none");
		if ( readonlyLabel!= null){
			readonlyLabel.getStyle().setProperty("display", "");
			renderReadonlyMarkerContent();
		}
	}
	
	
	
	/**
	 * render asteric pada label readonly
	 */
	private void renderReadonlyMarkerContent () {
		if ( readonlyLabel == null)
			return ; 
		String pwd =  getValue() ;
		readonlyLabel.setInnerHTML("");
		if ( pwd!= null && !pwd.isEmpty()){
			int pjg = pwd.length();
			String printed = "" ; 
			for ( int i= 0 ; i< pjg ; i++){
				printed+="*"; 
			}
			readonlyLabel.setInnerHTML(printed);
		}
	}
	
	@Override
	public void setValue(String value) {
		if ( value == null)
			value ="" ; 
		super.setValue(value);
		renderReadonlyMarkerContent ();
	}
	
	
	
	
	String dialogTitle  ; 
	String validateButtonTitle ; 
	String closeButtonTitle ; 
	String validationDoneMesssage ="validasi sesuai, password sesuai";
	
	String validationFailedMessage="validasi gagal, password yang di masukan tidak sama"; 
	
	
	String validationResult = "validasi password";
	
	
	/**
	 * tampilkan flag validasi atau tidak
	 * @param  dialogTitle title untuk dialog validasi
	 * @param validateButtonTitle label untuk tombol validasi
	 * @param closeButtonTitle label untuk tombol tutup
	 * @param validationFailedMessage message untuk validasi gagal. ini kalau password tidak sesuai
	 * @param validationDoneMesssage pesan kalau validasi selesai
	 */
	public void showValidatePassword(  String dialogTitle  , String validateButtonTitle , String closeButtonTitle  , String validationFailedMessage , String validationDoneMesssage) {
		
		JQueryUtils.getInstance().showElement(validatePasswordButton.getId());
		this.dialogTitle = dialogTitle ; 
		this.validateButtonTitle = validateButtonTitle ; 
		this.closeButtonTitle  = closeButtonTitle; 
		this.validationDoneMesssage = validationDoneMesssage ; 
		this.validationFailedMessage = validationFailedMessage;  
		
	}
	
	
	/**
	 * hide button
	 */
	public void hideValidatePassword() {
		JQueryUtils.getInstance().hideElement( validatePasswordButton.getId());
	}
	

}
