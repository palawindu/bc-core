package id.co.sigma.common.data;

/**
 * constant untuk common library
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final class CommonLibraryConstant {
	
	
	
	
	/**
	 * ini url untuk common upload
	 */
	public static final String COMMON_UPLOAD_CONTROLLER_URL ="common/upload-common-file.jsp"; 
	
	
	
	/**
	 * ini adalah url untuk upload dengan IFRAME
	 */
	public static final String COMMON_UPLOAD_CONTROLLER_URL_WITH_IFRAME = "common/upload-with-messaging-file.jsp" ;
	
	
	
	/**
	 * handler untuk menangani masalah double submit. 
	 * untuk menangani doble submit : <br/>
	 * <ol>
	 * <li>disable tombol</li>
	 * <li>tambah token, token di cek</li>
	 * </ol>
	 */
	public static final String DOUBLE_SUBMIT_AVOIDENCE_PARAMTER_KEY="bcSystemDoubleSubmitHandler";
	
	
	/**
	 * path application configuration
	 */
	public static final String COMMON_CONTROL_CONFIGURATION_URL ="/sigma-app-configuration"; 

}
