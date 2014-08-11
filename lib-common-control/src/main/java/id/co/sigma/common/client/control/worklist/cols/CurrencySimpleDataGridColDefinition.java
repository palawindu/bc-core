package id.co.sigma.common.client.control.worklist.cols;

import java.math.BigDecimal;

import com.google.gwt.core.client.GWT;

import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.CurrencyColumnDefinition;


/**
 * simple data grid dengan currency
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 21-sept-2012
 * @deprecated tidak di sarankan mempergunakan ini
 **/
@Deprecated
public class CurrencySimpleDataGridColDefinition<KEY> extends 
	CurrencyColumnDefinition<ActualSimpleGridDataWrapper<KEY>> implements ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Number>{

	




	private String field ; 
	
	
	
	
	/**
	 * @param defaultHeaderLabel label default untuk header
	 * @param dbField field dalam database(tepat nya : POJO)
	 * @param i18Key key untuk internalization
	 * @param columnWidth lebar column
	 **/
	public CurrencySimpleDataGridColDefinition(String defaultHeaderLabel,String dbField ,String i18Key,
			int columnWidth) {
		super(defaultHeaderLabel, columnWidth);
		this.field=dbField;
		setI18Key(i18Key);
	}
	
	
	@Override
	public String getPOJOField() {
		return field;
	}










	@Override
	public BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Number> getColumnDefinitionReference() {
		return this;
	}




	@Override
	public BigDecimal getData(ActualSimpleGridDataWrapper<KEY> data) {
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
