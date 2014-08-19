package id.co.sigma.common.server.gwt.rpc.system;

import id.co.sigma.common.data.lov.Common2ndLevelLOVHeader;
import id.co.sigma.common.data.lov.CommonLOVHeader;
import id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument;
import id.co.sigma.common.data.lov.LOVProviderRPCService;
import id.co.sigma.common.data.lov.LOVRequestArgument;
import id.co.sigma.common.exception.CacheStillUpToDateException;
import id.co.sigma.common.exception.UnknownLookupValueProviderException;
import id.co.sigma.common.server.dao.DirectTableLookupProviderDao;
import id.co.sigma.common.server.lov.ILOVProviderService;
import id.co.sigma.common.server.lov.ISelfRegisterLovManager;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Lov Provider servlet
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @author <a href="gede.mahendra@sigma.co.id">Gede Mahendra</a>
 **/

public class LOVProviderRPCServiceImpl extends id.co.sigma.common.server.gwt.rpc.BaseServerRPCService<LOVProviderRPCService> implements LOVProviderRPCService {

	
	@Autowired
	ILOVProviderService  lovProviderService;
	

	@Autowired(required=true )
	@Qualifier(value="common-lov.directTableProvider")
	private DirectTableLookupProviderDao directTableLookupProviderDao ;
	
	
	
	@Autowired
	private ISelfRegisterLovManager selfRegisterLovManager;
	
	
	@Override
	public List<CommonLOVHeader> getModifiedLOVs(String localizationCode,
			List<LOVRequestArgument> dataRequest) {
		
		return lovProviderService.getModifiedLOVs(localizationCode, dataRequest); 
		/*
		try {
			List<LOVRequestArgument> directTable = new ArrayList<LOVRequestArgument>(); 
			List<CommonLOVHeader> retval = new ArrayList<CommonLOVHeader>();
			for ( LOVRequestArgument scn : dataRequest){
				if ( LOVSource.directFromLookupTable.equals(scn.getSource()))
					directTable.add(scn); 
				else if ( LOVSource.useCustomProvider.equals(scn.getSource())){
					BaseLOVProvider provider =selfRegisterLovManager.get(scn.getId());
					if ( provider!=null){
						String dbVersion = provider.getVersion();
						if ( dbVersion!= null) {
							if (dbVersion.equals(  scn.getCacheVersion()))
								continue ;
						}
						else if ( scn.getCacheVersion()!= null) {
							if (scn.getCacheVersion().equals( dbVersion  ))
								continue ;
						}
						
						CommonLOVHeader  headCustom = provider.getLookupValues(localizationCode, scn); //customLOVProviders.get(scn.getId()).getLookupValues(localizationCode, scn);
						if ( headCustom!=null)
							retval.add(headCustom); 
					}
					
					 
				}	
			}
			
			if ( !directTable.isEmpty()){
				Collection<CommonLOVHeader> directData = getDirectLookupTableLOVData(localizationCode , directTable);
				if(directData!=null&&!directData.isEmpty())
					retval.addAll(directData);
			}
			
			
			return retval;
		} catch (Exception e) {
			e.printStackTrace(); 
			return null ; 
		}
		*/
	}
	 
	
		
	



	@Override
	public Common2ndLevelLOVHeader getModifiedLOV(String localizationCode,
			LOV2ndLevelRequestArgument dataRequest) throws UnknownLookupValueProviderException, CacheStillUpToDateException , Exception{
		return lovProviderService.getModifiedLOV(localizationCode, dataRequest);
		
	}


	@Override
	public Common2ndLevelLOVHeader getSameParent2ndLevelLOV(
			String localizationCode,
			LOV2ndLevelRequestArgument lovRequestArgument)
			throws UnknownLookupValueProviderException,
			CacheStillUpToDateException, Exception {
		return lovProviderService.getSameParent2ndLevelLOV(localizationCode, lovRequestArgument);
	}



	@Override
	public Class<LOVProviderRPCService> implementedInterface() {
		return LOVProviderRPCService.class;
	}
	
	
	

}
