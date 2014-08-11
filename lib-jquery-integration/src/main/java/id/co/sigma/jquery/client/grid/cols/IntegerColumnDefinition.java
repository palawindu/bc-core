package id.co.sigma.jquery.client.grid.cols;

import com.google.gwt.core.client.JavaScriptObject;

import id.co.sigma.common.util.NativeJsUtilities;
import id.co.sigma.jquery.client.grid.GridHorizontalAlign;




/**
 * column definition dengan tipe data Integer. 
 * item ini mengenal : decimal separator. jadinya data akan di format dengan pemisah desimal apa
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * @since 18-aug-2012
 **/
public abstract class IntegerColumnDefinition<DATA> extends BaseColumnDefinition<DATA, Integer>{

	public IntegerColumnDefinition(String headerLabel, int columnWidth) {
		super(headerLabel, columnWidth);
	}
	
	public IntegerColumnDefinition(String headerLabel, int columnWidth, String i18Key) {
		super(headerLabel, columnWidth, i18Key);
	}

	@Override
	public String getFormatterType() {
		//return "integer";
		return null ; 
	}

	@Override
	public String getSorttype() {
		return "int";
	}
	@Override
	public GridHorizontalAlign getHorizontalAlign() {
		return GridHorizontalAlign.right;
	}
	
	@Override
	public void extractAndPutDataToObject(DATA data,
			JavaScriptObject targetObject) {
		Integer swap = getData(data);
		if ( swap==null)
			NativeJsUtilities.getInstance().putNullObject(targetObject, rawJsDataName);
		else
			NativeJsUtilities.getInstance().putObject(targetObject, rawJsDataName, swap.intValue());
	}
}
