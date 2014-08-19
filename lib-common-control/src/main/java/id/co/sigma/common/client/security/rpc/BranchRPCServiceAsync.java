package id.co.sigma.common.client.security.rpc;

import id.co.sigma.common.security.domain.Branch;
import id.co.sigma.common.security.dto.BranchDTO;
import id.co.sigma.common.client.security.rpc.impl.BranchRPCServiceAsyncImpl;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;



import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BranchRPCServiceAsync {
	
	public static class Util {
		private static BranchRPCServiceAsync instance ; 
		
		public static BranchRPCServiceAsync getInstance() {
			if (instance==null){
				instance = GWT.create(BranchRPCServiceAsyncImpl.class);
//				CommonGlobalVariableHolder.getInstance().fixTargetUrl((ServiceDefTarget)instance);
			}			 
			return instance;
		}
	}
	
	/**
	 * Get branch by parameter
	 * @param filter
	 * @param page
	 * @param pageSize
	 * @param callback
	 */
	void getDataByParameter(SimpleQueryFilter[] filter, int page, int pageSize, AsyncCallback<PagedResultHolder<BranchDTO>> callback);
	
	/**
	 * Save or update data branch
	 * @param data
	 */
	void saveOrUpdateBranch(Branch data, AsyncCallback<Void> callback);
	
	/**
	 * remove data branch
	 * @param id
	 * @param callback
	 */
	void remove(Long id, AsyncCallback<Void> callback);
}