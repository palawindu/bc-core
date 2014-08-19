package id.co.sigma.common.client.security.rpc.impl;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;
import id.co.sigma.common.security.rpc.GroupAssignmentRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.GroupAssignmentRPCServiceAsync;

public class GroupAssignmentRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<GroupAssignmentRPCService> implements GroupAssignmentRPCServiceAsync{

	@Override
	protected Class<GroupAssignmentRPCService> getServiceInterface() {
		return GroupAssignmentRPCService.class;
	}
	
	public void delete(Long id,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "delete", new Class<?>[]{
			Long.class, 
			
		}, 
		new Object[]{
			 id, 
		}, 
		callback); 	
	}


	public void insert(id.co.sigma.common.security.domain.UserGroupAssignment param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "insert", new Class<?>[]{
			id.co.sigma.common.security.domain.UserGroupAssignment.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	


	public void getAllGroup(id.co.sigma.common.security.domain.UserGroupAssignment param0,int param1,int param2,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.dto.UserGroupAssignmentDTO>> callback) {
		this.submitRPCRequestRaw( "getAllGroup", new Class<?>[]{
			id.co.sigma.common.security.domain.UserGroupAssignment.class,int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, 
		}, 
		callback); 	
	}

	@Override
	public void getUserGroupByUserId(Long userId,
			AsyncCallback<List<UserGroupAssignmentDTO>> callback) {
		this.submitRPCRequestRaw( "getUserGroupByUserId", new Class<?>[]{
				Long.class,
			}, 
			new Object[]{
				userId, 
			}, 
			callback); 	
		
	}




	

}

