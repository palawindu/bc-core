package id.co.sigma.common.client.security.rpc.impl;

import java.math.BigInteger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import id.co.sigma.common.security.rpc.UserRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.UserRPCServiceAsync;

public class UserRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<UserRPCService> implements UserRPCServiceAsync{

	@Override
	protected Class<UserRPCService> getServiceInterface() {
		return UserRPCService.class;
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


	public void insert(id.co.sigma.common.security.domain.User param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "insert", new Class<?>[]{
			id.co.sigma.common.security.domain.User.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void update(id.co.sigma.common.security.domain.User param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "update", new Class<?>[]{
			id.co.sigma.common.security.domain.User.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getUserByParameter(id.co.sigma.common.data.query.SigmaSimpleQueryFilter[] param0,int param1,int param2,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.dto.UserDTO>> callback) {
		this.submitRPCRequestRaw( "getUserByParameter", new Class<?>[]{
			id.co.sigma.common.data.query.SigmaSimpleQueryFilter[].class,int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, 
		}, 
		callback); 	
	}


	public void getUserByParameter(java.math.BigInteger param0,id.co.sigma.common.data.query.SigmaSimpleQueryFilter[] param1,int param2,int param3,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.dto.UserDTO>> callback) {
		this.submitRPCRequestRaw( "getUserByParameter", new Class<?>[]{
			java.math.BigInteger.class,id.co.sigma.common.data.query.SigmaSimpleQueryFilter[].class,int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, param3, 
		}, 
		callback); 	
	}


	public void getUserByFilter(id.co.sigma.common.data.query.SigmaSimpleQueryFilter[] param0,int param1,int param2,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.domain.User>> callback) {
		this.submitRPCRequestRaw( "getUserByFilter", new Class<?>[]{
			id.co.sigma.common.data.query.SigmaSimpleQueryFilter[].class,int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, 
		}, 
		callback); 	
	}


	public void resetPassword(id.co.sigma.common.security.domain.User param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "resetPassword", new Class<?>[]{
			id.co.sigma.common.security.domain.User.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getCurrentUserLogin(com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.security.dto.UserDetailDTO> callback) {
		this.submitRPCRequestRaw( "getCurrentUserLogin", new Class<?>[]{
			 
			
		}, 
		new Object[]{
			 
		}, 
		callback); 	
	}

	@Override
	public void lockUser(BigInteger userId, AsyncCallback<Void> callback) {
		this.submitRPCRequestRaw( "lockUser", new Class<?>[]{
				BigInteger.class, 
				
			}, 
			new Object[]{
				userId, 
			}, 
			callback); 	
		
	}

	@Override
	public void unlockUser(BigInteger userId, AsyncCallback<Void> callback) {
		this.submitRPCRequestRaw( "unlockUser", new Class<?>[]{
				BigInteger.class, 
				
			}, 
			new Object[]{
				userId, 
			}, 
			callback); 	
		
	}




	

}


