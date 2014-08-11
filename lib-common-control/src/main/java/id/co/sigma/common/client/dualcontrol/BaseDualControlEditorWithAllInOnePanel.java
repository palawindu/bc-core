package id.co.sigma.common.client.dualcontrol;

import id.co.sigma.common.client.common.ITitleAndSearchPanelFilter;
import id.co.sigma.common.client.control.ExpensivePanelGenerator;
import id.co.sigma.common.client.control.IPanelGenerator;
import id.co.sigma.common.control.DataProcessWorker;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.jquery.client.grid.CellButtonHandler;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.GridColumnGroup;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * dual control panel , di jadikan 1 semua 
 * 
 * <ol>
 * <li>Editor</li>
 * <li>Grid</li>
 * <li>main panel</li>
 * <li>title panel</li>
 * </ol>
 * 
 *
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
  *@param <KEY> ini primary key dari data. di harapkan ini di antara ini : <ol>
 *<li>{@link BigInteger}</li>
 *<li>{@link Long}</li>
 *<li>{@link Integer}</li>
 *<li>{@link String}</li>
 *</ol>
 *key data di harapkan tunggal
 *@param <DATA> data yang di edit oleh editor. ini desain nya bukan DTO
 */
public abstract class BaseDualControlEditorWithAllInOnePanel<KEY extends Serializable ,  DATA extends DualControlEnabledData<DATA, KEY>> 
	extends 
	BaseDualControlDataEditor<KEY, DATA> implements IDualControlMultipleDataEditor<DATA>{
	
	
	
	/**
	 * grid untuk dual control panel 
	 * sama, di jadikan inner class agar cepat bisa di extends
	 */
	protected class InsideContainerDualControlGridPanel extends BaseDualControlDataGridPanel< DATA>{

		
		public InsideContainerDualControlGridPanel( 
		
		
		DataProcessWorker<DATA> editForApprovalRequestHandler  , DataProcessWorker<DATA> viewDetailHandler){
			super ( editForApprovalRequestHandler , viewDetailHandler ); 
		}
		@Override
		public Class<DATA> getDualControlClass() {
			return BaseDualControlEditorWithAllInOnePanel.this.getProccessedClass();
		}

		@Override
		protected String getEraseIconTitleI18nKey() {
			return BaseDualControlEditorWithAllInOnePanel.this.getEraseIconTitleI18nKey();
		}

		@Override
		protected String getEditIconTitleI18nKey() {
			return BaseDualControlEditorWithAllInOnePanel.this.getEditIconTitleI18nKey();
		}

		@Override
		protected String getDefaultEditIconTitle() {
			return BaseDualControlEditorWithAllInOnePanel.this.getDefaultEditIconTitle();
		}
		@Override
		protected String getViewDetailIconTitleI18nKey() {
			return BaseDualControlEditorWithAllInOnePanel.this.getViewDetailIconTitleI18nKey();
		}
		@Override
		protected String getDefaultViewDetailIconTitle() {
			return BaseDualControlEditorWithAllInOnePanel.this.getDefaultViewDetailIconTitle();
		}
		@Override
		protected String getApproveDataIconTitleI18nKey() {
			return BaseDualControlEditorWithAllInOnePanel.this.getApproveDataIconTitleI18nKey();
		}
		@Override
		protected String getDefaultApproveDataIconTitle() {
			return BaseDualControlEditorWithAllInOnePanel.this.getDefaultApproveDataIconTitle();
		}
		@Override
		protected String generateFailGetEditableDataListMessage(
				Throwable caught) {
			return BaseDualControlEditorWithAllInOnePanel.this.generateFailGetEditableDataListMessage(caught);
		}

		@Override
		protected String generateFailSubmitRequestDeleteDataMessage(
				Throwable caught) {
			return BaseDualControlEditorWithAllInOnePanel.this.generateFailSubmitRequestDeleteDataMessage(caught);
		}

		@Override
		protected String getDefaultDeleteRequestSubmitedDoneMessage() {
			return BaseDualControlEditorWithAllInOnePanel.this.getDefaultDeleteRequestSubmitedDoneMessage();
		}

		@Override
		protected String getRowNumberColumnHeaderLabelI18nKey() {
			return BaseDualControlEditorWithAllInOnePanel.this.getRowNumberColumnHeaderLabelI18nKey();
		}

		@Override
		protected String getActionColumnHeaderLabelI18nKey() {
			return BaseDualControlEditorWithAllInOnePanel.this.getActionColumnHeaderLabelI18nKey();
		}

		@Override
		protected String generateConfirmDeleteDataMessage(
				DATA dataToErase) {
			return BaseDualControlEditorWithAllInOnePanel.this.generateConfirmDeleteDataMessage(dataToErase);
		}

		@Override
		protected BaseColumnDefinition<DATA, ?>[] getColumnDefinitions() {
			return BaseDualControlEditorWithAllInOnePanel.this.getColumnDefinitions();
		}
		
		
		@Override
		public void setData(PagedResultHolder<DATA> data) {
			super.setData(data);
		}
		
		
		
		
		@Override
		protected GridColumnGroup[] getGroupedColumnHeader() {
			return getGroupedGridColumnHeader();
		}
		
		@Override
		protected String getGridStatePersistenceHolderKey() {
			return  getGridSaveStateKey ();
		}
		
		@Override
		protected CellButtonHandler<DATA>[] generateActionButtons() {
			GWT.log("[dualcontrol] generate action buttons");
			CellButtonHandler<DATA>[] org =super.generateActionButtons();
			CellButtonHandler<DATA>[] leftBtns = generateFrontSideAdditionalActionButtons(); 
			CellButtonHandler<DATA>[] rightBtns = generateAfterSideAdditionalActionButtons();
			return arrangeActionButtons(org, leftBtns, rightBtns);
			
		}
		
		
		@Override
		protected int getDefaultActionColumnWidth() {
			// TODO Auto-generated method stub
			return BaseDualControlEditorWithAllInOnePanel.this.getDefaultActionColumnWidth();
		}
	}
	
	
	/**
	 * subclass untuk main main panel dual control 
	 * di jadikan subclass agar bisa di extends di masing-masing project
	 */
	public class InsideContainerDualControlMainPanel extends BaseDualControlMainPanel< KEY, DATA>{
		public InsideContainerDualControlMainPanel(
				DualControlEditorState dualControlEditorState) {
			super(dualControlEditorState);
		}



		@Override
		protected BaseDualControlDataGridPanel<DATA> instantiateGrid(
				DataProcessWorker<DATA> editForApprovalRequestHandler,
				DataProcessWorker<DATA> viewDetailHandler) {
			GWT.log("[dualcontrol] instantiate grid");
			return instantiateGridListOfEditableData(editForApprovalRequestHandler,  viewDetailHandler);
		}
		
		

		@Override
		protected BaseDualControlDataEditor<KEY, DATA> instantiateEditorPanel() {
			BaseDualControlEditorWithAllInOnePanel.this.setMainPanelReference(this);
			return BaseDualControlEditorWithAllInOnePanel.this;
		}

		@Override
		protected ITitleAndSearchPanelFilter instantiateHeaderAndSearchFilter() {
			return generateHeaderAndSearchFilterPanel();
		}

		@Override
		protected Widget instantiateFooterPanel() {
			return generateFooterPanel();
		}

		@Override
		protected void instantiateDataForAddNewTemplate(AsyncCallback<DATA> callback) {
			
			  BaseDualControlEditorWithAllInOnePanel.this.instantiateDataForAddNewTemplate(callback);
		}

		@Override
		protected String getAddDataIconDefaultCaption() {
			return BaseDualControlEditorWithAllInOnePanel.this.getAddDataIconDefaultCaption();
		}

		@Override
		protected String getAddDataIconCaptionI18nCode() {
			return BaseDualControlEditorWithAllInOnePanel.this.getAddDataIconCaptionI18nCode();
		}
		
		
		@Override
		protected Integer getGridHeight() {
			return getGridHeightBridge();
		}
	}
	
	
	
	
	
	
	/**
	 * generate main editor panel. Grid di buat dengan property pada 
	 **/
	public  BaseDualControlMainPanel<KEY, DATA> instantiateMainPanel (DualControlEditorState editorState) {
		GWT.log("[dualcontrol] instiate main paenl");
		final BaseDualControlMainPanel<KEY, DATA> retval =new InsideContainerDualControlMainPanel(editorState) ; /*new BaseDualControlMainPanel< KEY, DATA>(editorState) {

			@Override
			protected BaseDualControlDataGridPanel<DATA> instantiateGrid(
					DataProcessWorker<DATA> editForApprovalRequestHandler,
					DataProcessWorker<DATA> viewDetailHandler) {
				GWT.log("[dualcontrol] instantiate grid");
				return instantiateGridListOfEditableData(editForApprovalRequestHandler,  viewDetailHandler);
			}
			
			

			@Override
			protected BaseDualControlDataEditor<KEY, DATA> instantiateEditorPanel() {
				BaseDualControlEditorWithAllInOnePanel.this.setMainPanelReference(this);
				return BaseDualControlEditorWithAllInOnePanel.this;
			}

			@Override
			protected ITitleAndSearchPanelFilter instantiateHeaderAndSearchFilter() {
				return generateHeaderAndSearchFilterPanel();
			}

			@Override
			protected Widget instantiateFooterPanel() {
				return generateFooterPanel();
			}

			@Override
			protected void instantiateDataForAddNewTemplate(AsyncCallback<DATA> callback) {
				
				  BaseDualControlEditorWithAllInOnePanel.this.instantiateDataForAddNewTemplate(callback);
			}

			@Override
			protected String getAddDataIconDefaultCaption() {
				return BaseDualControlEditorWithAllInOnePanel.this.getAddDataIconDefaultCaption();
			}

			@Override
			protected String getAddDataIconCaptionI18nCode() {
				return BaseDualControlEditorWithAllInOnePanel.this.getAddDataIconCaptionI18nCode();
			}
			
			
			@Override
			protected Integer getGridHeight() {
				return getGridHeightBridge();
			}
			 
		};
		*/
		setMainPanelReference(retval);
		
		final BaseDualControlEditorWithAllInOnePanel<KEY, DATA> swapTHis = this ; 
		retval.setEditorGenerator(new IPanelGenerator<BaseDualControlDataEditor<KEY,DATA>>() {
			@Override
			public void instantiatePanel(
					ExpensivePanelGenerator<BaseDualControlDataEditor<KEY, DATA>> callback) {
				swapTHis.makeClone(callback); //callback.onPanelGenerationComplete(widget);
				
			}
		}  );
		return retval ; 
	}

	@Override
	protected void initUnderlyingWidget(Widget uiBinderGeneratedWidget) {
		
		
		
		super.initUnderlyingWidget(uiBinderGeneratedWidget);
	}
	
	
	
	/**
	 * worker untuk instantiate grid list
	 **/
	protected BaseDualControlDataGridPanel<DATA> instantiateGridListOfEditableData (DataProcessWorker<DATA> editForApprovalRequestHandler,
			
			DataProcessWorker<DATA> viewDetailHandler) {
		GWT.log("[dualcontrol] instantiate grid untuk dual control all in one");
		
		
		BaseDualControlDataGridPanel<DATA> retvalGrid = new InsideContainerDualControlGridPanel(editForApprovalRequestHandler,  viewDetailHandler);  /*new BaseDualControlDataGridPanel< DATA>(editForApprovalRequestHandler,  viewDetailHandler) {

			@Override
			public Class<DATA> getDualControlClass() {
				return BaseDualControlEditorWithAllInOnePanel.this.getProccessedClass();
			}

			@Override
			protected String getEraseIconTitleI18nKey() {
				return BaseDualControlEditorWithAllInOnePanel.this.getEraseIconTitleI18nKey();
			}

			@Override
			protected String getEditIconTitleI18nKey() {
				return BaseDualControlEditorWithAllInOnePanel.this.getEditIconTitleI18nKey();
			}

			@Override
			protected String getDefaultEditIconTitle() {
				return BaseDualControlEditorWithAllInOnePanel.this.getDefaultEditIconTitle();
			}
			@Override
			protected String getViewDetailIconTitleI18nKey() {
				return BaseDualControlEditorWithAllInOnePanel.this.getViewDetailIconTitleI18nKey();
			}
			@Override
			protected String getDefaultViewDetailIconTitle() {
				return BaseDualControlEditorWithAllInOnePanel.this.getDefaultViewDetailIconTitle();
			}
			@Override
			protected String getApproveDataIconTitleI18nKey() {
				return BaseDualControlEditorWithAllInOnePanel.this.getApproveDataIconTitleI18nKey();
			}
			@Override
			protected String getDefaultApproveDataIconTitle() {
				return BaseDualControlEditorWithAllInOnePanel.this.getDefaultApproveDataIconTitle();
			}
			@Override
			protected String generateFailGetEditableDataListMessage(
					Throwable caught) {
				return BaseDualControlEditorWithAllInOnePanel.this.generateFailGetEditableDataListMessage(caught);
			}

			@Override
			protected String generateFailSubmitRequestDeleteDataMessage(
					Throwable caught) {
				return BaseDualControlEditorWithAllInOnePanel.this.generateFailSubmitRequestDeleteDataMessage(caught);
			}

			@Override
			protected String getDefaultDeleteRequestSubmitedDoneMessage() {
				return BaseDualControlEditorWithAllInOnePanel.this.getDefaultDeleteRequestSubmitedDoneMessage();
			}

			@Override
			protected String getRowNumberColumnHeaderLabelI18nKey() {
				return BaseDualControlEditorWithAllInOnePanel.this.getRowNumberColumnHeaderLabelI18nKey();
			}

			@Override
			protected String getActionColumnHeaderLabelI18nKey() {
				return BaseDualControlEditorWithAllInOnePanel.this.getActionColumnHeaderLabelI18nKey();
			}

			@Override
			protected String generateConfirmDeleteDataMessage(
					DATA dataToErase) {
				return BaseDualControlEditorWithAllInOnePanel.this.generateConfirmDeleteDataMessage(dataToErase);
			}

			@Override
			protected BaseColumnDefinition<DATA, ?>[] getColumnDefinitions() {
				return BaseDualControlEditorWithAllInOnePanel.this.getColumnDefinitions();
			}
			
			
			@Override
			public void setData(PagedResultHolder<DATA> data) {
				super.setData(data);
			}
			
			
			
			
			@Override
			protected GridColumnGroup[] getGroupedColumnHeader() {
				return getGroupedGridColumnHeader();
			}
			
			@Override
			protected String getGridStatePersistenceHolderKey() {
				return  getGridSaveStateKey ();
			}
			
			@Override
			protected CellButtonHandler<DATA>[] generateActionButtons() {
				GWT.log("[dualcontrol] generate action buttons");
				CellButtonHandler<DATA>[] org =super.generateActionButtons();
				CellButtonHandler<DATA>[] leftBtns = generateFrontSideAdditionalActionButtons(); 
				CellButtonHandler<DATA>[] rightBtns = generateAfterSideAdditionalActionButtons();
				return arrangeActionButtons(org, leftBtns, rightBtns);
				
			}
			
		};*/
		Integer tinggi = getGridHeight() ; 
		if ( tinggi != null ){
			retvalGrid.setHeight(tinggi);
		}
		else {
			retvalGrid.setHeight(300);
		}
		Integer pgSize = getGridPagingSize() ; 
		if ( pgSize!= null){
			retvalGrid.setPageSize(pgSize);
		}
		
		
		 
		
		return retvalGrid; 
		
	}
	
	
	
	/**
	 * default action button adalah edit, erase dan open. tombol berikut adalah tombol untuk di sebelah tombol original. tombol-tombol akan di taruh di sisi kiri. ilutrasi nya di bawah ini : 
	 * <button>baru</button><span style="border:red solid 1px;padding:3px"><button>e</button><button>d</button><button>v</button></span><br/>
	 * tombol dengan tag <i>baru</i> merupakan penemapatan tombol untuk ini
	 */
	protected CellButtonHandler<DATA>[] generateFrontSideAdditionalActionButtons(){
		return null ; 
	}
	
	/**
	 * ini berseberangan dengan {@link #generateFrontSideAdditionalActionButtons()}, tombol akan di taruh setelah tombol default. visualisasi nya sbb :<br/> 
	 * <span style="border:red solid 1px;padding:3px"><button>e</button><button>d</button><button>v</button></span><button>baru</button><br/>
	 */
	protected CellButtonHandler<DATA>[] generateAfterSideAdditionalActionButtons(){
		return null ; 
	}
	
	
	
	
	
	/**
	 * worker untuk menyusun tombol. kalau anda memerlukan handler tersendiri, silakan override method ini
	 */
	protected CellButtonHandler<DATA>[] arrangeActionButtons (CellButtonHandler<DATA>[] defaultButton , CellButtonHandler<DATA>[] buttonsBefore , CellButtonHandler<DATA>[] buttonsAfter ) {
		ArrayList<CellButtonHandler<DATA>> btns = new ArrayList<CellButtonHandler<DATA>>();
		if (buttonsBefore!= null && buttonsBefore.length > 0  ) {
			for ( CellButtonHandler<DATA> scn : buttonsBefore){
				btns.add(scn); 
			}
			GWT.log("custom button di tambahkan : " + buttonsBefore.length);
		}else {
			GWT.log("tombol custom sisi kiri kosong untuk class  :" + getClass().getName());
		}
		if (defaultButton!= null && defaultButton.length > 0  ) {
			for ( CellButtonHandler<DATA> scn : defaultButton){
				btns.add(scn); 
			}
		}
		if (buttonsAfter!= null && buttonsAfter.length > 0  ) {
			for ( CellButtonHandler<DATA> scn : buttonsAfter){
				btns.add(scn); 
			}
		}
		
		CellButtonHandler<DATA>[] retval =(CellButtonHandler<DATA>[]) new CellButtonHandler<?>[btns.size()];
		btns.toArray(retval); 
		return retval ; 
		
	} 
	
	
	
	/**
	 * methd ini adalah key untuk menyimpan state dari grid di client. 
	 * kalau anda berencana agar grid bisa di simpan dalam local storage, pastikan ini tidak null/ empty
	 */
	protected String getGridSaveStateKey() {
		return null;
	}

	/**
	 * ini kalau memerlukan grouped column header
	 */
	protected GridColumnGroup[] getGroupedGridColumnHeader() {
		
		return null;
	}
	
	
	
	/**
	 * ini untuk override grid paging size. kalau null tidak akan di set
	 */
	public Integer getGridPagingSize () {
		return null;
	}
	
	
	/**
	 * tinggi darigrid. kalau null berarti tidak akand i set
	 */
	protected Integer getGridHeight () {
		return null ; 
	}
	
	
	
	/**
	 * untuk mengakali methode dengan nama sama. dalam main
	 */
	private Integer getGridHeightBridge (){
		return getGridHeight(); 
	} 
	
	
	/**
	 * membuat search + title panel. ini untuk di taruh ke main panel. kalau anda mempergunakan UIBinder, new ui binder dan return di sini
	 **/
	protected abstract ITitleAndSearchPanelFilter generateHeaderAndSearchFilterPanel() ; 
	
	
	

	
	
	/**
	 * label default untuk icon hapus<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected String getDefaultEraseIconTitle () {
		return "hapus data"; 
	}
	
	
	
	/**
	 * widget untuk footer. kalau di perlukan footer, masukan di sini.<br/>
	 * Sebaliknya kalau misal nya tidak ada footer, return null saja
	 * 
	 * <br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract Widget generateFooterPanel();
	
	
	
	/**
	 * i18n key untuk title erase<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getEraseIconTitleI18nKey () ; 
	
	
	/**
	 * i18n key untuk title delete<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getEditIconTitleI18nKey () ; 
	
	
	/**
	 * default label utnuk edit data<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getDefaultEditIconTitle () ; 
	
	
	/**
	 * i18n Key untuk view detail<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getViewDetailIconTitleI18nKey () ;
	
	/**
	 * label default utnuk view detail data<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getDefaultViewDetailIconTitle () ;
	/**
	 * key internalization untuk approve data<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getApproveDataIconTitleI18nKey () ;
	
	/**
	 * label default utnuk approve data<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract
		String getDefaultApproveDataIconTitle () ;
	
	
	/**
	 * message kalau membaca da ta yang bisa di edit gagal di lakukan. menjadi tanggung jawab masing-masing. termasuk isu i18n<br/>
	 * Ini dipergunakan dalam grid 
	 **/
	protected abstract String generateFailGetEditableDataListMessage (Throwable caught) ; 
	
	/**
	 * message kalau gagal submit request delete data<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract String generateFailSubmitRequestDeleteDataMessage (Throwable caught) ;
	
	
	/**
	 * message kalau delete di submit<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract String getDefaultDeleteRequestSubmitedDoneMessage (); 
	

	/**
	 * i18n code utnuk row number<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract  String  getRowNumberColumnHeaderLabelI18nKey () ; 
	
	/**
	 * i18n key utnuk action column label<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract String  getActionColumnHeaderLabelI18nKey () ; 
	

	/**
	 * message untuk konfirmasi kalau data mau di delete. render data anda di sini. kalau di perlukan internalization, silakan di masukan<br/>
	 * Ini dipergunakan dalam grid
	 * @param dataToErase data yang hendak di hapus. kalau ada property yang di perlukan silakan di get dari data nya
	 **/
	protected abstract String generateConfirmDeleteDataMessage (DATA dataToErase) ; 
	
	
	
	/**
	 * column defs<br/>
	 * Ini dipergunakan dalam grid
	 **/
	protected abstract BaseColumnDefinition<DATA, ?>[] getColumnDefinitions(); 
	
	
	
	/**
	 * ini membuat data untuk add baru. Proses nya spt ini : 
	 * <ol>
	 * <li>data di new</li>
	 * <li>data di kirimkan ke editor dalam proses add new</li>
	 * </ol>
	 * anda perlu mengisikan beberapa field yang mandatory, jadinya editor sudah dalam prosisi siap
	 **/
	protected abstract void instantiateDataForAddNewTemplate (AsyncCallback<DATA> callback) ; 
	
	
	/**
	 * default label untuk icon add, ini ada di sisi bawah dari grid
	 **/
	protected abstract String getAddDataIconDefaultCaption () ; 
	
	
	
	/**
	 * key internalization untuk 
	 **/
	protected abstract String getAddDataIconCaptionI18nCode () ;
	
	protected int getDefaultActionColumnWidth() {
		return 100 ; 
	}
	

}
