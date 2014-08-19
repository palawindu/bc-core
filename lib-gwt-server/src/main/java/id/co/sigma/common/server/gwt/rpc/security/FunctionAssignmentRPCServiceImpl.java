/**
 * 
 */
package id.co.sigma.common.server.gwt.rpc.security;


import id.co.sigma.common.security.domain.ApplicationMenuAssignment;
import id.co.sigma.common.security.rpc.FunctionAssignmentRPCService;
import id.co.sigma.security.server.service.IFunctionAssignmentService;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * rpc untuk function assignment
 * @author Dode
 * @version $Id
 * @since Jan 8, 2013, 2:24:17 PM
 */
public class FunctionAssignmentRPCServiceImpl extends
		BaseSecurityRPCService<FunctionAssignmentRPCService> implements FunctionAssignmentRPCService {

	

	@Autowired
	private IFunctionAssignmentService functionAssignmentService;
	
	@Override
	public List<Long> getFunctionIdByGroupId(Long groupId)
			throws Exception {
		return functionAssignmentService.getFunctionIdByGroupId(groupId);
	}

	@Override
	public void addRemoveFunctionAssignment(List<ApplicationMenuAssignment> addedMenuItem,
			List<ApplicationMenuAssignment> removedMenuItem) throws Exception {
		functionAssignmentService.addRemoveFunctionAssignment(addedMenuItem, removedMenuItem);
	}

	@Override
	public Class<FunctionAssignmentRPCService> implementedInterface() {
		return FunctionAssignmentRPCService.class;
	}

	

}
