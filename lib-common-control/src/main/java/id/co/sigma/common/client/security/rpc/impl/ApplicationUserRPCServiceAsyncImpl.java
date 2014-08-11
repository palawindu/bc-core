package id.co.sigma.common.client.security.rpc.impl;

import id.co.sigma.common.security.rpc.ApplicationUserRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.ApplicationUserRPCServiceAsync;

public class ApplicationUserRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<ApplicationUserRPCService> implements ApplicationUserRPCServiceAsync{

	@Override
	protected Class<ApplicationUserRPCService> getServiceInterface() {
		return ApplicationUserRPCService.class;
	}
	
		public void insertOrUpdate(java.util.List<id.co.sigma.common.security.dto.UserGroupAssignmentDTO> param0,java.math.BigInteger param1,java.math.BigInteger param2,java.lang.String param3,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "insertOrUpdate", new Class<?>[]{
			java.util.List.class,java.math.BigInteger.class,java.math.BigInteger.class,java.lang.String.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, param3, 
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


	public void deleteApplicationUser(java.math.BigInteger param0,java.math.BigInteger param1,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "deleteApplicationUser", new Class<?>[]{
			java.math.BigInteger.class,java.math.BigInteger.class, 
			
		}, 
		new Object[]{
			 param0, param1, 
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


