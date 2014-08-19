/**
 * 
 */
package id.co.sigma.common.server.gwt.rpc.security;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.exception.DataNotFoundException;
import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.security.domain.PageDefinition;
import id.co.sigma.common.security.dto.ApplicationMenuDTO;
import id.co.sigma.common.security.dto.PageDefinitionDTO;
import id.co.sigma.common.security.rpc.FunctionRPCService;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.util.IDTOGenerator;
import id.co.sigma.security.server.service.IApplicationService;
import id.co.sigma.security.server.service.IFunctionService;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * rpc untuk function
 * @author Dode
 * @version $Id
 * @since Jan 7, 2013, 10:30:26 AM
 */
public class FunctionRPCServiceImpl extends  BaseSecurityRPCService<FunctionRPCService>
		implements FunctionRPCService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(FunctionRPCServiceImpl.class); 
	@Autowired
	private IFunctionService functionService;
	
	
	@Autowired
	private IApplicationService applicationService ; 
	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ; 

	@Override
	public List<ApplicationMenu> getFunctionByGroupIdOrderByTreeLevelAndSiblingOrder(
			List<Long> groupIds) throws Exception {
		return functionService.getFunctionByGroupIdOrderByTreeLevelAndSiblingOrder(groupIds);
	}
	
	@Override
	public List<ApplicationMenu> getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(
			Long applicationId) throws Exception {
		List<ApplicationMenu> swap =  functionService.getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(applicationId);
		if ( swap != null&& !swap.isEmpty()){
			for ( ApplicationMenu scn : swap){
				scn.setApplication(null);
			}
		}
		return swap ; 
	}

	@Override
	public PagedResultHolder<PageDefinitionDTO> getCurrentAppAvailablePages(SimpleQueryFilter[] filters , SimpleSortArgument[] sortArgs , int pageSize , int page) throws Exception {
		
		
		Long appId = applicationService.getCurrentApplicationId() ; 
		SimpleQueryFilter appIdFilter = new SimpleQueryFilter("applicationId"  ,SimpleQueryFilterOperator.equal ,appId) ; 
		SimpleQueryFilter[]  actual = appendToArray(filters, appIdFilter);
		
		PagedResultHolder<PageDefinitionDTO>  retval =  selectDataPaged(PageDefinition.class, actual, sortArgs, pageSize, page , new IDTOGenerator<PageDefinition, PageDefinitionDTO>() {
			@Override
			public PageDefinitionDTO generateDTO(PageDefinition sourceData) {
				PageDefinitionDTO retval = new PageDefinitionDTO(sourceData); 
				 
				return retval;
			}
		
		} );
		return retval ; 
		
	}

	@Override
	public List<ApplicationMenu> getCurrentAppMenusOrderByTreeLevelAndSiblingOrder()
			throws Exception {
		Long currentAppId =  applicationService.getCurrentApplicationId();
		return getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(currentAppId);
	}

	@Override
	public List<ApplicationMenuDTO> getCurrentAppMenuDToByAppIdOrderByTreeLevelAndSiblingOrder()
			throws Exception {
		Long appId = applicationService.getCurrentApplicationId() ;
		List<ApplicationMenu> swap =  functionService.getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(appId);
		if ( swap != null && !swap.isEmpty()){
			List<ApplicationMenuDTO> retval = new ArrayList<ApplicationMenuDTO>();
			for ( ApplicationMenu scn : swap ){
				ApplicationMenuDTO dto = new ApplicationMenuDTO(scn); 
				retval.add(dto); 
			}
			return retval ;
		}
		return null;
	}

	@Override
	public void eraseApplicationMenu(Long applicationMenuId)
			throws Exception {
		// TODO ini berat, implement yang detail, ke sema node
		try {
			functionService.eraseApplicationMenu(applicationMenuId);
		} catch (Exception e) {
			throw translateToSerializableException(e);
		}
		
	}

	@Override
	public void updateApplicationMenu(ApplicationMenuDTO menuData)
			throws   DataNotFoundException , Exception   {
		ApplicationMenu  f =  functionService.getFunctionById(menuData.getId()); 
		if ( f ==null ){
			throw new DataNotFoundException("unable to find menu with id: " + menuData.getId() +", data probable already erased");
		}
		//f.setModifiedBy(getCurrentUser().getUsername());
		f.setFunctionLabel(menuData.getLabel());
		f.setFunctionCode(menuData.getCode());
		f.setPageId(menuData.getPageId());
		try {
			functionService.updateFunction(f);
		} catch (Exception e) {
			throw translateToSerializableException(e);
		}
		
		
	}

	@Override
	public ApplicationMenuDTO appendNewMenuNode(ApplicationMenuDTO menuData) throws Exception {
		try {
			ApplicationMenuDTO retval = functionService.insertNewApplicationMenu(menuData);
			return retval;
		} catch (Exception e) {
			throw translateToSerializableException(e);
		}
	}

	@Override
	public Class<FunctionRPCService> implementedInterface() {
		return FunctionRPCService.class;
	}

	@Override
	public PageDefinition getPageDefinition(Long page) {
		 if ( page== null){
			 LOGGER.error("page null  , request di abaikan");
			 return null ; 
		 }
		try {
			return generalPurposeDao.get(PageDefinition.class, page);
		} catch (Exception e) {
			LOGGER.error("gagal membaca data page untuk id : " + page  + " , error : " + e.getMessage() , e );
			e.printStackTrace();
			return null ; 
		}
	}
}
