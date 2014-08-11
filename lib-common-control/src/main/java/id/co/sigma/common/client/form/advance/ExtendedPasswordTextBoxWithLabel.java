package id.co.sigma.common.client.form.advance;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

import id.co.sigma.common.client.CommonClientControlConstant;
import id.co.sigma.common.client.control.IHasLabelFormElement;
import id.co.sigma.common.client.form.ExtendedPasswordTextBox;

/**
 * textbox password dengan label
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ExtendedPasswordTextBoxWithLabel extends ExtendedPasswordTextBox implements IHasLabelFormElement{
	
	/**
	 * default label dari text password
	 */
	private String defaultLabel = "Default Label";	
	
	
	/**
	 * flag, di tambahkan tanda <strong>:</strong> atau tidak di antara label dan control
	 */
	private boolean usePoint = true;
		
	/**
	 * label element. ini berada di sisi kanan dari form
	 */
	private Element label;
	
	
	/**
	 * element reference untuk point( tanda :  ). ini di isi kalau flag {@link #usePoint} = true  
	 */
	private Element point;
	
	
	
	@Override
	protected void onAttach() {
		super.onAttach();
		if(getElement().getId() == null || getElement().getId().length() == 0){
			getElement().setId(DOM.createUniqueId());
		}						
		List<Element> result = AdvanceControlUtil.getInstance().createdLabelAndPoint(getElement().getPropertyString(CommonClientControlConstant.I18_KEY_ON_DOM), usePoint, defaultLabel, getElement());
		if(!result.isEmpty()){
			if(usePoint){
				this.point = result.get(0);
				this.label = result.get(1);
			}else{
				this.label = result.get(0);
			}
		}		
	}
	
	protected void onDetach() {
		super.onDetach();
		if(usePoint){			
			point.removeFromParent();
		}
		label.removeFromParent();
	};
	/**
	 * default label dari text password
	 */
	public void setDefaultLabel(String defaultLabel) {
		this.defaultLabel = defaultLabel;
	}
	/**
	 * default label dari text password
	 */
	public String getDefaultLabel() {
		return defaultLabel;
	}

	/**
	 * flag, di tambahkan tanda <strong>:</strong> atau tidak di antara label dan control
	 */
	public void setUsePoint(boolean usePoint) {
		this.usePoint = usePoint;
		if ( !usePoint){
			if ( point!= null){
				point.getStyle().setProperty("display", "none");
			}
		}
		else {
			if ( point!= null){
				point.getStyle().setProperty("display", "none");
			}
		}
	}
	
	/**
	 * flag, di tambahkan tanda <strong>:</strong> atau tidak di antara label dan control
	 */
	public boolean isUsePoint() {
		return usePoint;
	}
	
	
	/**
	 * label element. ini berada di sisi kanan dari form
	 */
	public Element getLabel() {
		return label;
	}
	/**
	 * element reference untuk point( tanda :  ). ini di isi kalau flag {@link #usePoint} = true  
	 */
	public Element getPoint() {
		return point;
	}
	@Override
	public void setControlLabel(String labelValue) {
		if ( label== null ){
			defaultLabel = labelValue ; 
			return ; 
		}
		label.setInnerHTML(labelValue);
	}
	
	
	@Override
	public String getControlLabel() {
		return label.getInnerHTML();
	}
	
}
