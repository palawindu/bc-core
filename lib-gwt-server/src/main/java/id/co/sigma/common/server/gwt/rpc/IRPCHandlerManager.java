/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.server.gwt.rpc;

import id.co.sigma.common.data.serializer.json.RPCResponseWrapper;
import id.co.sigma.common.exception.LoginExpiredException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gps
 */
public interface IRPCHandlerManager {
    
    /**
     * handler RPC request
    */
    public void registerHandler (IRPCHandler handler ) ; 
    
    
    
    /**
     * invoke RPC request
     * @param request request object reference. parameter di baca dari sini
     * @exception LoginExpiredException ini kalau login sudah habis
     * @exception Exception exception dari masing-masing handler
     */
    public RPCResponseWrapper invokeRPCRequest (HttpServletRequest request   ) throws LoginExpiredException ,  Exception  ; 
    
    
}
