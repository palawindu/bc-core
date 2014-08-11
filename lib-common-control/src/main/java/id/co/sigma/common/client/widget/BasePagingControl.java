package id.co.sigma.common.client.widget;





/**
 * base class paging control
 **/
public interface  BasePagingControl {


	

	/**
	 * sesuakan paging sesuai dengan data yang di terima dari server
	 **/
	public  void adjustPaging(int totalPage , int pageSize  , int currentPagePosition ); 
	
	
}
