package id.co.sigma.common.client.rpc;

import id.co.sigma.common.client.rpc.impl.ApplicationSecurityRelatedServiceAsyncImpl;
import id.co.sigma.common.data.app.security.ClientSecurityData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ApplicationSecurityRelatedServiceAsync {

	
	public static class Util {
		private static ApplicationSecurityRelatedServiceAsync instance ;
		public static ApplicationSecurityRelatedServiceAsync getInstance() {
			if( instance==null){
				instance = GWT.create(ApplicationSecurityRelatedServiceAsyncImpl.class);
//				CommonGlobalVariableHolder.getInstance().fixTargetUrl((ServiceDefTarget)instance);
			}
			return instance;
		}
	}

	/**
	 * membaca current user security data
	 **/
	public void getCurrentUserSecurityData ( AsyncCallback<ClientSecurityData> callback) ; 
}