package id.co.sigma.common.client.dualcontrol;





import id.co.sigma.common.client.control.worklist.PagedSimpleGridPanel;
import id.co.sigma.common.data.app.SimpleDualControlData;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;

/**
 * grid multiple control untuk grid approval. ini untuk versi data yang di upload dengan spreadsheet
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */

public class MultipleDataDualControlGrid<DATA extends SimpleDualControlData<?>> extends PagedSimpleGridPanel<DATA>{
	
	
	
	private BaseColumnDefinition<DATA, ?>[] columnDefinitions ; 
	
	
	
	/**
	 * target object FQCN.class data yang di render apa
	 **/
	String targetObjectFQCN ; 
	
	public MultipleDataDualControlGrid(){
		super(); 
		this.renderJqueryOnAttach = false;
		
	}
	
	
	
	

	/**
	 * menaruh column definition ke dalam grid
	 **/
	public void configureGrid( String targetObjectFQCN , 
			BaseColumnDefinition<DATA, ?>[] columnDefinitions) {
		this.targetObjectFQCN = targetObjectFQCN; 
		this.columnDefinitions = columnDefinitions;
		if ( isAttached()){
			renderJQWidgetOnAttachWorker(); 
			
		}else{
			renderJqueryOnAttach = true ; 
		}
		
	}
	@Override
	protected BaseColumnDefinition<DATA, ?>[] getColumnDefinitions() {
		return columnDefinitions;
	}
	
	
	
	
	
	
	

	 	


}
