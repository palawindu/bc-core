package id.co.sigma.common.server.gwt.rpc.security;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.security.domain.Branch;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.dto.UserDTO;
import id.co.sigma.common.security.dto.UserDetailDTO;
import id.co.sigma.common.security.exception.PasswordPolicyException;
import id.co.sigma.common.security.exception.UserNotExistException;
import id.co.sigma.common.security.rpc.UserRPCService;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.security.server.UserDetailUtils;
import id.co.sigma.security.server.service.IUserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User RPC Impl
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 1:42:47 PM
 * @version $Id
 */

public class UserRPCServiceImpl extends BaseSecurityRPCService<UserRPCService> implements UserRPCService{

	
	private static final Logger logger = LoggerFactory.getLogger(UserRPCServiceImpl.class); 
	

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao;
	
	private Branch getBranchByCode(String code) {
		List<Serializable> filters = new ArrayList<Serializable>();
		filters.add(code);
		
		List<Branch> data = null;
		try {
			data = generalPurposeDao.loadDataByKeys(Branch.class, "branchCode", filters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data == null || data.isEmpty() ? null : data.get(0);
	}
	
	
	@Override
	public PagedResultHolder<UserDTO> getUserByParameter(SimpleQueryFilter[] filter, int page, int pageSize) throws Exception{		
		return userService.getUserByParameter(filter, page, pageSize);
	}

	@Override
	public PagedResultHolder<UserDTO> getUserByParameter(Long applicationId, SimpleQueryFilter[] filter,int page, int pageSize) throws Exception {	
		return userService.getUserAtWorklistByParam(applicationId, filter, page, pageSize);
	}

	@Override
	public PagedResultHolder<User> getUserByFilter(
			SimpleQueryFilter[] filters, int pagePosition, int pageSize)
			throws Exception {
		return userService.getUserByFilter(filters, pagePosition, pageSize);
	}

	@Override
	public void insert(User data) throws Exception, PasswordPolicyException {
		try {
			userService.insert(data);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(User data) throws Exception {
		userService.update(data);
	}

	@Override
	public void remove(Long id) throws Exception {
		userService.remove(id);
	}

	@Override
	public void resetPassword(User data) throws Exception, PasswordPolicyException {
		userService.updateUserPassword(data);
	}

	@Override
	public UserDetailDTO getCurrentUserLogin() {	
		try {
			UserDetailDTO userDetails = UserDetailUtils.getInstance().getUserDetailDTOFromSecurityContext();
			
			Branch b = getBranchByCode(userDetails.getCurrentBranch());
			if ( b!= null)
				userDetails.setCurrentBranchName(b.getBranchName());
			
			return userDetails;
		} catch (Exception e) {
			logger.error("gagal membaca data login. error : " + e.getMessage()  , e);
			return null ; 
		}
		
	}

	@Override
	public Class<UserRPCService> implementedInterface() {
		return UserRPCService.class;
	}


	@Override
	public void lockUser(Long userId)  throws UserNotExistException{
		User dbUser = null ;
		try {
			dbUser= generalPurposeDao.get(User.class, userId);
		} catch (Exception e) {
			dbUser = null ; 
			logger.error("gagal membaca data user dengan ID : " + userId +",error : " + e.getMessage() , e); 
		}
		if ( dbUser== null ){
			throw new UserNotExistException("User dengan id : "  + userId + "tidak detemukan.mungkin data sudah di hapus");
		}
		dbUser.setActiveFlag("D");
		try {
			userService.update(dbUser);
		} catch (Exception e) {
			logger.error("gagal menyimpan user untuk proses lock, error di laporkan : " + e.getMessage() , e);
			e.printStackTrace();
		}
		
	}


	@Override
	public void unlockUser(Long userId) throws UserNotExistException {
		User dbUser = null ;
		try {
			dbUser= generalPurposeDao.get(User.class, userId);
		} catch (Exception e) {
			dbUser = null ; 
			logger.error("gagal membaca data user dengan ID : " + userId +",error : " + e.getMessage() , e); 
		}
		if ( dbUser== null ){
			throw new UserNotExistException("User dengan id : "  + userId + "tidak detemukan.mungkin data sudah di hapus");
		}
		dbUser.setActiveFlag("A");
		try {
			userService.update(dbUser);
		} catch (Exception e) {
			logger.error("gagal menyimpan user untuk proses unlock, error di laporkan : " + e.getMessage() , e);
			e.printStackTrace();
		}
		
	}
}