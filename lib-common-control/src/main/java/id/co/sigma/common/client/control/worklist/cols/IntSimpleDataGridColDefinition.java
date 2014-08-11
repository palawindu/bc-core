package id.co.sigma.common.client.control.worklist.cols;


import com.google.gwt.core.client.GWT;

import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.IntegerColumnDefinition;

/**
 * @deprecated tidak di sarankan mempergunakan ini
 **/
@Deprecated
public class IntSimpleDataGridColDefinition<KEY> extends IntegerColumnDefinition<ActualSimpleGridDataWrapper<KEY>>  
	implements ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Integer>{

	private String field ; 
	
	
	
	
	/**
	 * @param defaultHeaderLabel label default untuk header
	 * @param dbField field dalam database(tepat nya : POJO)
	 * @param i18Key key untuk internalization
	 * @param columnWidth lebar column
	 **/
	public IntSimpleDataGridColDefinition(String defaultHeaderLabel,String dbField ,String i18Key,
			int columnWidth) {
		super(defaultHeaderLabel, columnWidth);
		this.field=dbField;
		setI18Key( i18Key);
	}
	
	
	@Override
	public Integer getData(ActualSimpleGridDataWrapper<KEY> data) {
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
	public BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Integer> getColumnDefinitionReference() {
		return this;
	}



	
	
	@Override
	public void setConfiguredText(String text) {
		if ( this.replaceLabelWorker!=null)
			this.replaceLabelWorker.setLabel(text);
	}
}
