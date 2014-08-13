package id.co.sigma.common.server.gwt.rpc;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletConfig;


import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

/**
 * rpc url mapper Sigma
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * @since 5 Aug 2012
 **/
public class RPCServiceUrlMapper extends SimpleUrlHandlerMapping implements SelfRegisterRPCUrlMapper , ServletConfigAware{
	Map<String, RPCServletWrapperController> urlHandlerMap ; 
	
	
	
	protected ServletConfig servletConfig;
	
	protected int automaticServletNameCounter =1 ; 
	
	public RPCServiceUrlMapper(){
		setOrder(1); 
		urlHandlerMap= new HashMap<String, RPCServletWrapperController>();
		
	}
	
	
	@Override
	public void registerRPCService(RPCServletWrapperController wrappedRPCservlet) { 
		//registerHandler(wrappedRPCservlet.getRemoteServicePath(), wrappedRPCservlet);  
		//getServletContext().addServlet("sigma-rpc-servlet" + automaticServletNameCounter,(Servlet) wrappedRPCservlet).addMapping(wrappedRPCservlet.getRemoteServicePath());
		automaticServletNameCounter++;
	}
		
		


	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig = servletConfig; 
		if ( servletConfig==null){
			System.out.println("servlet config null @" + this.getClass().getName());
		}
		else{
			System.out.println("not null @" + this.getClass().getName());
		}
		
	}
	
}
