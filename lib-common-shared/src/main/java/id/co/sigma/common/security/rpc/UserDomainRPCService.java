package id.co.sigma.common.security.rpc;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.security.menu.UserDomain;

/**
 * User domain untuk windows authentifikasi RPC Service
 * @author I Gede Mahendra
 * @since Nov 29, 2012, 5:46:36 PM
 * @version $Id
 */
//@RemoteServiceRelativePath(value="/sigma-rpc/user-domain.app-rpc")
public interface UserDomainRPCService extends JSONSerializedRemoteService{
	
	/**
	 * Mendapatkan user domain dari server IIS
	 * @param parameter
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PagedResultHolder<UserDomain> getUserDomainFromIIS(SimpleQueryFilter[] filter, int page, int pageSize);
}