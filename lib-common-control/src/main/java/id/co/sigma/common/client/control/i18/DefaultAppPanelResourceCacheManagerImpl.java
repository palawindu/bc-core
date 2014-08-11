package id.co.sigma.common.client.control.i18;

import id.co.sigma.common.client.cache.BaseActualClientCacheWorker;
import id.co.sigma.common.client.cache.LocalStorageActualClientCacheWorker;





/**
 * default local storage
 **/
public class DefaultAppPanelResourceCacheManagerImpl extends BaseAppPanelResourceCacheManagerImpl{
	
	private BaseActualClientCacheWorker  baseActualClientCacheWorker = new LocalStorageActualClientCacheWorker(); 
	
	
	@Override
	public BaseActualClientCacheWorker getActualClientCacheWorker() {
		
		return baseActualClientCacheWorker;
	}

}
