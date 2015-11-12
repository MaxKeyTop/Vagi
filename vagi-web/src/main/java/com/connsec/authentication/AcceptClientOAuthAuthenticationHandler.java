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
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connsec.web.WebContext;
import com.connsec.web.socialsignon.SocialSignOnEndpoint;

import java.security.GeneralSecurityException;

import javax.security.auth.login.FailedLoginException;

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
public class AcceptClientOAuthAuthenticationHandler extends AcceptJdbcUsersAuthenticationHandler {
	private static Logger _logger = LoggerFactory.getLogger(AcceptClientOAuthAuthenticationHandler.class);


    public AcceptClientOAuthAuthenticationHandler() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
     * {@inheritDoc}
     **/
    @Override
    protected  HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
    	
    	String socialsignon_type_session=null;
        if(WebContext.getAttribute(SocialSignOnEndpoint.SOCIALSIGNON_TYPE_SESSION)!=null){
        	socialsignon_type_session=WebContext.getAttribute(SocialSignOnEndpoint.SOCIALSIGNON_TYPE_SESSION).toString();
        }
      
        if(socialsignon_type_session!=null&&socialsignon_type_session.equals(SocialSignOnEndpoint.SOCIALSIGNON_TYPE.SOCIALSIGNON_TYPE_LOGON)){
        	final String username = credential.getUsername();
        	final UserInfo u = this.userInfoService.loadUserInfo(username);
        	WebContext.setUserInfo(u);
            insertLoginHistory(WebContext.getUserInfo(),"SocialSignOn","Web","100000","Success");
        	return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
        }
       
        throw new FailedLoginException();
        
        
    }

}
