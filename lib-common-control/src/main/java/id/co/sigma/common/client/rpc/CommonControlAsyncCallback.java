package id.co.sigma.common.client.rpc;

import id.co.sigma.common.exception.LoginExpiredException;
import id.co.sigma.common.rpc.ILoginExpiredDetector;
import id.co.sigma.common.rpc.ManagedAsyncCallback;




/**
 * Base class untuk handler async callback. penempatan packaging ini agak salah taruh. jadinya di bikinkan base class {@link ManagedAsyncCallback} 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * 
 **/
public abstract class CommonControlAsyncCallback<T> extends ManagedAsyncCallback<T>{

	static {
		ManagedAsyncCallback.LOGIN_EXPIRED_DETECTOR = new ILoginExpiredDetector() {
			
			@Override
			public boolean isLoginExpired(Throwable exception) {
				return exception instanceof LoginExpiredException;
			}
		};
		
		
	}
	


	
}
