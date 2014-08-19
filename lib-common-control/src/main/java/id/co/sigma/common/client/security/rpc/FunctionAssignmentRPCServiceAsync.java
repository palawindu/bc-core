/**
 * 
 */
package id.co.sigma.common.client.security.rpc;

import id.co.sigma.common.client.security.rpc.impl.FunctionAssignmentRPCServiceAsyncImpl;
import id.co.sigma.common.data.app.security.MenuEditingData;
import id.co.sigma.common.security.domain.ApplicationMenuAssignment;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Dode
 * @version $Id
 * @since Jan 8, 2013, 2:21:28 PM
 */
public interface FunctionAssignmentRPCServiceAsync {
	
	public static class Util {
		private static FunctionAssignmentRPCServiceAsync instance ; 
		
		public static FunctionAssignmentRPCServiceAsync getInstance() {
			if (instance==null){
				instance = GWT.create(FunctionAssignmentRPCServiceAsyncImpl.class);
//				CommonGlobalVariableHolder.getInstance().fixTargetUrl((ServiceDefTarget)instance);
			}			 
			return instance;
		}
	}
	
	/**
	 * get function id by group id
	 * @param groupId group id
	 * @return list of function id
	 * @throws Exception
	 */
	public void getFunctionIdByGroupId(Long groupId, AsyncCallback<List<Long>> callback) throws Exception;
	
	/**
	 * added by dode
	 * add and remove menu item from group menu item
	 * @param addedMenuItem list of added menu item
	 * @param removedMenuItem list of removed menu item
	 * @throws Exception
	 */
	public void addRemoveFunctionAssignment(List<ApplicationMenuAssignment> addedMenuItem, List<ApplicationMenuAssignment> removedMenuItem, AsyncCallback<Void> callback) throws Exception;
	
	 
}
