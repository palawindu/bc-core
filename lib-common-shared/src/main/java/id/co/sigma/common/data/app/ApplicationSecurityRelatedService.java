package id.co.sigma.common.data.app;

import id.co.sigma.common.data.app.security.ClientSecurityData;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;




/**
 * rpc untuk security related task
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
//@RemoteServiceRelativePath(value="/rpc/security/application-security.app-rpc")
public interface ApplicationSecurityRelatedService extends JSONSerializedRemoteService{


	
	/**
	 * membaca current user security data
	 **/
	public ClientSecurityData getCurrentUserSecurityData () ; 
	
	
	
}
