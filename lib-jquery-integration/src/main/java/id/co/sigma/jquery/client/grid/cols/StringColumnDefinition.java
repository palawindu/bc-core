package id.co.sigma.jquery.client.grid.cols;

import com.google.gwt.core.client.JavaScriptObject;

import id.co.sigma.common.util.NativeJsUtilities;
import id.co.sigma.jquery.client.grid.GridHorizontalAlign;



/**
 * grid column dengan tipe data String
 **/
public abstract class StringColumnDefinition<DATA> extends BaseColumnDefinition<DATA, String>{

	public StringColumnDefinition(String headerLabel, int columnWidth) {
		super(headerLabel, columnWidth);
	}
	
	
	
	
	
	/**
	 * 
	 * @param headerLabel header label. ini yang akan muncul pada header grid
	 * @param columnWidth lebar column(dalam px)
	 * @param i18Key internalization key untuk header label. pergunakan ini kalau anda berencana untuk membuat grid i18n friendly
	 * 
	 */
	public StringColumnDefinition(String headerLabel, int columnWidth, String i18Key) {
		super(headerLabel, columnWidth , i18Key);
	}
	
	
	
	/**
	 * versi ini untuk membuat sortable column
	 * 
	 */
	public StringColumnDefinition(String headerLabel, int columnWidth, String i18Key, String sortFieldIndexName ) {
		super(headerLabel , columnWidth , i18Key ); 
		this.setSortFieldName(sortFieldIndexName);
	}
	
	


	@Override
	public String getFormatterType() {
		return null;
	}
	
	@Override
	public String getSorttype() {
		return "text";
	}
	@Override
	public GridHorizontalAlign getHorizontalAlign() {
		return GridHorizontalAlign.left;
	}
	

	@Override
	public void extractAndPutDataToObject(DATA data,
			JavaScriptObject targetObject) {
		String swap = getData(data);
		if ( swap==null){
			NativeJsUtilities.getInstance().putObject(targetObject, rawJsDataName , "");
			return ;
		}
		NativeJsUtilities.getInstance().putObject(targetObject, rawJsDataName , swap);
		
	}
}
