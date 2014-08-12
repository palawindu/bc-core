package id.co.sigma.common.server.gwt.rpc.system;

import id.co.sigma.common.data.app.ApplicationSecurityRelatedService;
import id.co.sigma.common.data.app.security.ClientSecurityData;
import id.co.sigma.common.server.gwt.rpc.BaseServerRPCService;




/**
 * RPC class untuk handle security related task
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/

public class ApplicationSecurityRelatedServiceImpl extends BaseServerRPCService<ApplicationSecurityRelatedService> implements ApplicationSecurityRelatedService{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7059080708211478057L;
	/*@Autowired
	@Named("sigma-session-registry")
	private ISigmaSessionRegistry sigmaSessionRegistry;*/

	@Override
	public ClientSecurityData getCurrentUserSecurityData() {
//		List<Object> swap = sigmaSessionRegistry.getAllPrincipals(); 
//		
//		SigmaSimpleUserData usr =  getCurrentUser();
		
		System.out.println("method id.co.sigma.common.server.rpc.system.ApplicationSecurityRelatedServiceImpl.getCurrentUserSecurityData() masih hard codeed" );
		ClientSecurityData retval = new ClientSecurityData() ; 
		/*if ( usr!=null){
			retval.setFullName(usr.getFullName());
			retval.setSecurityToken(usr.getUuid());
			retval.setUserName(usr.getUsername());
			
			//FIXME : pemilihan branch blm jalan
			//retval.setUnitCode(unitCode)
		}*/
		return retval;
	}

	@Override
	public Class<ApplicationSecurityRelatedService> implementedInterface() {
		return ApplicationSecurityRelatedService.class;
	} 
}
