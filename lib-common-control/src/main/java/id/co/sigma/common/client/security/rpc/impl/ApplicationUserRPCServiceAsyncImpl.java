package id.co.sigma.common.client.security.rpc.impl;



import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;
import id.co.sigma.common.security.rpc.ApplicationUserRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.ApplicationUserRPCServiceAsync;

public class ApplicationUserRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<ApplicationUserRPCService> implements ApplicationUserRPCServiceAsync{

	@Override
	protected Class<ApplicationUserRPCService> getServiceInterface() {
		return ApplicationUserRPCService.class;
	}
	
	
	@Override
	public void insertOrUpdate(List<UserGroupAssignmentDTO> data,
			Long applicationId, Long userId, String currentUser,
			AsyncCallback<Void> callback) {
		this.submitRPCRequestRaw( "getApplicationMenu", new Class<?>[]{
				List.class , 
				Long.class  , 
				Long.class , 
				String.class
				
			}, 
			new Object[]{
				 data , applicationId , userId  , currentUser  
			}, 
			callback);
		
	}
	


	public void getApplicationMenu(id.co.sigma.common.security.domain.Signon param0,com.google.gwt.user.client.rpc.AsyncCallback<java.util.List<id.co.sigma.common.security.menu.ApplicationMenuSecurity>> callback) {
		this.submitRPCRequestRaw( "getApplicationMenu", new Class<?>[]{
			id.co.sigma.common.security.domain.Signon.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	@Override
	public void deleteApplicationUser(Long applicationId, Long userId,
			AsyncCallback<Void> callback) {
		this.submitRPCRequestRaw( "deleteApplicationUser", new Class<?>[]{
			Long.class , 
			Long.class
			
		}, 
		new Object[]{
			 applicationId , userId 
		}, 
		callback); 	
	}


	public void countApplicationUserByParameter(id.co.sigma.common.security.domain.ApplicationUser param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Integer> callback) {
		this.submitRPCRequestRaw( "countApplicationUserByParameter", new Class<?>[]{
			id.co.sigma.common.security.domain.ApplicationUser.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}




	

}


