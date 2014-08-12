package id.co.sigma.common.server.gwt.rpc.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.gwt.rpc.IRPCHandler;
import id.co.sigma.common.server.gwt.rpc.IRPCHandlerManager;
import id.co.sigma.common.server.util.IDTOGenerator;
import id.co.sigma.common.server.util.IObjectCleanUp;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



/**
 * 
 * base class untuk menangani RPC(custom-berdasarkan manual json serialization)
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseRPCHandler<T> implements IRPCHandler<T>,  InitializingBean , ApplicationContextAware{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRPCHandler.class);
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.applicationContext = ctx ; 
		
	}
	
	
	
	private  ApplicationContext applicationContext ;  
	//@Autowired
	private IRPCHandlerManager handlerManager ;
	
	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ; 
	
	public BaseRPCHandler(){
		LOGGER.info("[RPC]RPC handler :" + this.getClass().getName() + " di init untuk menghandler" );
		
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		handlerManager = (IRPCHandlerManager) applicationContext.getBean("rpc-handler-manager");
		handlerManager.registerHandler(this);
		LOGGER.info("[RPC]handle class : " + this.getClass().getName() + " di register ke manager RPC");
		
	}
	
	
	
	public void setHandlerManager(IRPCHandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}
	
	public IRPCHandlerManager getHandlerManager() {
		return handlerManager;
	}
	
	
	/**
	 * common utuls, membaca data dalam posisi paged
	 * @throws Exception 
	 **/
	protected <POJO> PagedResultHolder<POJO> selectDataPaged (Class<POJO> entityClass , SimpleQueryFilter[] filters , SimpleSortArgument[] sortArgs , int pageSize , int page) throws Exception {
		return selectDataPaged(entityClass, filters, sortArgs, pageSize, page ,(IObjectCleanUp<POJO>) null ) ; 
	}
	
	
	/**
	 * common utuls, membaca data dalam posisi paged
	 * @throws Exception 
	 **/
	protected <POJO> PagedResultHolder<POJO> selectDataPaged (Class<POJO> entityClass , SimpleQueryFilter[] filters , SimpleSortArgument[] sortArgs , int pageSize , int page , IObjectCleanUp<POJO> cleaner) throws Exception {
		Long cnt =  generalPurposeDao.count(entityClass, filters); 
		if ( cnt==null||cnt.longValue()==0)
			return null ; 
		PagedResultHolder<POJO> retval = new PagedResultHolder<POJO>(); 
		int firstRow = retval.adjustPagination(page, pageSize, cnt.intValue());
		List<POJO> datas =  generalPurposeDao.list(entityClass, filters, sortArgs, pageSize, firstRow); 
		retval.setHoldedData(datas); 
		if ( cleaner!=null && datas!= null){
			for ( POJO scn : datas){
				cleaner.cleanUp(scn); 
			}
		}
		return retval ; 
	}
	
	
	/**
	 * common utuls, membaca data dalam posisi paged
	 * @throws Exception 
	 **/
	protected <POJO , DTO> PagedResultHolder<DTO> selectDataPaged (Class<POJO> entityClass , SimpleQueryFilter[] filters , SimpleSortArgument[] sortArgs , int pageSize , int page , IDTOGenerator<POJO , DTO> dtoGenerator) throws Exception {
		Long cnt =  generalPurposeDao.count(entityClass, filters); 
		if ( cnt==null||cnt.longValue()==0)
			return null ; 
		PagedResultHolder<DTO> retval = new PagedResultHolder<DTO>(); 
		int firstRow = retval.adjustPagination(page, pageSize, cnt.intValue());
		List<POJO> datas =  generalPurposeDao.list(entityClass, filters, sortArgs, pageSize, firstRow); 
		ArrayList<DTO> actualDatas = new ArrayList<DTO>(); 
		retval.setHoldedData(actualDatas); 
		
		if ( dtoGenerator!=null && datas!= null){
			for ( POJO scn : datas){
				actualDatas.add( dtoGenerator.generateDTO( scn)); 
			}
		}
		return retval ; 
	}
	
	
	
	/**
	 * membaca data dengan ID dari data.<br/>
	 * Data ini di baca dengan ID long
	 * @param classFqcn FQCN dari class hendak di load
	 * @param dataId id dari data. versi ini dengan long
	 *  
	 */
	public <POJO> POJO getDataById (String classFqcn  , Long dataId ) throws Exception {
		if ( dataId== null || classFqcn== null || classFqcn.isEmpty())
			return null ; 
		Class<?> t = Class.forName(classFqcn); 
		return generalPurposeDao.get(t, dataId); 
	}
	
	/**
	 * membaca data dengan ID dari data.<br/>
	 * Data ini di baca dengan ID {@link BigInteger}
	 * @param classFqcn FQCN dari class hendak di load
	 * @param dataId id dari data. versi ini dengan {@link BigInteger}
	 *  
	 */
	public <POJO> POJO getDataById (String classFqcn  , BigInteger dataId ) throws Exception {
		if ( dataId== null || classFqcn== null || classFqcn.isEmpty())
			return null ; 
		Class<?> t = Class.forName(classFqcn); 
		return generalPurposeDao.get(t, dataId); 
	}
	
	/**
	 * membaca data dengan ID dari data.<br/>
	 * Data ini di baca dengan ID {@link Integer}
	 * @param classFqcn FQCN dari class hendak di load
	 * @param dataId id dari data. versi ini dengan {@link Integer}
	 *  
	 */
	public <POJO> POJO getDataById (String classFqcn  , Integer dataId ) throws Exception {
		if ( dataId== null || classFqcn== null || classFqcn.isEmpty())
			return null ; 
		Class<?> t = Class.forName(classFqcn); 
		return generalPurposeDao.get(t, dataId); 
	}
	
	
	/**
	 * membaca data dengan ID dari data.<br/>
	 * Data ini di baca dengan ID {@link String}
	 * @param classFqcn FQCN dari class hendak di load
	 * @param dataId id dari data. versi ini dengan {@link String}
	 *  
	 */
	public <POJO> POJO getDataById (String classFqcn  , String dataId ) throws Exception {
		if ( dataId== null || classFqcn== null || classFqcn.isEmpty())
			return null ; 
		Class<?> t = Class.forName(classFqcn); 
		return generalPurposeDao.get(t, dataId); 
	}
	
	
	/**
	 * versi ini membaca data dengan key JSON friendly object
	 * @param classFqcn FQCN dari class yang hendak di baca
	 * @param dataId ID dari data
	 */
	public <POJO, KEY extends IJSONFriendlyObject<KEY>> POJO getDataById (String classFqcn  , KEY dataId ) throws Exception {
		if ( dataId== null || classFqcn== null || classFqcn.isEmpty())
			return null ; 
		Class<?> t = Class.forName(classFqcn); 
		return generalPurposeDao.get(t, dataId); 
	}
	
	
}
