package id.co.sigma.common.client.form;




import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasValue;

import id.co.sigma.common.client.control.IParentLOVEnableControl;
import id.co.sigma.jquery.client.util.JQueryUtils;



/**
 * combo box. dedicated di buat dengan simple set value
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa(gede.sutarsa@gmail.com)</a>
 * @version $Id
 **/
public class ExtendedComboBox extends BaseExtendedListbox implements IParentLOVEnableControl, HasValue<String>{

	
	
	/**
	 * kalau di set true maka item akan bisa di switch menjadi multiple list box . ada tombol untuk convert menjadi multiple selection
	 */
	private boolean allowSwitchToMultiListbox = false; 
	private Element expandButtonElement ; 
	private Element collapseButtonElement ; 
	
	
	/**
	 * size of list box. default 10 
	 */
	private int listboxRowSize = 10 ; 
	
	protected class BundlerEvent extends ValueChangeEvent<String>{

		protected BundlerEvent(String value) {
			super(value);
		}
		
	}
	
	public ExtendedComboBox(){
		super(false);
		if ( getElement().getId() == null || getElement().getId().isEmpty()){
			getElement().setId(DOM.createUniqueId());
			
		}
		addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				fireChangeEvent(); 
			}
		}); 
		
		
	}
	
	
	
	protected void fireChangeEvent () {
		String val = getValue() ; 
		BundlerEvent e = new BundlerEvent(val); 
		for ( ValueChangeHandler<String > scn : handlers){
			scn.onValueChange(e); 
		}
	}
	
	

	
	/**
	 * otomatis memeilih index sesuai dengan value di kirim. jadinya tidak iterate manual untuk kontrol
	 **/
	public void setValue (String value){
		GWT.log("##set value on combo box di panggil.value : " + value );
		setValue(value , false); 
		if ( readonlyMode)
			renderReadonlyData();
	}
	 
	
	/**
	 * current slected item
	 **/
	public String getValue() {
		if(getSelectedIndex()==-1)
			return null ;
		return getValue(getSelectedIndex());
	}
	
	
	
	/**
	 * memilih item yang terpilih. null kalau none selected
	 */
	public List<String> getSelectedValues() {
		
		ArrayList<String> retval = new ArrayList<String>(); 
		for ( int i =0 ; i < getItemCount() ; i++ ){
			if ( isItemSelected(i))
				retval.add(getValue(i)); 
		}
		if ( retval.isEmpty())
			return null ; 
		return retval ; 
	}
	
	/**
	 * ambil nilai dari combo box dengan asumsi adata adalah integer
	 **/
	public Integer getIntegerValue() {
		String val = getValue() ;
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return null ; 
		}
		
		
	}
	
	
	
	/**
	 * eversi ini dengan return BIg integer
	 **/
	public BigInteger getBigIntegerValue() {
		String val = getValue() ;
		try {
			return new BigInteger(val);
		} catch (Exception e) {
			return null ; 
		}
		
		
	}
	
	/**
	 * internal worker untuk set value
	 **/
	protected int setValueInternalWorker (String value){
		int total = this.getItemCount();
		
		for ( int i= 0 ; i<total ; i++){
			String val = getValue(i); 
			if ( val==null ){
				if ( value==null){
					setSelectedIndex(i);
					return i;
				}
			}
			else{
				if ( val.equals(value)){
					setSelectedIndex(i);
					return i;
				}
			}
			
		}
		
		setSelectedIndex(-1);
		return -1 ; 
		
	}

	@Override
	public boolean validateMandatory() {
		String val = getValue(); 
		return val!=null&& val.length()>0;
	}
	
	
	
	
	
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if ( !visible){
			if ( mandatoryMarker!=null){
				mandatoryMarker.getStyle().setProperty("display", "none");
			}
		}
		else{
			baseMandatoryMarkerRenderer();
		}
	}




	
	
	private ArrayList<ValueChangeHandler<String>> handlers = new ArrayList<ValueChangeHandler<String>>() ; 

	@Override
	public HandlerRegistration addValueChangeHandler(
			final ValueChangeHandler<String> handler) {
		HandlerRegistration retval = new HandlerRegistration() {
			
			@Override
			public void removeHandler() {
				handlers.remove(handler); 
				
			}
		};
		return retval ; 
		 
	}


	
	private Command expandClickHandler = new Command() {
		@Override
		public void execute() {
			/*
			Window.alert("click expand");
			getElement().setPropertyBoolean("multiple", true);
			JQueryUtils.getInstance().hideElement((com.google.gwt.user.client.Element)expandButtonElement);
			JQueryUtils.getInstance().showElement((com.google.gwt.user.client.Element) expandButtonElement);
			*/
		}
	};
	
	private Command collapseClickHandler = new Command() {

		@Override
		public void execute() {/*
			Window.alert("click collapse");
			getElement().setPropertyBoolean("multiple", false);
			JQueryUtils.getInstance().showElement((com.google.gwt.user.client.Element)expandButtonElement);
			JQueryUtils.getInstance().hideElement( (com.google.gwt.user.client.Element)expandButtonElement);
			*/
		}
	};
	
	
	
	native void appendClickHandlerOnButton ( String expandElementId , String collapseElementId  , String comboBoxElementId) /*-{
	
		var swapThis = this ; 
		$wnd.$("#" +expandElementId).click(function () {
			$wnd.$("#"+comboBoxElementId)[0].multiple= true ;
			$wnd.$("#"+comboBoxElementId)[0].size=swapThis.@id.co.sigma.common.client.form.ExtendedComboBox::listboxRowSize; 
			$wnd.$("#"+expandElementId).hide() ; 
			$wnd.$("#"+collapseElementId).show() ; 
		}); 
		$wnd.$("#" +collapseElementId).click(function () {
			$wnd.$("#"+comboBoxElementId)[0].multiple= false ;
			$wnd.$("#"+comboBoxElementId)[0].size=1; 
			$wnd.$("#"+expandElementId).show() ; 
			$wnd.$("#"+collapseElementId).hide() ; 
		});
	
	
	}-*/; 
	
	
	
	
	/**
	 * render tombol untuk expand collapse
	 */
	private void renderExpandCollapseElement () {
		
		this.expandButtonElement = DOM.createElement("button"); 
		this.collapseButtonElement = DOM.createElement("button");
		
		this.expandButtonElement.setId(DOM.createUniqueId());
		this.collapseButtonElement.setId(DOM.createUniqueId());
		
		getElement().getParentElement().insertAfter(expandButtonElement, getElement());
		getElement().getParentElement().insertAfter(collapseButtonElement, expandButtonElement);
		JQueryUtils.getInstance().renderIconButton((com.google.gwt.user.client.Element)collapseButtonElement, "ui-icon-circle-minus", collapseClickHandler);
		JQueryUtils.getInstance().renderIconButton((com.google.gwt.user.client.Element)expandButtonElement, "ui-icon-circle-plus", expandClickHandler);
		new Timer() {
			@Override
			public void run() {
				JQueryUtils.getInstance().hideElement((com.google.gwt.user.client.Element)collapseButtonElement);
			}
		}.schedule(10);
		
		appendClickHandlerOnButton(expandButtonElement.getId(), collapseButtonElement.getId(), getElement().getId());
		
	}
	
	
	/**
	 * remove tombol sebelah combo box
	 */
	private void removeExpandCollapseElement () {
		if ( expandButtonElement== null)
			return ; 
		expandButtonElement.removeFromParent(); 
		collapseButtonElement.removeFromParent();
	}



	@Override
	public void setValue(String value, boolean fireEvents) {
		this.currentSelectedValue= value; 
		this.setValueInternalWorker(currentSelectedValue); 
		 if ( readonlyMode)
			 switchToReadonlyText();
		if ( fireEvents){
			fireChangeEvent(); 
		}
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		if ( allowSwitchToMultiListbox)
			renderExpandCollapseElement();
	}
	
	
	@Override
	protected void onDetach() {
		super.onDetach();
		if ( allowSwitchToMultiListbox)
			removeExpandCollapseElement(); 
		
	}
	/**
	 * kalau di set true maka item akan bisa di switch menjadi multiple list box . ada tombol untuk convert menjadi multiple selection
	 */
	public void setAllowSwitchToMultiListbox(boolean allowSwitchToMultiListbox) {
		if ( isAttached()){
			renderExpandCollapseElement();
		}
		this.allowSwitchToMultiListbox = allowSwitchToMultiListbox;
	}
	/**
	 * kalau di set true maka item akan bisa di switch menjadi multiple list box . ada tombol untuk convert menjadi multiple selection
	 */
	public boolean isAllowSwitchToMultiListbox() {
		return allowSwitchToMultiListbox;
	}
	
	/**
	 * size of list box. default 10 
	 */
	public void setListboxRowSize(int listboxRowSize) {
		this.listboxRowSize = listboxRowSize;
		
	}

	/**
	 * size of list box. default 10 
	 */
	public int getListboxRowSize() {
		return listboxRowSize;
	}

	
}
