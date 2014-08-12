package id.co.sigma.common.server.service.system.impl;

import id.co.sigma.common.server.service.system.ICoreServerSessionRegistry;

import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;



/**
 * session registry. to be define
 **/
@Service(value="sigma-session-registry")
public class SigmaSessionRegistryImpl extends SessionRegistryImpl implements ICoreServerSessionRegistry{

	
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		// TODO Auto-generated method stub
		super.onApplicationEvent(event);
	}
}
