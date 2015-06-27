package com.connsec.authentication;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connsec.web.WebContext;
import com.connsec.web.rememberme.RemeberMeService;
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
public class AcceptRemeberMeAuthenticationHandler extends AcceptJdbcUsersAuthenticationHandler {
	private static Logger _logger = LoggerFactory.getLogger(AcceptRemeberMeAuthenticationHandler.class);


    public AcceptRemeberMeAuthenticationHandler() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
     * {@inheritDoc}
     **/
    @Override
    protected  HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        if(WebContext.getAttribute(RemeberMeService.LOGIN_REMEBERME_SESSION)!=null){
        	final String username = credential.getUsername();
        	final UserInfo u = this.userInfoService.loadUserInfo(username);
        	WebContext.setUserInfo(u);
            insertLoginHistory(WebContext.getUserInfo(),"RemeberMe","Web","100000","Success");
        	return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
        }
       
        throw new FailedLoginException();
        
        
    }

}
