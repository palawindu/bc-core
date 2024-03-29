package id.co.sigma.common.client.rpc;


import id.co.sigma.common.client.rpc.impl.DualControlDataRPCServiceAsyncImpl;
import id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder;
import id.co.sigma.common.data.DataWithToken;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.SystemParamDrivenClass;
import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.DualControlDefinition;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.data.app.DualControlEnabledOperation;
import id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable;
import id.co.sigma.common.data.app.SimpleMasterDataDualControlApprovalResult;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;


import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;



/**
 * Async interface untuk dual control related table
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface DualControlDataRPCServiceAsync {
	
	
	public static class Util {
		
		private static DualControlDataRPCServiceAsync instance ; 
		
		public static DualControlDataRPCServiceAsync getInstance() {
			if ( instance == null){
				instance = GWT.create(DualControlDataRPCServiceAsyncImpl.class);

			}
			return instance;
		}
		
		
	}

	
	/**
	 * versi ini dengan mengirimkan data token kembali ke client. jadinya round trip data bisa di kurangi
	 * @param id ID pada table m_dual_control_table. data yang memerlukan approval
	 */
	public void getWaitApprovalDataWithToken (Long id , AsyncCallback<DataWithToken<CommonDualControlContainerTable>> callback)  ; 
	
	/**
	 * membaca data sebagai JSON String. data di baca dengan class FQCN dan ID dari data. id dari data di compact menjadi 1. Kalau id dari data composite, data di concat dengan .
	 **/
	public void  getTargetDataAsJSonString ( String classFQCN , String dataIdAsCompactedString , AsyncCallback<String> jsonData); 
	
	/**
	 * @param dataId id data yang di reject
	 * @param rejectReason alasan data di tolak oleh user
	 **/
	public void rejectData (Long dataId , String rejectReason, AsyncCallback<Void> callback)   ; 
	
	
	
	/**
	 * reject semua data. ini di manfaatkan dalam timer untuk reject semua data yang masih waiting for approval. ini menjamin tidak ada data yang terlewat
	 * data di cari dengan pembatas tanggal yang di kirimkan dalam parameter. jadinya data yang sama atau lebih kecil akan di reject semua
	 */
	public void rejectAllWaitingApprovalData ( Date latestDateToFetch ,   String remarkForAllRejected , AsyncCallback<Void> callback );
	
	/**
	 * ini untuk operasi dual control, untuk permintaan : <ol>
	 * <li>membuat data baru</li>
	 * <li>hapus data</li>
	 * <li>edit data</li>
	 * </ol>
	 * dari sini data akan muncul pada screen approval
	 * @param dualControlledData data dual control yang di submit untuk proses approval
	 * @param operation operasi yang di lakukan. cretae/ update delete
	 *  
	 * 
	 * @return id dari object yang di create dalam database
	 **/
	public void submitDataForApproval (  CommonDualControlContainerTable dualControlledData , DualControlEnabledOperation operation , AsyncCallback<Long> callback) ;
	
	/**
	 * perbaikan dari {@link #submitDataForApproval(CommonDualControlContainerTable, DualControlEnabledOperation)} , ini dengan return yang lebih lengkap
	 */
	public void submitMasterDataDataForApproval (  CommonDualControlContainerTable dualControlledData , DualControlEnabledOperation operation , DoubleSubmitProtectedAsyncCallback<SimpleMasterDataDualControlApprovalResult> callback)  ;
	/**
	 * approve data dan apply langsung ke table target
	 **/
	public void approveAndApplyData ( CommonDualControlContainerTable dualControlledData , DoubleSubmitProtectedAsyncCallback<Void> callback) ; 
	/**
	 * membaca data object untuk di approve
	 * @param objectFQCN full qualified class name dari object yang perlu di baca
	 * @param filters query filters. dalam kasus ini, yang di pakai adalah key 1 dan key2 
	 * @param sortArguments argument untuk proses sorting data
	 **/
	public void getDataRequiredApproval(String objectFQCN , SimpleQueryFilter[] filters , SimpleSortArgument[] sortArguments , int pageSize , int page , AsyncCallback<PagedResultHolder<CommonDualControlContainerTable>> callback);
	
	
	
	
	
	/**
	 * get data untuk proses update
	 **/
	public void getDataForEditList(
			String objectFQCN, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments, int pageSize, int page, AsyncCallback<PagedResultHolder<? extends DualControlEnabledData<?, ?>>> callback)  ;
	
	
	
	
	/**
	 * ini unutk membaca data data detail dari bulk approval. data di simpan dalam json array. method ini akan mereverse balik data menjadi object dan mengembalikan data ke client kembali. Sementara tidak ada filter yang di mungkinkan. Hanya paging data yang di sertakan. karena detail dari data tidak di simpan dalam database
	 * @param approvalDataId id dari approval data. ini refer ke table : m_dual_control_table
	 * @param pageSize ukuran page per pembacaan data
	 * @param page page berapa yang akan di baca
	 * 
	 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
	 */
	public <DATA extends DualControlEnabledData<?,?>> void getBulkApprovalDataDetails ( Long approvalDataId , int pageSize, int page , AsyncCallback<PagedResultHolder<DATA>> callback)  ;
	
	
	
	/**
	 * membaca definisi dual control table
	 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
	 */
	public void getMasterDataDualControlDefinitions (AsyncCallback<List<DualControlDefinition>> callback) ;
	
	
	/**
	 * approve data bulk. yang di kirim cuma Id dari approval data
	 */
	public void approveAndApplyBulkData ( Long bulkDataId , AsyncCallback<Void> callback )   ;
	
	
	
	/**
	 * reject data bulk
	 * @param bulkDataId id dari data bulk
	 * @param rejectReason alasan penolakan
	 */
	public void rejectBulkData ( Long bulkDataId , String rejectReason, AsyncCallback<Void> callback)  ;
	
	
	/**
	 * handler upload bulk upload
	 * @param uploadedDataKey key untuk file upload. ini unutk mencari file dalam session
	 * @param targetClassFQCN fqcn dari item yang di handle
	 * @param remark catatan dari data
	 *  
	 */
	public  void handleUploadedMasterFile(
			  String  uploadedDataKey,
			   String targetClassFQCN ,
			  String remark , AsyncCallback<HeaderDataOnlyCommonDualControlContainerTable> callback
			 ) ;
	
	
	/**
	 * membaca data konfigurasi berbasis sistem 
	 * @param fqcn fqcn dari data
	 */
	public <D extends SystemParamDrivenClass<?, ?>> void getSytemConfigurationDrivenData (String fqcn , AsyncCallback<AppConfigurationDrivenDetaiResultHolder<D>> callback) ; 
	/**
	 * membaca data reject list. data di cari dengan current user. jadinya data di tampilan untuk data yang match dengan : 
	 * creator = current user
	 * rejector = current user. 
	 * @param filters filter ini exclude user dan status. status akan di apply dari dao
	 */
	public void getRejectListData (SimpleQueryFilter[] filters , SimpleSortArgument[] sorts , int page , int pageSize , AsyncCallback<PagedResultHolder<CommonDualControlContainerTable>> callback) ;
	
}
