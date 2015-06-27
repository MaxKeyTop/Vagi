/**
 * 
 */
package com.connsec.authentication;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class UserInfo implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -982711660913369581L;
	
	protected String 	id;
	protected String 	username;
	protected String 	password;
	protected String 	displayName;
	protected String 	passwordLastSetTime;
	protected int 		badPasswordCount;
	protected String 	badPasswordTime;
	protected String 	unLockTime;
	protected int 		isLocked;
	protected String 	lastLoginTime;
	protected String 	lastLoginIp;
	protected String 	lastLogoffTime;
	protected Integer 	loginCount;
	protected int 		status;

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserInfo(String username) {
		super();
		this.username=username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPasswordLastSetTime() {
		return passwordLastSetTime;
	}

	public void setPasswordLastSetTime(String passwordLastSetTime) {
		this.passwordLastSetTime = passwordLastSetTime;
	}

	public int getBadPasswordCount() {
		return badPasswordCount;
	}

	public void setBadPasswordCount(int badPasswordCount) {
		this.badPasswordCount = badPasswordCount;
	}

	public String getBadPasswordTime() {
		return badPasswordTime;
	}

	public void setBadPasswordTime(String badPasswordTime) {
		this.badPasswordTime = badPasswordTime;
	}

	public String getUnLockTime() {
		return unLockTime;
	}

	public void setUnLockTime(String unLockTime) {
		this.unLockTime = unLockTime;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLogoffTime() {
		return lastLogoffTime;
	}

	public void setLastLogoffTime(String lastLogoffTime) {
		this.lastLogoffTime = lastLogoffTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password="
				+ password + ", displayName=" + displayName
				+ ", passwordLastSetTime=" + passwordLastSetTime
				+ ", badPasswordCount=" + badPasswordCount
				+ ", badPasswordTime=" + badPasswordTime + ", unLockTime="
				+ unLockTime + ", isLocked=" + isLocked + ", lastLoginTime="
				+ lastLoginTime + ", lastLoginIp=" + lastLoginIp
				+ ", lastLogoffTime=" + lastLogoffTime + ", loginCount="
				+ loginCount + "]";
	}

}
