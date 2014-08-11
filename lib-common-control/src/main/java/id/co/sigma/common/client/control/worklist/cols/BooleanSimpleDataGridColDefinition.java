package id.co.sigma.common.client.control.worklist.cols;

import com.google.gwt.core.client.GWT;

import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.BooleanColumnDefinition;

/**
 * @deprecated tidak di sarankan mempergunakan ini
 **/
@Deprecated
public class BooleanSimpleDataGridColDefinition<KEY> extends BooleanColumnDefinition<ActualSimpleGridDataWrapper<KEY>>  implements ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Boolean> {

	

	private String field ; 
	

	
	/**
	 * 
	 * 
	 * @param defaultHeaderLabel label header(default)
	 * @param dbField field yang akan di akses dari POJO JPA
	 * @param columnWidth lebar 
	 **/
	public BooleanSimpleDataGridColDefinition(String defaultHeaderLabel,String dbField , 
			int columnWidth ) {
		super(defaultHeaderLabel, columnWidth);
		this.field = dbField;
				
	}
	
	
	/**
	 * 
	 * @param defaultHeaderLabel label default untuk grid. di pergunakan kalau resource bundle tidak ketemu
	 * @param dbField db field untuk column
	 * @param i18Key i18 key untuk grid column definition
	 * @param columnWidth lebar dari grid column
	 **/
	public BooleanSimpleDataGridColDefinition(String defaultHeaderLabel,String dbField ,String i18Key, 
			int columnWidth ){
		this(defaultHeaderLabel, dbField, columnWidth);
		setI18Key(i18Key);
	}
	
	@Override
	public String getPOJOField() {
		return field;
	}

	@Override
	public BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Boolean> getColumnDefinitionReference() {
		return this;
	}

	@Override
	public Boolean getData(ActualSimpleGridDataWrapper<KEY> data) {
		try {
			return data.getIndexedDataAccessor().get( data.getValues() ,  field);
		} catch (Exception e) {
			GWT.log("gagal membaca field : " + field + ",error :" + e.getMessage() , e);
			return null ; 
		}
	}

	

	@Override
	public void setConfiguredText(String text) {
		if ( this.replaceLabelWorker!=null)
			this.replaceLabelWorker.setLabel(text);
	}

}
