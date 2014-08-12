package id.co.sigma.common.client.control.worklist;


import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class GridSavedStateData implements IJSONFriendlyObject<GridSavedStateData>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7746232851482365113L;


	/**
	 * sorting data terakhir. di sort dengan column apa saja data
	 */
	private SimpleSortArgument [] latestSort ; 
	
	
	/**
	 * lebar columns
	 */
	private Integer[] columnWidths;
	
	
	/**
	 * lebar dari grid. sebab setelah di resize, bisa jadi beda lebar dari grid
	 */
	private Integer gridWidth ; 
	
	@Override
	public GridSavedStateData instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		GridSavedStateData retval = new GridSavedStateData();
		retval.columnWidths = jsonContainer.getAsArrayOfIntegers("columnWidths"); 
		
		retval.latestSort = (SimpleSortArgument [])jsonContainer.getAsArray("latestSort", SimpleSortArgument.class);
		retval.gridWidth = jsonContainer.getAsInteger("gridWidth"); 
		return retval ;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		if ( latestSort!= null && latestSort.length!=0){
			jsonContainerData.appendToArray("latestSort", latestSort);
		}
		if ( columnWidths!= null && columnWidths.length!= 0 ){
			jsonContainerData.appendToArray("columnWidths", columnWidths);
		}
		if (gridWidth!= null){
			jsonContainerData.put("gridWidth", gridWidth);
		}
	}

	/**
	 * sorting data terakhir. di sort dengan column apa saja data
	 */
	public void setLatestSort(SimpleSortArgument[] latestSort) {
		this.latestSort = latestSort;
	}
	/**
	 * sorting data terakhir. di sort dengan column apa saja data
	 */
	public SimpleSortArgument[] getLatestSort() {
		return latestSort;
	}
	/**
	 * lebar columns
	 */
	public Integer[] getColumnWidths() {
		return columnWidths;
	}
	/**
	 * lebar columns
	 */
	public void setColumnWidths(Integer[] columnWidths) {
		this.columnWidths = columnWidths;
	}
	/**
	 * lebar dari grid. sebab setelah di resize, bisa jadi beda lebar dari grid
	 */
	public void setGridWidth(Integer gridWidth) {
		this.gridWidth = gridWidth;
	}
	/**
	 * lebar dari grid. sebab setelah di resize, bisa jadi beda lebar dari grid
	 */
	public Integer getGridWidth() {
		return gridWidth;
	}

}
