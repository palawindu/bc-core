package id.co.sigma.common.security.domain;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Entiti untuk tabel : sec_signon
 * @author I Gede Mahendra
 * @since Nov 12, 2012, 1:10:32 PM
 * @version $Id
 */
@Entity
@Table(name="sec_signon_hs")
public class SignonHistory implements Serializable,IsSerializable, IJSONFriendlyObject<SignonHistory>{

	private static final long serialVersionUID = 8765875105373866197L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SIGNON_ID")
	private Long id;
	
	@Column(name="USER_ID")
	private Long userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", insertable=false, updatable=false)
	private User user;
	
	@Column(name="APPLICATION_ID")
	private Long applicationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPLICATION_ID", insertable=false, updatable=false)
	private Application application;
	
	@Column(name="TERMINAL")
	private String terminal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOGON_TIME")
	private Date logonTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOGOFF_TIME")
	private Date logoutTime;
	
	@Column(name="UUID")
	private String uuid;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	
	/**
	 * browser dari user
	 **/
	@Column(name="USER_BROWSER")
	private String userBrowser ; 

	/**
	 * browser dari user
	 **/
	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}
	
	/**
	 * browser dari user
	 **/
	public String getUserBrowser() {
		return userBrowser;
	}
	

	/**
	 * Signon Id. Column : SIGNON_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Signon Id. Column : SIGNON_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * User id. Column : USER_ID
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * User id. Column : USER_ID
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * reference ke Object user 
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * reference ke Object user 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * Application id. Column : APPLICATION_ID
	 * @return applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}
	/**
	 * Application id. Column : APPLICATION_ID
	 * @param applicationId
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * reference ke Object Application 
	 * @return application
	 */
	public Application getApplication() {
		return application;
	}
	/**
	 * reference ke Object Application
	 * @param application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	/**
	 * Terminal. Column : TERMINAL
	 * @return terminal
	 */
	public String getTerminal() {
		return terminal;
	}
	/**
	 * Terminal. Column : TERMINAL
	 * @param terminal
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	/**
	 * Login time. Column : LOGON_TIME
	 * @return logonTime
	 */
	public Date getLogonTime() {
		return logonTime;
	}
	/**
	 * Login time. Column : LOGON_TIME
	 * @param logonTime
	 */
	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}
	/**
	 * Logout time. Column : LOGOUT_TIME
	 * @return
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}
	/**
	 * Logout time. Column : LOGOUT_TIME
	 * @param logoutTime
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	/**
	 * UUID. Column : uuid
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * UUID. Column : uuid
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * Deskripsi. Column : description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Deskripsi. Column : description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		  
		 Application param1 = getApplication();   
		 if ( param1 != null){ 
		
 //1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json
			jsonContainer.put("application", param1);
//4. restore lagi 
		}
		jsonContainer.put("application",getApplication());
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("description",getDescription());
		jsonContainer.put("id",getId());
		jsonContainer.put("logonTime",getLogonTime());
		jsonContainer.put("logoutTime",getLogoutTime());
		jsonContainer.put("terminal",getTerminal());
		  
		 User param9 = getUser();   
		 if ( param9 != null){ 
		
 //1. Ok tampung dulu variable
			Application param9_defaultApplication_tmp = param9.getDefaultApplication();
//2. null kan variable 
			param9.setDefaultApplication(null);
// 3 taruh ke json
			jsonContainer.put("user", param9);
//4. restore lagi 
			param9.setDefaultApplication(param9_defaultApplication_tmp);
		}
		jsonContainer.put("user",getUser());
		jsonContainer.put("userBrowser",getUserBrowser());
		jsonContainer.put("userId",getUserId());
		jsonContainer.put("uuid",getUuid());
	}
	
	@Override
	public SignonHistory instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		SignonHistory retval = new SignonHistory();
		  
		retval.setApplication( (Application)jsonContainer.get("application" ,  Application.class.getName()));
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		retval.setDescription( (String)jsonContainer.get("description" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setLogonTime( (Date)jsonContainer.get("logonTime" ,  Date.class.getName()));
		retval.setLogoutTime( (Date)jsonContainer.get("logoutTime" ,  Date.class.getName()));
		retval.setTerminal( (String)jsonContainer.get("terminal" ,  String.class.getName()));
		  
		retval.setUser( (User)jsonContainer.get("user" ,  User.class.getName()));
		retval.setUserBrowser( (String)jsonContainer.get("userBrowser" ,  String.class.getName()));
		retval.setUserId( (Long)jsonContainer.get("userId" ,  Long.class.getName()));
		retval.setUuid( (String)jsonContainer.get("uuid" ,  String.class.getName()));
		return retval; 
	}
}