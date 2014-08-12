package id.co.sigma.common.client.security.rpc.impl;

import id.co.sigma.common.security.rpc.UserDomainRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.UserDomainRPCServiceAsync;

public class UserDomainRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<UserDomainRPCService> implements UserDomainRPCServiceAsync{

	@Override
	protected Class<UserDomainRPCService> getServiceInterface() {
		return UserDomainRPCService.class;
	}
	
		public void getUserDomainFromIIS(id.co.sigma.common.data.query.SimpleQueryFilter[] param0,int param1,int param2,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.menu.UserDomain>> callback) {
		this.submitRPCRequestRaw( "getUserDomainFromIIS", new Class<?>[]{
			id.co.sigma.common.data.query.SimpleQueryFilter[].class,int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, 
		}, 
		callback); 	
	}




	

}


