package id.co.sigma.common.client.rpc.impl;

import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.rpc.SessionTimerRPCServiceAsync;
import id.co.sigma.common.rpc.common.SessionTimerRpcService;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class SessionTimerRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<SessionTimerRpcService> implements SessionTimerRPCServiceAsync {

	@Override
	public void getSessionTimeoutLength(AsyncCallback<Long> callback) {
		this.submitRPCRequestRaw( "getSessionTimeoutLength",
			new Class[]{
		}, 
		
		new Object []{
		}, callback);
	}

	@Override
	protected Class<SessionTimerRpcService> getServiceInterface() {
		return SessionTimerRpcService.class;
	}



}
