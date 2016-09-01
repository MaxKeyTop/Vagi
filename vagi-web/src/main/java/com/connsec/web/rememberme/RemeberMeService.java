package com.connsec.web.rememberme;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connsec.client.crypto.ReciprocalUtils;
import com.connsec.util.Base64Utils;
import com.connsec.web.WebConstants;

public class RemeberMeService {
	private static Logger _logger = LoggerFactory.getLogger(RemeberMeService.class);
	 /**
     * The number of seconds in one year (= 60 * 60 * 24 * 365).
     */
    public static final Integer ONE_YEAR = 60 * 60 * 24 * 365;
    
    public static final Integer ONE_DAY = 60	*	60	*	24; //1 day
    
    public static final Integer ONE_WEEK = ONE_DAY	*	7; //1 week
    
    public static final Integer TWO_WEEK = ONE_DAY	*	14; //2 week
    
    public static final Integer TWO_MONTH = ONE_DAY	*	30; //1 month
    
    public static final String SPLIT_CHAR="@";
    
    public static final String LOGIN_REMEBERME_SESSION="LOGIN_REMEBERME_SESSION";
    
    
	public RemeberMeService() {
		// TODO Auto-generated constructor stub
	}
	public boolean createRemeberMe(String username,HttpServletResponse response){
		_logger.info("do RememberMe ");
		DateTime currentDateTime=DateTime.now();
		currentDateTime=currentDateTime.plusDays(7);
		String rememberMeString=username+SPLIT_CHAR+currentDateTime.toString();
		String rememberMeEncodeString=ReciprocalUtils.encode(rememberMeString);
		String cookieValue=Base64Utils.base64UrlEncode(rememberMeEncodeString.getBytes());
		Cookie cookie= new Cookie(WebConstants.REMEBER_ME_COOKIE,cookieValue);
		
		Integer maxAge=ONE_WEEK;
		_logger.debug("Cookie Max Age :"+maxAge+" seconds.");
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
		return true;
		
	}
	
	public String getRemeberMe(HttpServletRequest request){
 		_logger.debug("Read RemeberMe from cookie");

 		String cookieValue=null;
 		Cookie[] cookies = request.getCookies(); 
		if(cookies!=null) {
			 for (int i = 0; i < cookies.length; i++) {
				 if(cookies[i].getName().equalsIgnoreCase(WebConstants.REMEBER_ME_COOKIE)){
					 cookieValue= cookies[i].getValue();
				 }
			 }
		}
		if(cookieValue!=null){
			String remeberMeB64URL=new String(Base64Utils.base64UrlDecode(cookieValue));
			
			String remeberMe=ReciprocalUtils.decoder(remeberMeB64URL);
			
			_logger.debug("decoder RemeberMe : "+remeberMe);
			cookieValue=remeberMe;
		}
    	return cookieValue;
 	}
	
	public String getUsername(String remeberMe){
		return remeberMe.substring(0, remeberMe.indexOf(SPLIT_CHAR));
	}
	
	public boolean validate(String remeberMe){
		DateTime expiryDate=new DateTime(remeberMe.substring(remeberMe.indexOf(SPLIT_CHAR)+1, remeberMe.length()));
		DateTime now = new DateTime();
		if(now.isBefore(expiryDate)){
	    	return true;
		}
		return false;
	}
}
