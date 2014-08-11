package id.co.sigma.common.client.control.worklist.cols;

import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;



/**
 * interface untuk simple field column def. ini untuk proses akses ke actual column definition ( akses ke class : {@link BaseColumnDefinition} )
 * pls use following class :
 *  
 * <ol>
 * 	<li>{@link BooleanSimpleDataGridColDefinition}</li>
 *  <li>{@link CurrencySimpleDataGridColDefinition}</li>
 *  <li>{@link DateSimpleDataColDefinition}</li>
 *  <li>{@link StringSimpleDataGridColDefinition}</li>
 *  <li>{@link FloatSimpleDataGridColDefinition}</li>
 *  <li>{@link BigIntegerSimpleDataGridColDefinition}</li>
 *  <li>implementasi lain nya:D</li>
 *  </ol>
 **/
public interface ISimpleFieldColumnDefinition<DATA , COLTYPE> {
	
	
	/**
	 * field POJO yang di representasikan oleh 
	 **/
	public String getPOJOField ();
	
	
	/**
	 * reference column definition
	 **/
	public BaseColumnDefinition<DATA, COLTYPE> getColumnDefinitionReference();
	/**
	 * nama field untuk sorting. kalau ini tidak null dan tidak blank, field akan menjadi sortable
	 **/
	public void setSortFieldName(String sortFieldName);

}
