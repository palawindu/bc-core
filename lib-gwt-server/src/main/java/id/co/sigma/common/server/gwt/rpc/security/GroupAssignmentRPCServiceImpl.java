package id.co.sigma.common.server.gwt.rpc.security;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;
import id.co.sigma.common.security.rpc.GroupAssignmentRPCService;
import id.co.sigma.security.server.service.IUserGroupAssignmentService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class GroupAssignmentRPCServiceImpl extends BaseSecurityRPCService<GroupAssignmentRPCService> implements GroupAssignmentRPCService{



	@Autowired
	private IUserGroupAssignmentService groupAssignmentService;
	
	@Override
	public PagedResultHolder<UserGroupAssignmentDTO> getAllGroup(UserGroupAssignment parameter, int pagePosition, int pageSize) throws Exception {	
		return groupAssignmentService.getGroupAssignmentByParameter(parameter, pagePosition, pageSize);
	}

	@Override
	public void insert(UserGroupAssignment data) throws Exception {
		groupAssignmentService.insert(data);
	}

	@Override
	public void delete(Long data) throws Exception {
		UserGroupAssignment parameter = new UserGroupAssignment();
		parameter.setId(data);
		groupAssignmentService.delete(parameter);	
	}

	@Override
	public List<UserGroupAssignmentDTO> getUserGroupByUserId(Long userId) throws Exception {		
		return groupAssignmentService.getGroupByUserId(userId);
	}

	@Override
	public Class<GroupAssignmentRPCService> implementedInterface() {
		return GroupAssignmentRPCService.class;
	}
}