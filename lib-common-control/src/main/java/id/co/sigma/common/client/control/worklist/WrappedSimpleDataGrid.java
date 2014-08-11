package id.co.sigma.common.client.control.worklist;


import id.co.sigma.common.client.control.worklist.cols.BooleanSimpleDataGridColDefinition;
import id.co.sigma.common.client.control.worklist.cols.CurrencySimpleDataGridColDefinition;
import id.co.sigma.common.client.control.worklist.cols.DateSimpleDataColDefinition;
import id.co.sigma.common.client.control.worklist.cols.ISimpleFieldColumnDefinition;
import id.co.sigma.common.client.control.worklist.cols.StringSimpleDataGridColDefinition;
import id.co.sigma.common.data.ActualSimpleGridDataWrapper;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;


/**
 * grid dengan data {@link ActualSimpleGridDataWrapper}. Grid ini desain nya data di kirim dalam wrapped data. ini untuk menghindari LIE , pengiriman full data graph ke client, yang bisa cost di network
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public abstract class WrappedSimpleDataGrid<KEY> extends  PagedSimpleGridPanel<ActualSimpleGridDataWrapper<KEY>>{
	
	/**
	 * column definition.ide nya sama dengan method {@link #getColumnDefinitions()}, cuma versi ini di paksa yang : 
	 * <ul>
	 * 	<li>i18 friendly</li>
	 * <li>simple field support</li>
	 * </ul> 
	 * Mohon pergunakan class-class berikut ini : 
	 *   <ol>
	 * 	<li>{@link BooleanSimpleDataGridColDefinition}</li>
	 *  <li>{@link CurrencySimpleDataGridColDefinition}</li>
	 *  <li>{@link DateSimpleDataColDefinition}</li>
	 *  <li>{@link StringSimpleDataGridColDefinition}</li>
	 *  <li>implementasi lain nya:D</li>
	 *  </ol>
	 **/
	protected abstract ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, ?>[] generateSimpleColumnDefinitions();
	
	
	private BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, ?>[] tempColumnDefinitions =null; 
	
	/**
	 * method ini tidak untuk di pergunakan
	 * 
	 * 
	 **/
	@SuppressWarnings("unchecked")
	@Override
	protected final BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, ?>[] getColumnDefinitions() {
		if ( tempColumnDefinitions!=null){
			return tempColumnDefinitions ; 
		}
		ISimpleFieldColumnDefinition<ActualSimpleGridDataWrapper<KEY>, ?>[] cols =  generateSimpleColumnDefinitions();
		if ( cols!=null && cols.length>0){
			tempColumnDefinitions=(BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, ?>[]) new BaseColumnDefinition<?, ?>[cols.length];
			for ( int i = 0 ;i < cols.length;i++){
				tempColumnDefinitions[i]= cols[i].getColumnDefinitionReference();
			}
		}
		else{
			tempColumnDefinitions = (BaseColumnDefinition<ActualSimpleGridDataWrapper<KEY>, ?>[]) new BaseColumnDefinition<?, ?>[0];
		}
		
		return tempColumnDefinitions;
	}

}
