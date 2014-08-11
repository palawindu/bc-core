package id.co.sigma.common.client.control.worklist.cols;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.common.util.NativeJsUtilities;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.StringColumnDefinition;


/**
 * grid column definition dengan simple data grid wrapper. 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class StringSimpleDataGridColDefinition<KEY> extends StringColumnDefinition<ActualSimpleGridDataWrapper<KEY>> implements ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, String>{
	
	private String field ; 

	
	/**
	 * @param defaultHeaderLabel label default untuk header
	 * @param dbField field dalam database(tepat nya : POJO)
	 * @param i18Key key untuk internalization
	 * @param columnWidth lebar column
	 **/
	public StringSimpleDataGridColDefinition(String defaultHeaderLabel,String dbField ,String i18Key,
			int columnWidth) {
		super(defaultHeaderLabel, columnWidth);
		this.field=dbField;
		setI18Key(  i18Key);
	}

	

	@Override
	public String getData(ActualSimpleGridDataWrapper<KEY> data) {
		if ( data==null){
			GWT.log("data null untuk current row"); 
			return "" ; 
		}
		if ( data.getIndexedDataAccessor()==null){
			GWT.log("data indexer null , tidak ada data yang akan bisa di baca"); 
			return "" ; 
		}
		try {
			return data.getIndexedDataAccessor().get( data.getValues() ,  field);
		} catch (Exception e) {
			GWT.log("gagal membaca field : " + field + ",error message->" + e.getMessage() , e);
			return "";
		}
		
	}

	@Override
	public String getPOJOField() {
		return field;
	}

	@Override
	public BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, String> getColumnDefinitionReference() {
		return this;
	}



	

	@Override
	public void setConfiguredText(String text) {
		if ( this.replaceLabelWorker!=null)
			this.replaceLabelWorker.setLabel(text);
	}
	
	@Override
	protected void generateRawGridColumnDefinitionWorker(
			JavaScriptObject rawColumnDefinition) {
		
		super.generateRawGridColumnDefinitionWorker(rawColumnDefinition);
		NativeJsUtilities.getInstance().putObject(rawColumnDefinition, "index", this.field);
	}

}
