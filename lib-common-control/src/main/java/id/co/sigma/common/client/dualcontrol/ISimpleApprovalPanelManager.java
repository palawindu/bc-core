package id.co.sigma.common.client.dualcontrol;




/**
 * interface manager approval
 **/
public interface ISimpleApprovalPanelManager {
	
	
	/**
	 * register panel handler
	 **/
	public void register (ISimpleApprovalPanelGenerator approvalHandler) ; 

	
	
	/**
	 * mengambil approval handler by <i>fqcn</i>
	 **/
	public ISimpleApprovalPanel getHandler ( String fqcn ) ; 
	
}
