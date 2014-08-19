package id.co.sigma.common.client.rpc;

import java.math.BigInteger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import id.co.sigma.common.client.rpc.impl.GeneralPurposeRPCAsyncImpl;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface GeneralPurposeRPCAsync  {

	

	public static class Util {
		
		private static GeneralPurposeRPCAsync instance ; 
		
		public static GeneralPurposeRPCAsync getInstance() {
			if ( instance == null){
				instance = GWT.create(GeneralPurposeRPCAsyncImpl.class);

			}
			return instance;
		}
		
		
	}

	
	
	/**
	 * select data dengan ke page. data di akases dengan FQCN dari data. 
	 * <ol>
	 * <li>data yang di request merupakan data yang di JPA mapped, bukan DTO</li>
	 * <li>data di filter dengan filter sederhana, client perlu menyediakan filter + sorts</li>
	 * </ol>
	 * @param page page yang di ambil. berbasis 0 
	 *  
	 * 
	 **/
	public <DATA extends IJSONFriendlyObject<DATA>> void getPagedData (String objectFQCN , SimpleQueryFilter[] filters , SimpleSortArgument[] sorts , int page , int pageSize , AsyncCallback<PagedResultHolder<DATA>> callback);
	
	
	/**
	 * mencari data dengan big integer ID
	 **/
	void getObjectByBigInteger ( String objectFQCN  ,  BigInteger objectId  , AsyncCallback<IJSONFriendlyObject<?>> callback)  ;
	
	
	/**
	 * select data dengan id. ID berupa long.ini hanya bekerja untuk JPA object
	 */
	void getObjectById ( String objectFQCN  ,  Long objectId , AsyncCallback<IJSONFriendlyObject<?>> callback);  
	
	/**
	 * select data dengan id. ID berupa Integer. ini hanya bekerja untuk JPA object
	 * @param objectFQCN FQCN dari object
	 */
	void getObjectById ( String objectFQCN  ,  Integer objectId , AsyncCallback<IJSONFriendlyObject<?>> callback); 
	
	
	
	/**
	 * membaca data dengan key string
	 */
	void getObjectById ( String objectFQCN  ,  String objectId , AsyncCallback<IJSONFriendlyObject<?>> callback) ; 
	
	/**
	 * generate double submit avoidence token. Token ini di akan di rematch pada saat request sampai ke server. methode nya kurang lebih begini : 
	 * <ol>
	 * <li>pada awal, panel aktiv musti request token dan cache dalam variable</li>
	 * <li>panel musti menyimpan token</li>
	 * <li>pada saat RPC di kirim, token ini musti di kirimkan juga ke server</li>
	 * </ol>
	 * 
	 * @return token
	 */
	public void generateDoubleSubmitAvoidenceToken (AsyncCallback<String> callback) ; 

	/**
	 * insert object baru ke dalam database
	 * @param newObject 
	 */
	public void insertData (IJSONFriendlyObject<?> newObject , AsyncCallback<Void> callback) ;
	
}
