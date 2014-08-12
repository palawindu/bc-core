/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.server.gwt.rpc;

import javax.servlet.http.HttpServletRequest;

/**
 * interface untuk invoke method handler actual dari RPC<br/>
 * method ini tidak di proses manual. dengan reflection akan membaca method-method yang tersedia dalam 1 class dan membuatkan interface nya
 * @author gps
 */
public interface IRPCHandlerMethodInvoker {
    
    
	/**
	 * invoke method dalam class 
	 * @param request request interface
	 * @param serialzedArguments parameter yang di passing via http request
	 * @return return value dari method(kalau ada)
	 */
    public Object invokeMethod(   HttpServletRequest request ,    Object[] serialzedArguments ) throws Exception; 
    
}
