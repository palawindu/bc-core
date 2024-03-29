/**
 * File Name : User.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.app.SimpleDualControlData;
import id.co.sigma.common.util.json.ParsedJSONContainer;


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

/**
 * Entiti untuk tabel : sec_user
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 2:12:17 PM
 * @version $Id
 */
@Entity
@Table(name="sec_user")
public class User extends SimpleDualControlData<User> {

	private static final long serialVersionUID = -6026281510605996562L;
	
	/**
	* id<br/>
	* column :USER_ID
	**/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long id;
	/**
	* kode user<br/>
	* column :USER_CODE
	**/
	@Column(name="USER_CODE")
	private String userCode;
	/**
	* tanggal expire<br/>
	* column :EXPIRED_DATE
	**/
	@Column(name="EXPIRED_DATE")
	private Date  expiredDate;
	/**
	* tanggal lahir<br/>
	* column :BIRTHDATE
	**/
	@Column(name="BIRTHDATE")
	private Date  birthDate;
	
	/**
	* email<br/>
	* column :EMAIL
	**/
	@Column(name="EMAIL")
	private String email;
	
	
	/**
	* failed login attemps<br/>
	* column :FAILED_LOGIN_ATTEMPTS
	**/
	@Column(name="FAILED_LOGIN_ATTEMPTS")
	private Integer failedLoginAttempts;
	/**
	* real name<br/>
	* column :REAL_NAME
	**/
	@Column(name="REAL_NAME")
	private String realName;
	/**
	* status<br/>
	* column :STATUS
	**/
	@Column(name="STATUS", length=1)
	private String status;
	/**
	* locale<br/>
	* column :LOCALE
	**/
	@Column(name="LOCALE")
	private String locale;
	/**
	* default application id<br/>
	* column :DEFAULT_APPLICATION_ID
	**/
	@Column(name="DEFAULT_APPLICATION_ID")
	private Integer defaultApplicationId;
	/**
	* active status<br/>
	* column :ACTIVE_STATUS
	**/
	@Column(name="ACTIVE_STATUS")
	private String activeFlag;
	
	
		
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEFAULT_APPLICATION_ID", insertable=false, updatable=false)
	private Application defaultApplication;
	
	/**
	* password user<br/>
	* column :CHIPPER_TEXT
	**/
	@Column(name="CHIPPER_TEXT")
	private String chipperText;
	
	
	
	
	
	
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	@Column(name="default_branch_code", length=16)
	private String defaultBranchCode ; 
	
	public User(){}
	
	
	
	public User(String userCode, String email, String realName, String locale) {
		super();
		this.userCode = userCode;
		this.email = email;
		this.realName = realName;
		this.locale = locale;
		this.activeFlag="A"; 
		this.defaultApplicationId = 1 ; 
		this.createdOn = new Date(); 
		this.createdBy ="APP"; 
		this.creatorIPAddress="127.0.0.1"; 
		
	}



	/**
	* id<br/>
	* column :USER_ID
	**/
	public void setId(Long id){
	  this.id=id;
	}
	/**
	* id<br/>
	* column :USER_ID
	**/
	public Long getId(){
	    return this.id;
	}
	/**
	* kode user<br/>
	* column :USER_CODE
	**/
	public void setUserCode(String userCode){
	  this.userCode=userCode;
	}
	/**
	* kode user<br/>
	* column :USER_CODE
	**/
	public String getUserCode(){
	    return this.userCode;
	}
	/**
	* tanggal expire<br/>
	* column :EXPIRED_DATE
	**/
	public void setExpiredDate(Date  expiredDate){
	  this.expiredDate=expiredDate;
	}
	/**
	* tanggal expire<br/>
	* column :EXPIRED_DATE
	**/
	public Date getExpiredDate(){
	    return this.expiredDate;
	}
	/**
	* tanggal lahir<br/>
	* column :BIRTHDATE
	**/
	public void setBirthDate(Date  birthDate){
	  this.birthDate=birthDate;
	}
	/**
	* tanggal lahir<br/>
	* column :BIRTHDATE
	**/
	public Date getBirthDate(){
	    return this.birthDate;
	}
	/**
	 * flag super admin atau bukan
	 **/
	@Column(name="IS_SUPER_ADMIN")		
	private String superAdmin; 
	
	
	
	/**
	* email<br/>
	* column :EMAIL
	**/
	public void setEmail(String email){
	  this.email=email;
	}
	/**
	* email<br/>
	* column :EMAIL
	**/
	public String getEmail(){
	    return this.email;
	}
	
	
	/**
	* failed login attemps<br/>
	* column :FAILED_LOGIN_ATTEMPTS
	**/
	public void setFailedLoginAttempts(Integer failedLoginAttempts){
	  this.failedLoginAttempts=failedLoginAttempts;
	}
	/**
	* failed login attemps<br/>
	* column :FAILED_LOGIN_ATTEMPTS
	**/
	public Integer getFailedLoginAttempts(){
	    return this.failedLoginAttempts;
	}
	/**
	* real name<br/>
	* column :REAL_NAME
	**/
	public void setRealName(String realName){
	  this.realName=realName;
	}
	/**
	* real name<br/>
	* column :REAL_NAME
	**/
	public String getRealName(){
	    return this.realName;
	}
	/**
	* status<br/>
	* column :STATUS
	**/
	public void setStatus(String status){
	  this.status=status;
	}
	/**
	* status<br/>
	* column :STATUS
	**/
	public String getStatus(){
	    return this.status;
	}
	/**
	* locale<br/>
	* column :LOCALE
	**/
	public void setLocale(String locale){
	  this.locale=locale;
	}
	/**
	* locale<br/>
	* column :LOCALE
	**/
	public String getLocale(){
	    return this.locale;
	}
	/**
	* default application id<br/>
	* column :DEFAULT_APPLICATION_ID
	**/
	public void setDefaultApplicationId(Integer defaultApplicationId){
	  this.defaultApplicationId=defaultApplicationId;
	}
	/**
	* default application id<br/>
	* column :DEFAULT_APPLICATION_ID
	**/
	public Integer getDefaultApplicationId(){
	    return this.defaultApplicationId;
	}
	
	
	/**
	 * Reference object UserPassword
	 * @return password
	 */
	/*public UserPassword getPassword() {
		return password;
	}*/
	/**
	 * Reference object UserPassword
	 * @param password
	 */
	/*public void setPassword(UserPassword password) {
		this.password = password;
	}*/
	
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	public void setDefaultApplication(Application defaultApplication) {
		this.defaultApplication = defaultApplication;
	}
	
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	public Application getDefaultApplication() {
		return defaultApplication;
	}
	
	/**
	 * password<br/>
	 * column :CHIPPER_TEXT
	 */
	public String getChipperText() {
		return chipperText;
	}
	/**
	 * password<br/>
	 * column :CHIPPER_TEXT
	 */
	public void setChipperText(String chipperText) {
		this.chipperText = chipperText;
	}
	
	
	
	 
	
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	public void setDefaultBranchCode(String defaultBranchCode) {
		this.defaultBranchCode = defaultBranchCode;
	}
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	public String getDefaultBranchCode() {
		return defaultBranchCode;
	}
	@Override
	public String[] retrieveModifableFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * column : column :ACTIVE_STATUS
	 */
	@Override
	public void setActiveFlag(String activeFlag) {
		this.activeFlag  = activeFlag ; 
		
	}
	@Override
	public String getActiveFlag() {
		return activeFlag;
	}
	@Override
	public Long getPrimaryKey() {
		return id;
	}
	@Override
	public Class<Long> getPrimaryKeyClassType() {
		return Long.class;
	}
	@Override
	public String getKey1AsString() {
		return userCode;
	}
	@Override
	public String getKey2AsString() {
		return null;
	}
	@Override
	public boolean isEraseDataOnApproveErase() {
		return true;
	}
	@Override
	public String getPrimaryKeyJPAName() {
		return "id";
	}
	
	/**
	 * flag super admin atau bukan
	 **/
	public String getSuperAdmin() {
		return superAdmin;		
	}
	
	/**
	 * flag super admin atau bukan
	 **/
	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	
	@Override
	protected void extractDataFromJSON(User targetObject,
			ParsedJSONContainer jsonContainer) {
		targetObject.setActiveFlag(  (String)jsonContainer.get("activeFlag" ,  String.class.getName()));
		targetObject.setBirthDate( (Date)jsonContainer.get("birthDate" ,  Date.class.getName()));
		targetObject.setChipperText( (String)jsonContainer.get("chipperText" ,  String.class.getName()));
		targetObject.setDefaultApplication( (Application)jsonContainer.get("defaultApplication" ,  Application.class.getName()));
		targetObject.setDefaultApplicationId( (Integer)jsonContainer.get("defaultApplicationId" ,  Integer.class.getName()));
		targetObject.setEmail( (String)jsonContainer.get("email" ,  String.class.getName()));
		targetObject.setExpiredDate( (Date)jsonContainer.get("expiredDate" ,  Date.class.getName()));
		targetObject.setFailedLoginAttempts( (Integer)jsonContainer.get("failedLoginAttempts" ,  Integer.class.getName()));
		targetObject.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		targetObject.setLocale( (String)jsonContainer.get("locale" ,  String.class.getName()));
		targetObject.setRealName( (String)jsonContainer.get("realName" ,  String.class.getName()));
		targetObject.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		targetObject.setUserCode( (String)jsonContainer.get("userCode" ,  String.class.getName()));
		targetObject.setDefaultBranchCode(jsonContainer.getAsString("defaultBranchCode")); 
		
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		super.translateToJSON(jsonContainerData);
		jsonContainerData.put("id", id);
		jsonContainerData.put("userCode", userCode);
		jsonContainerData.put("expiredDate", expiredDate);
		jsonContainerData.put("birthDate", birthDate);
		jsonContainerData.put("email", email);
		jsonContainerData.put("failedLoginAttempts", failedLoginAttempts);
		jsonContainerData.put("realName", realName);
		jsonContainerData.put("status", status);
		jsonContainerData.put("locale", locale);
		jsonContainerData.put("defaultApplicationId", defaultApplicationId);
		jsonContainerData.put("defaultApplication", defaultApplication);
		jsonContainerData.put("chipperText", chipperText);
		jsonContainerData.put("defaultBranchCode", defaultBranchCode);
	}
}