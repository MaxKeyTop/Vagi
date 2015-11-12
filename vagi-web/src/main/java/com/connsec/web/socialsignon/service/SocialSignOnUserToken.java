package com.connsec.web.socialsignon.service;

import java.io.Serializable;
import java.util.UUID;


/**
 * 
 * @author Crystal.Sea
 */
public class SocialSignOnUserToken implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2151179554190800162L;
	
	private String id;
	private String provider;
	private String uid;
	private String username;
	private String socialuid;
	private String socialUserInfo;
	private String accessToken;
	private String exAttribute; 
	
	public SocialSignOnUserToken() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSocialuid() {
		return socialuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSocialuid(String socialuid) {
		this.socialuid = socialuid;
	}

	public String getSocialUserInfo() {
		return socialUserInfo;
	}

	public void setSocialUserInfo(String socialUserInfo) {
		this.socialUserInfo = socialUserInfo;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExAttribute() {
		return exAttribute;
	}

	public void setExAttribute(String exAttribute) {
		this.exAttribute = exAttribute;
	}

	public void genId(){
		this.id=UUID.randomUUID().toString().toLowerCase();
	}
	
	@Override
	public String toString() {
		return "SocialSignOnUserToken [provider=" + provider + ", uid=" + uid
				+ ", socialuid=" + socialuid + ", socialUserInfo="
				+ socialUserInfo + ", accessToken=" + accessToken
				+ ", exAttribute=" + exAttribute + "]";
	}
}
