package id.co.sigma.common.client.security.function;

import id.co.sigma.common.security.dto.FunctionDTO;
import id.co.sigma.common.client.control.worklist.I18ColumnDefinition;
import id.co.sigma.common.client.control.worklist.I18EnabledSimpleGrid;
import id.co.sigma.jquery.client.grid.CellButtonHandler;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.BooleanColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.StringColumnDefinition;

/**
 * Function Grid Panel
 * @author I Gede Mahendra
 * @since Jan 31, 2013, 3:40:19 PM
 * @version $Id
 */
public class FunctionGridPanel extends I18EnabledSimpleGrid<FunctionDTO>{

	private StringColumnDefinition<FunctionDTO> colFuncCode;
	private StringColumnDefinition<FunctionDTO> colFuncLabel;
	private StringColumnDefinition<FunctionDTO> colFuncLabelParent;
	private BooleanColumnDefinition<FunctionDTO> colActive;
	private CellButtonHandler<FunctionDTO> buttonAction;
	private BaseColumnDefinition<?, ?>[] gridColumnDefinition;
	
	/**
	 * Constructor Default
	 */
	public FunctionGridPanel() {
		
	}
	
	@Override
	public I18ColumnDefinition<FunctionDTO>[] getI18ColumnDefinitions() {		
		return null;
	}

	@Override
	protected BaseColumnDefinition<FunctionDTO, ?>[] getColumnDefinitions() {		
		return null;
	}
}