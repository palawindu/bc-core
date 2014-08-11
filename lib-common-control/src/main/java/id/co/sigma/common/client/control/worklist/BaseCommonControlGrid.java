package id.co.sigma.common.client.control.worklist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.storage.client.Storage;

import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;
import id.co.sigma.jquery.client.grid.SimpleGridPanel;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;

/**
 * base class JQgrid untuk common control
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseCommonControlGrid<DATA> extends SimpleGridPanel<DATA>{
	
	/**
	 * ini default dari project name. ini prefix untuk penyimpanan grid. 
	 * pastikan anda mengganti ini di awal code anda
	 */
	public static String GRID_PROJECT_NAME="balicamp-core-grid"; 
	
	public BaseCommonControlGrid(){
		super() ; 
	}
	
	
	public BaseCommonControlGrid(boolean multipleSelect){
		super(multipleSelect); 
	}
	
	public BaseCommonControlGrid( boolean multipleSelection, boolean renderOnAttach){
		super(multipleSelection , renderOnAttach); 
	}


/**
	 * nama project. ini prefix untuk menyimpan konfigurasi grid
	 */
	protected final String getProjectName () {
		return GRID_PROJECT_NAME; 
	}
	
	
	
	/**
	 * grid bisa di desain bisa menyimpan state. lebar dari column, latest sort dsb.
	 * agar hal ini bisa di lakukan, anda harus menyediakan key dalam proses persistence ini . rekomendasi : 
	 */
	protected String getGridStatePersistenceHolderKey () {
		return null ; 
	}
	
	
	/**
	 * menyimpan konfigurasi grid ke dalam local storage. ini antara lain meyimpan lebar dari column dan sort column
	 */
	protected final void saveCurrentGridConfiguration () {
		String key = getGridStatePersistenceHolderKey(); 
		if ( key== null || key.isEmpty())
			return   ;
		String actualKey = getProjectName() +"."  + key ;
		try {
			GridSavedStateData st =  generateCurrentGridState();
			ParsedJSONContainer c =  SharedServerClientLogicManager.getInstance().getJSONParser().createBlankObject() ; 
			st.translateToJSON(c );  
			Storage.getLocalStorageIfSupported().setItem(actualKey, c.getJSONString());
		} catch (Exception e) {
			GWT.log("gagal menympan grid dengan key : " + actualKey + ", error : " + e.getMessage() , e);
		}
		
	}

	
	
	/**
	 * ini untuk membaca lebar per column. di baca sebagai array of int
	 */
	public int[] getColumnWidthAsArray() {
		JsArrayInteger swap = getColumnWidthAsArrayNativeWorker(getJQWidgetId());
		if ( swap == null || swap.length()==0)
			return null ; 
		int pjg  =  swap.length();
		int[] retval = new int[pjg]; 
		for ( int i = 0 ; i < pjg ; i++){
			retval[i] = swap.get(i); 
		}
		return retval ; 
	}
	
	/**
	 * method ini akan membaca lebar dari column saat ini . ini bisa di pergunakan untuk menyimpan state dari data
	 */
	private native JsArrayInteger getColumnWidthAsArrayNativeWorker (String gridTableId)/*-{
		var a = $wnd.$("#" + gridTableId).jqGrid("getGridParam", 'colModel');
		var retval =[] ;
		for ( i = 0 ; i< a.length;i++){
			retval.push(a[i].width)
		}
		return retval ; 
	
	}-*/; 
	
	
	
	/**
	 * generate save state dari current grid. di trigger oleh proses change column
	 */
	protected GridSavedStateData generateCurrentGridState () {
		GridSavedStateData retval = new GridSavedStateData();
		int [] p = getColumnWidthAsArray();
		Integer arr[] = new Integer[p.length]; 
		for ( int i = 0 ; i< p.length ; i++){
			arr[i] = p[i];
		}
		retval.setColumnWidths(arr);
		retval.setGridWidth(this.widthSeted);
		return retval 
				 ;
	}
	
	
	
	
	/**
	 * load data dari local storage
	 */
	protected GridSavedStateData loadFromLocalStorage () {
		String key = getGridStatePersistenceHolderKey(); 
		if ( key== null || key.isEmpty())
			return null ; 
		try {
			
			String actualKey = getProjectName() +"."  + key ; 
			
			String valAsJson =  Storage.getLocalStorageIfSupported().getItem(actualKey);
			if ( valAsJson== null || valAsJson.isEmpty())
				return null ; 
			ParsedJSONContainer j =  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON(valAsJson);
			GridSavedStateData smpl = new GridSavedStateData(); 
			return smpl.instantiateFromJSON(j); 
		} catch (Exception e) {
			GWT.log("gagal load data grid dengan key :" + key +", error : " + e.getMessage() , e);
			return null ; 
		}
	}
	
	
	@Override
	protected void onColumnResized(int width,
			BaseColumnDefinition<DATA, ?> changedColumnDef) {
		super.onColumnResized(width, changedColumnDef);
		saveCurrentGridConfiguration();
	}
	
	
	@Override
	protected final void runAdditionalTaskBeforeGridGridRender(
			BaseColumnDefinition<DATA, ?>[] actualColumnDefs) {
		super.runAdditionalTaskBeforeGridGridRender(actualColumnDefs);
		try {
			GridSavedStateData savedState =  loadFromLocalStorage();
			loadAndSetGridConfigurationFromLocalStorage  (  savedState  ,   actualColumnDefs);
		} catch (Exception e) {
			GWT.log("gagal membaca data state grid dan set kembali ke grid. error : " + e.getMessage()  , e);
		}
		
	}
	
	
	
	/**
	 * pekerjaan di level common control. apa saja yang perlu di load di sini
	 */
	protected void loadAndSetGridConfigurationFromLocalStorage (GridSavedStateData savedState ,  
			BaseColumnDefinition<DATA, ?>[] actualColumnDefs) {
		if ( savedState!= null ) {
			if ( savedState.getColumnWidths()!= null && savedState.getColumnWidths().length== actualColumnDefs.length){
				Integer lebars[] = savedState.getColumnWidths()  ; 
				for ( int i = 0 ; i < actualColumnDefs.length ; i++){
					actualColumnDefs[i].setWidth(lebars[i] );
				}
			}
		}
		
	}
}
