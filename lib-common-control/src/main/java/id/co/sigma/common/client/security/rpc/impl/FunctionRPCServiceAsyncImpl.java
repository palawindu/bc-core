package id.co.sigma.common.client.security.rpc.impl;

import java.math.BigInteger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import id.co.sigma.common.security.domain.PageDefinition;
import id.co.sigma.common.security.rpc.FunctionRPCService;
import id.co.sigma.common.client.rpc.ManualJSONSerializeRPCService;
import id.co.sigma.common.client.security.rpc.FunctionRPCServiceAsync;

public class FunctionRPCServiceAsyncImpl extends ManualJSONSerializeRPCService<FunctionRPCService> implements FunctionRPCServiceAsync{

	@Override
	protected Class<FunctionRPCService> getServiceInterface() {
		return FunctionRPCService.class;
	}
	
		public void eraseApplicationMenu(java.math.BigInteger param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "eraseApplicationMenu", new Class<?>[]{
			java.math.BigInteger.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void appendNewMenuNode(id.co.sigma.common.security.dto.ApplicationMenuDTO param0,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.security.dto.ApplicationMenuDTO> callback) {
		this.submitRPCRequestRaw( "appendNewMenuNode", new Class<?>[]{
			id.co.sigma.common.security.dto.ApplicationMenuDTO.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getCurrentAppAvailablePages(id.co.sigma.common.data.query.SigmaSimpleQueryFilter[] param0,id.co.sigma.common.data.query.SigmaSimpleSortArgument[] param1,int param2,int param3,com.google.gwt.user.client.rpc.AsyncCallback<id.co.sigma.common.data.PagedResultHolder<id.co.sigma.common.security.dto.PageDefinitionDTO>> callback) {
		this.submitRPCRequestRaw( "getCurrentAppAvailablePages", new Class<?>[]{
			id.co.sigma.common.data.query.SigmaSimpleQueryFilter[].class,id.co.sigma.common.data.query.SigmaSimpleSortArgument[].class,int.class,int.class, 
			
		}, 
		new Object[]{
			 param0, param1, param2, param3, 
		}, 
		callback); 	
	}


	public void updateApplicationMenu(id.co.sigma.common.security.dto.ApplicationMenuDTO param0,com.google.gwt.user.client.rpc.AsyncCallback<java.lang.Void> callback) {
		this.submitRPCRequestRaw( "updateApplicationMenu", new Class<?>[]{
			id.co.sigma.common.security.dto.ApplicationMenuDTO.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(java.math.BigInteger param0,com.google.gwt.user.client.rpc.AsyncCallback<java.util.List<id.co.sigma.common.security.domain.Function>> callback) {
		this.submitRPCRequestRaw( "getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder", new Class<?>[]{
			java.math.BigInteger.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getCurrentAppMenuDToByAppIdOrderByTreeLevelAndSiblingOrder(com.google.gwt.user.client.rpc.AsyncCallback<java.util.List<id.co.sigma.common.security.dto.ApplicationMenuDTO>> callback) {
		this.submitRPCRequestRaw( "getCurrentAppMenuDToByAppIdOrderByTreeLevelAndSiblingOrder", new Class<?>[]{
			 
			
		}, 
		new Object[]{
			 
		}, 
		callback); 	
	}


	public void getFunctionByGroupIdOrderByTreeLevelAndSiblingOrder(java.util.List<java.math.BigInteger> param0,com.google.gwt.user.client.rpc.AsyncCallback<java.util.List<id.co.sigma.common.security.domain.Function>> callback) {
		this.submitRPCRequestRaw( "getFunctionByGroupIdOrderByTreeLevelAndSiblingOrder", new Class<?>[]{
			java.util.List.class, 
			
		}, 
		new Object[]{
			 param0, 
		}, 
		callback); 	
	}


	public void getCurrentAppMenusOrderByTreeLevelAndSiblingOrder(com.google.gwt.user.client.rpc.AsyncCallback<java.util.List<id.co.sigma.common.security.domain.Function>> callback) {
		this.submitRPCRequestRaw( "getCurrentAppMenusOrderByTreeLevelAndSiblingOrder", new Class<?>[]{
			 
			
		}, 
		new Object[]{
			 
		}, 
		callback); 	
	}

	@Override
	public void getPageDefinition(BigInteger page,
			AsyncCallback<PageDefinition> callback) {
		this.submitRPCRequestRaw( "getPageDefinition", new Class<?>[]{
				BigInteger.class	 
				
		}, 
		new Object[]{
				page
		}, 
		callback); 	
		
	}




	

}

