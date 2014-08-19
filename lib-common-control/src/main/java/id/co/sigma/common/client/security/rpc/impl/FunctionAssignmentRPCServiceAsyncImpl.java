package id.co.sigma.common.client.security.rpc.impl;



import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import id.co.sigma.common.security.rpc.FunctionAssignmentRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.FunctionAssignmentRPCServiceAsync;
import id.co.sigma.common.data.app.security.MenuEditingData;

public class FunctionAssignmentRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<FunctionAssignmentRPCService> implements FunctionAssignmentRPCServiceAsync{

	@Override
	protected Class<FunctionAssignmentRPCService> getServiceInterface() {
		return FunctionAssignmentRPCService.class;
	}
	@Override
	public void getFunctionIdByGroupId(Long groupId,
			AsyncCallback<List<Long>> callback) throws Exception {
		this.submitRPCRequestRaw( "getFunctionIdByGroupId", new Class<?>[]{
				Long.class,
			}, 
			new Object[]{
				groupId, 
			}, 
			callback);
		
	}
		

	public void addRemoveFunctionAssignment(java.util.List<id.co.sigma.common.security.domain.ApplicationMenuAssignment> param0,java.util.List<id.co.sigma.common.security.domain.ApplicationMenuAssignment> param1,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "addRemoveFunctionAssignment", new Class<?>[]{
			java.util.List.class,java.util.List.class, 
			
		}, 
		new Object[]{
			 param0, param1, 
		}, 
		callback); 	
	}

	




	

}


