package id.co.sigma.common.client.security.rpc.impl;

import id.co.sigma.common.security.rpc.PasswordPolicyRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.PasswordPolicyRPCServiceAsync;

public class PasswordPolicyRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<PasswordPolicyRPCService> implements PasswordPolicyRPCServiceAsync{

	@Override
	protected Class<PasswordPolicyRPCService> getServiceInterface() {
		return PasswordPolicyRPCService.class;
	}
	
		public void remove(java.math.BigInteger param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "remove", new Class<?>[]{
			java.math.BigInteger.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void insert(id.co.sigma.common.security.domain.PasswordPolicy param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "insert", new Class<?>[]{
			id.co.sigma.common.security.domain.PasswordPolicy.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void update(id.co.sigma.common.security.domain.PasswordPolicy param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "update", new Class<?>[]{
			id.co.sigma.common.security.domain.PasswordPolicy.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getPasswordPolicyData(int param0,int param1,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.domain.PasswordPolicy>> callback) {
		this.submitRPCRequestRaw( "getPasswordPolicyData", new Class<?>[]{
			int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, 
		}, 
		callback); 	
	}




	

}

