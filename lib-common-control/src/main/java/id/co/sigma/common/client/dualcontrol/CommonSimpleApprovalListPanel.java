/**
 * 
 */
package id.co.sigma.common.client.dualcontrol;

import id.co.sigma.common.client.control.MainPanelStackControl;
import id.co.sigma.common.client.control.ViewScreenMode;
import id.co.sigma.common.client.form.ExtendedButton;
import id.co.sigma.common.client.form.advance.LOVCapabledComboBoxWithLabel;
import id.co.sigma.common.client.form.advance.TextBoxWithLabel;
import id.co.sigma.common.client.widget.BaseCommonControlComposite;
import id.co.sigma.common.control.DataProcessWorker;
import id.co.sigma.common.data.CoreLibLookup;
import id.co.sigma.common.data.approval.CommonApprovalHeader;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Sep 25, 2013 4:51:34 PM
 */
public class CommonSimpleApprovalListPanel extends BaseCommonControlComposite {

	private static CommonSimpleApprovalListPanelUiBinder uiBinder = GWT
			.create(CommonSimpleApprovalListPanelUiBinder.class);
	
	
	private static final SimpleSortArgument[] APPROVAL_SORT_ARGS ={
		
		new SimpleSortArgument("requestTime" , false)
		
	} ; 
	@UiField LOVCapabledComboBoxWithLabel cbObjectType;
	@UiField TextBoxWithLabel txtReqUserName;
	@UiField ExtendedButton btnSearch;
	@UiField ExtendedButton btnReset;
	@UiField Label lblPanelTitle;
	@UiField SimpleApprovalGrid approvalGrid;
	
	
	/**
	 * FQCN object yang di include
	 */
	private String []includedObjectFQCNs ;
	
	
	/**
	 * fqcn dari object yang tidak di sertakn
	 */
	private String []excludedObjectFQCNs ;

	interface CommonSimpleApprovalListPanelUiBinder extends
			UiBinder<Widget, CommonSimpleApprovalListPanel> {
	}

	public CommonSimpleApprovalListPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
		cbObjectType.setCustomLookupParameter(CoreLibLookup.approvalDefinitionTypes);
		
		this.registerLOVCapableControl(cbObjectType);
		this.fillLookupValue();
		
		approvalGrid.setApproveProcessWorker(new DataProcessWorker<CommonApprovalHeader>() {
			
			@Override
			public void runProccess(final CommonApprovalHeader data) {
				
				//System.out.println("class handler di minta  : " + data.getTargteObjectFQCN() ) ; 
				final ISimpleApprovalPanel  handler = SimpleApprovalUtil.getInstance().getHandler(data.getTargteObjectFQCN());
				
				new Timer() {
					
					@Override
					public void run() {
						handler.setAfterApprovalCommand(new Command() {
							
							@Override
							public void execute() {
								getData();
							}
						});
						
						Widget panel = (Widget)handler ; 
						
						MainPanelStackControl.getInstance().putPanel(panel, ViewScreenMode.normal);
						new Timer() {
							
							@Override
							public void run() {
								handler.renderDataForApproval(data);
								
							}
						}.schedule(100);;
						
						
					}
				}.schedule(100);;
				
			}
		});
	}
	
	/**
	 * generate query filters
	 * @return query filters
	 */
	public SimpleQueryFilter[] generateFilters() {
		List<SimpleQueryFilter> filterList = new ArrayList<SimpleQueryFilter>();
		if (!(cbObjectType.getValue() == null || cbObjectType.getValue().trim().length() == 0)) {
			SimpleQueryFilter objectTypeFilter = new SimpleQueryFilter();
			objectTypeFilter.setField("targteObjectFQCN");
			objectTypeFilter.setOperator(SimpleQueryFilterOperator.equal);
			objectTypeFilter.setFilter(cbObjectType.getValue());
			filterList.add(objectTypeFilter);
		}
		
		if (!(txtReqUserName.getValue() == null || txtReqUserName.getValue().trim().length() == 0)) {
			SimpleQueryFilter reqUNameFilter = new SimpleQueryFilter();
			reqUNameFilter.setField("requestorUserName");
			reqUNameFilter.setOperator(SimpleQueryFilterOperator.likeBothSide);
			reqUNameFilter.setFilter(txtReqUserName.getValue());
			filterList.add(reqUNameFilter);
		}
		
		if ( includedObjectFQCNs!= null && includedObjectFQCNs.length> 0 ){
			SimpleQueryFilter f = new SimpleQueryFilter(); 
			f.setField("targteObjectFQCN");
			f.setFilter(includedObjectFQCNs);
			f.setOperator(SimpleQueryFilterOperator.fieldIn);
			filterList.add(f); 
		}
		if ( excludedObjectFQCNs!= null && excludedObjectFQCNs.length> 0 ){
			SimpleQueryFilter f = new SimpleQueryFilter(); 
			f.setField("targteObjectFQCN");
			f.setFilter(excludedObjectFQCNs);
			f.setOperator(SimpleQueryFilterOperator.fieldNotIn);
			filterList.add(f);
		}
		
		if (filterList.isEmpty())
			return null;
		
		SimpleQueryFilter[] filters = new SimpleQueryFilter[filterList.size()];
		filterList.toArray(filters);
		return filters;
	}
	
	@UiHandler("btnSearch")
	void onBtnSearchClickHandler(ClickEvent event) {
		getData();
	}
	
	
	@Override
	protected void onAttach() {
		super.onAttach();
		getData();
	}
	/**
	 * get list data yang waiting approval
	 */
	private void getData() {
		SimpleQueryFilter[] filters = generateFilters();
		approvalGrid.applyFilter(filters, APPROVAL_SORT_ARGS);
	}

	@UiHandler("btnReset")
	void onBtnResetClickHandler(ClickEvent event) {
		cbObjectType.setSelectedIndex(0);
		txtReqUserName.setValue(null);
		approvalGrid.clearData();
	}
	/**
	 * FQCN object yang di include
	 */
	public void setExcludedObjectFQCNs(String[] excludedObjectFQCNs) {
		this.excludedObjectFQCNs = excludedObjectFQCNs;
	}
	/**
	 * FQCN object yang di include
	 */
	public void setIncludedObjectFQCNs(String[] includedObjectFQCNs) {
		this.includedObjectFQCNs = includedObjectFQCNs;
	}
}
