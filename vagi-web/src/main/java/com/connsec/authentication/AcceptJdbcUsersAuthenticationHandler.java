/*
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.connsec.authentication;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.RememberMeUsernamePasswordCredential;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.web.support.WebUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.connsec.client.crypto.ReciprocalUtils;
import com.connsec.util.Base64Utils;
import com.connsec.web.WebConstants;
import com.connsec.web.WebContext;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.security.GeneralSecurityException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handler that contains a list of valid users and passwords. Useful if there is
 * a small list of users that we wish to allow. An example use case may be if
 * there are existing handlers that make calls to LDAP, etc. but there is a need
 * for additional users we don't want in LDAP. With the chain of command
 * processing of handlers, this handler could be added to check before LDAP and
 * provide the list of additional users. The list of acceptable users is stored
 * in a map. The key of the map is the username and the password is the object
 * retrieved from doing map.get(KEY).
 * <p>
 * Note that this class makes an unmodifiable copy of whatever map is provided
 * to it.
 *
 * @author Scott Battaglia
 * @author Marvin S. Addison
 *
 * @since 3.0.0
 */
public class AcceptJdbcUsersAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
	private static Logger _logger = LoggerFactory.getLogger(AbstractUsernamePasswordAuthenticationHandler.class);
	
	
	
	private static final String LOCK_USER_UPDATE_STATEMENT = "UPDATE USERINFO SET ISLOCKED = ?  , UNLOCKTIME = ? WHERE ID = ?";
	
	private static final String UNLOCK_USER_UPDATE_STATEMENT = "UPDATE USERINFO SET ISLOCKED = ? , UNLOCKTIME = ? WHERE ID = ?";
	
	private static final String BADPASSWORDCOUNT_UPDATE_STATEMENT = "UPDATE USERINFO SET BADPASSWORDCOUNT = ? , BADPASSWORDTIME = ?  WHERE ID = ?";
	
	private static final String BADPASSWORDCOUNT_RESET_UPDATE_STATEMENT = "UPDATE USERINFO SET BADPASSWORDCOUNT = ? , ISLOCKED = ? ,UNLOCKTIME = ?  WHERE ID = ?";
	
	private static final String HISTORY_LOGIN_INSERT_STATEMENT = "INSERT INTO LOGIN_HISTORY (ID , SESSIONID , UID , USERNAME , DISPLAYNAME , LOGINTYPE , MESSAGE , CODE , PROVIDER , SOURCEIP , BROWSER , PLATFORM , APPLICATION , LOGINURL )VALUES( ? , ? , ? , ? , ?, ? , ? , ?, ? , ? , ?, ? , ? , ?)";
	
	private static final String LOGIN_USERINFO_UPDATE_STATEMENT  = "UPDATE USERINFO SET LASTLOGINTIME = ?  , LASTLOGINIP = ? , LOGINCOUNT = ?  WHERE ID = ?";
	
	private static final String LOGOUT_USERINFO_UPDATE_STATEMENT = "UPDATE USERINFO SET LASTLOGOFFTIME = ?   WHERE ID = ?";
	
	private static final String HISTORY_LOGOUT_UPDATE_STATEMENT = "UPDATE LOGIN_HISTORY SET LOGOUTTIME = ?  WHERE  SESSIONID = ?";
	
	public final static String FORMAT_DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	

    /** The list of users we will accept. */
    @NotNull
    protected UserInfoService userInfoService;
    
    protected  JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     **/
    @Override
    protected  HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {

        final String username = credential.getUsername();
        final UserInfo u = this.userInfoService.loadUserInfo(username);

        if (u == null) {
           logger.debug("{} was not found in the map.", username);
           throw new AccountNotFoundException(username + " not found in backing map.");
        }

        final String encodedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        if (!u.getPassword().equals(encodedPassword)) {
            throw new FailedLoginException();
        }
        WebContext.setUserInfo(u);
        
        insertLoginHistory(u,"WebLogin","Web","100000","Success");
        return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
    }


/**
  * login log write to log db
  * @param uid
  * @param j_username
  * @param type
  * @param code
  * @param message
  */
	public boolean insertLoginHistory(UserInfo userInfo,String type,String provider,String code,String message){
		Date loginDate=new Date();
		String sessionId=WebContext.genId();
		WebContext.setAttribute(WebConstants.CURRENT_USER_SESSION_ID, sessionId);
		String ipAddress=WebContext.getRequestIpAddress();
		String platform="";
		String browser="";
		String userAgent = WebContext.getRequest().getHeader("User-Agent");  
 	String []arrayUserAgent=null;
 	if(userAgent.indexOf("MSIE")>0){
 		arrayUserAgent=userAgent.split(";");
 		browser=arrayUserAgent[1].trim();
 		platform=arrayUserAgent[2].trim();
 	}else if(userAgent.indexOf("Trident")>0){
 		arrayUserAgent=userAgent.split(";");
 		browser="MSIE/"+arrayUserAgent[3].split("\\)")[0];;
 		platform=arrayUserAgent[0].split("\\(")[1];
 	}else if(userAgent.indexOf("Chrome")>0){
 		arrayUserAgent=userAgent.split(" ");
 		//browser=arrayUserAgent[8].trim();
 		for(int i=0;i<arrayUserAgent.length;i++){
 			if(arrayUserAgent[i].contains("Chrome")){
 				browser=arrayUserAgent[i].trim();
 				browser=browser.substring(0, browser.indexOf('.'));
 			}
 		}
 		platform=(arrayUserAgent[1].substring(1)+" "+arrayUserAgent[2]+" "+arrayUserAgent[3].substring(0, arrayUserAgent[3].length()-1)).trim();
 	}else if(userAgent.indexOf("Firefox")>0){
 		arrayUserAgent=userAgent.split(" ");
 		for(int i=0;i<arrayUserAgent.length;i++){
 			if(arrayUserAgent[i].contains("Firefox")){
 				browser=arrayUserAgent[i].trim();
 				browser=browser.substring(0, browser.indexOf('.'));
 			}
 		}
 		platform=(arrayUserAgent[1].substring(1)+" "+arrayUserAgent[2]+" "+arrayUserAgent[3].substring(0, arrayUserAgent[3].length()-1)).trim();
 		
 	}
 	
		jdbcTemplate.update(HISTORY_LOGIN_INSERT_STATEMENT, 
				new Object[] { 
					WebContext.genId(),
					sessionId,
					userInfo.getId(),
					userInfo.getUsername(),
					userInfo.getDisplayName(),
					type,
					message,
					code,
					provider,
					ipAddress,
					browser,
					platform,
					"Browser",
					loginDate},
				new int[] {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.TIMESTAMP });
		
		userInfo.setLastLoginTime(new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD_HH_MM_SS).format(loginDate));
		
		jdbcTemplate.update(LOGIN_USERINFO_UPDATE_STATEMENT, 
				new Object[] { 
					loginDate,
					ipAddress,
					userInfo.getLoginCount()+1,
					userInfo.getId()},
				new int[] {Types.TIMESTAMP, Types.VARCHAR,Types.INTEGER,Types.VARCHAR});
		
		return true;
	}
	
	public boolean logout(HttpServletResponse response){
		Object sessionIdAttribute=WebContext.getAttribute(WebConstants.CURRENT_USER_SESSION_ID);
		if(sessionIdAttribute!=null){
		UserInfo userInfo=WebContext.getUserInfo();
			Date logoutDateTime=new Date();
			if(sessionIdAttribute!=null){
				
				jdbcTemplate.update(HISTORY_LOGOUT_UPDATE_STATEMENT, 
						new Object[] { 
							logoutDateTime,
							sessionIdAttribute.toString()},
						new int[] {Types.TIMESTAMP ,Types.VARCHAR});
			}
			
			jdbcTemplate.update(LOGOUT_USERINFO_UPDATE_STATEMENT, 
					new Object[] { 
						logoutDateTime,
						userInfo.getId()},
					new int[] {Types.TIMESTAMP ,Types.VARCHAR});
			
			_logger.debug("Session " +WebContext.getAttribute(WebConstants.CURRENT_USER_SESSION_ID)+ ", user "+userInfo.getUsername()+" Logout, datetime "+logoutDateTime+" .");
		}
		return true;
		
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
