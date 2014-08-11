package id.co.sigma.common.client.rpc.impl;

import id.co.sigma.common.client.rpc.ApplicationSecurityRelatedServiceAsync;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;

import id.co.sigma.common.data.app.ApplicationSecurityRelatedService;

public class ApplicationSecurityRelatedServiceAsyncImpl extends ManualJSONSerializeRPCService<ApplicationSecurityRelatedService> implements ApplicationSecurityRelatedServiceAsync{

	@Override
	protected Class<ApplicationSecurityRelatedService> getServiceInterface() {
		return ApplicationSecurityRelatedService.class;
	}
	
		public void getCurrentUserSecurityData(com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.app.security.ClientSecurityData> callback) {
		this.submitRPCRequestRaw( "getCurrentUserSecurityData", new Class<?>[]{
			 
			
		}, 
		new Object[]{
			 
		}, 
		callback); 	
	}




	

}
