/**
 * 
 */
package id.co.sigma.common.server.gwt.rpc.security;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.PasswordPolicy;
import id.co.sigma.common.security.rpc.PasswordPolicyRPCService;
import id.co.sigma.security.server.service.IPasswordPolicyService;



import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Dode
 * @version $Id
 * @since Jan 30, 2013, 3:42:08 PM
 */

public class PasswordPolicyRPCServiceImpl extends BaseSecurityRPCService<PasswordPolicyRPCService>
		implements PasswordPolicyRPCService {

	

	@Autowired
	private IPasswordPolicyService passwordPolicyService;
	
	@Override
	public PagedResultHolder<PasswordPolicy> getPasswordPolicyData(
			int pagePosition, int pageSize) throws Exception {
		return passwordPolicyService.getPasswordPolicy(pagePosition, pageSize);
	}

	@Override
	public void insert(PasswordPolicy data) throws Exception {
		passwordPolicyService.insert(data);
	}

	@Override
	public void update(PasswordPolicy data) throws Exception {
		passwordPolicyService.update(data);
	}

	@Override
	public void remove(Long id) throws Exception {
		passwordPolicyService.remove(id);
	}

	@Override
	public Class<PasswordPolicyRPCService> implementedInterface() {
		return PasswordPolicyRPCService.class;
	}

}
