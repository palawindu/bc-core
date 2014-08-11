package id.co.sigma.common.client.control.worklist.cols;

import java.util.Date;

import com.google.gwt.core.client.GWT;

import id.co.sigma.common.control.ResourceBundleConfigurableControl;
import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.DateColumnDefinition;


/**
 * 
 **/
@Deprecated
public class DateSimpleDataColDefinition<KEY> extends DateColumnDefinition<ActualSimpleGridDataWrapper<KEY>> 
	implements ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Date>{
	
	



	private String field ; 
	
	
	/**
	 * @param defaultHeaderLabel label default untuk header
	 * @param dbField field dalam database(tepat nya : POJO)
	 * @param i18Key key untuk internalization
	 * @param columnWidth lebar column
	 **/
	public DateSimpleDataColDefinition(String defaultHeaderLabel,String dbField ,String i18Key,
			int columnWidth) {
		super(defaultHeaderLabel, columnWidth);
		this.field=dbField;
		setI18Key(  i18Key);
	}
	
	public DateSimpleDataColDefinition(String defaultHeaderLabel,String dbField ,String i18Key,
			int columnWidth, String dateFormat) {
		super(defaultHeaderLabel, columnWidth, dateFormat);
		this.field=dbField;
		setI18Key(  i18Key);
	}
	
	
	@Override
	public Date getData(ActualSimpleGridDataWrapper<KEY> data) {
		try {
			return data.getIndexedDataAccessor().get( data.getValues() ,  field);
		} catch (Exception e) {
			GWT.log("gagal membaca field : " + field + ",error :" + e.getMessage() , e);
			return null ; 
		}
	}

	@Override
	public String getPOJOField() {
		return field;
	}

	@Override
	public BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Date> getColumnDefinitionReference() {
		return this;
	}




	
	
	@Override
	public void setConfiguredText(String text) {
		if ( this.replaceLabelWorker!=null)
			this.replaceLabelWorker.setLabel(text);
	}

}
