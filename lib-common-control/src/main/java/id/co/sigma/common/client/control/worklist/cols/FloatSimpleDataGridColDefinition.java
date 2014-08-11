package id.co.sigma.common.client.control.worklist.cols;

import java.math.BigInteger;

import com.google.gwt.core.client.GWT;

import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.NumberColumnDefinition;



/**
 * definisi grid dnegan argument float
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @deprecated tidak di sarankan mempergunakan ini
 **/
@Deprecated
public class FloatSimpleDataGridColDefinition<KEY> extends NumberColumnDefinition<ActualSimpleGridDataWrapper<KEY>> implements 
	ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Float>{

	public FloatSimpleDataGridColDefinition(String headerLabel, int columnWidth) {
		super(headerLabel, columnWidth);
	}

	private String field ; 

	@Override
	public String getPOJOField() {
		return field;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Float> getColumnDefinitionReference() {
		BaseColumnDefinition a = (BaseColumnDefinition)this; 
		return (BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, Float>)a;
	}

	@Override
	public BigInteger getData(ActualSimpleGridDataWrapper<KEY> data) {
		try {
			return data.getIndexedDataAccessor().get( data.getValues() ,  field);
		} catch (Exception e) {
			GWT.log("gagal membaca field : " + field + ",error :" + e.getMessage() , e);
			return null ; 
		}
	}

}
