package id.co.sigma.common.server.gwt.rpc.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.rpc.common.GeneralPurposeRPC;
import id.co.sigma.common.server.gwt.rpc.BaseServerRPCService;
import id.co.sigma.common.server.service.IRPCSecurityService;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class GeneralPurposeRPCImpl extends BaseServerRPCService<GeneralPurposeRPC> implements GeneralPurposeRPC{

	
	@Autowired
	IRPCSecurityService rpcSecurityService ; 
	
	
	@Override
	public Class<GeneralPurposeRPC> implementedInterface() {
		return GeneralPurposeRPC.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagedResultHolder<IJSONFriendlyObject<?>> getPagedData(
			String objectFQCN, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sorts, int page, int pageSize)
			throws Exception {
		return (PagedResultHolder<IJSONFriendlyObject<?>>) selectDataPaged(Class.forName(objectFQCN), filters, sorts, pageSize, page);
	}

	@Override
	public IJSONFriendlyObject<?> getObjectByBigInteger(String objectFQCN,
			BigInteger objectId) throws Exception {
		if ( objectId== null)
			return null ;
		return (IJSONFriendlyObject<?>) generalPurposeDao.get(Class.forName(objectFQCN), objectId);
		
	}

	@Override
	public IJSONFriendlyObject<?> getObjectById(String objectFQCN, Long objectId)
			throws Exception {
		if ( objectId== null)
			return null ;
		return (IJSONFriendlyObject<?>) generalPurposeDao.get(Class.forName(objectFQCN), objectId);
	}

	@Override
	public IJSONFriendlyObject<?> getObjectById(String objectFQCN,
			Integer objectId) throws Exception {
		if ( objectId== null)
			return null ;
		return (IJSONFriendlyObject<?>) generalPurposeDao.get(Class.forName(objectFQCN), objectId);
	}

	@Override
	public IJSONFriendlyObject<?> getObjectById(String objectFQCN,
			String objectId) throws Exception {
		if ( objectId== null)
			return null ;
		return (IJSONFriendlyObject<?>) generalPurposeDao.get(Class.forName(objectFQCN), objectId);
	}

	@Override
	public String generateDoubleSubmitAvoidenceToken() {
		return rpcSecurityService.generateToken();
	}
	
	@Override
	public void insertData(IJSONFriendlyObject<?> newObject) throws Exception {
		rpcSecurityService.insertNewData(newObject); 
	}

}
