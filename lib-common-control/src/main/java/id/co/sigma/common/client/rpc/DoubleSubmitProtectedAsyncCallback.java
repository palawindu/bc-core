package id.co.sigma.common.client.rpc;

/**
 * calback dengan proteksi double submit. callback ini musti menyediakan reference ke token callback. variable ini sebaiknya tidak di keep dalam class ini, namun di keep dalam variable lain nya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @param <DATA> data yang di return oleh server
 */
public abstract class DoubleSubmitProtectedAsyncCallback<DATA> extends CommonControlAsyncCallback<DATA>{

	
	
	
	/**
	 * mengambil token untuk avoid double submit
	 */
	public abstract String retrieveDoubleSubmitAvoidenceToken () ; 
}
