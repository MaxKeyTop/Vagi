package com.connsec.web;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.connsec.authentication.UserInfo;

/**
 * Application is common class for Web Application Context
 * 
 * @author Crystal.Sea
 * @since 1.5
 */
public final class WebContext {
	  
	/**
	 * set Current login user  to session
	 * @see WebConstants.CURRENT_USER
	 */
	public static void setUserInfo(UserInfo userInfo) {
		 setAttribute(WebConstants.CURRENT_USER,userInfo);
	}
	
	/**
	 * get Current login user from session
	 * @see WebConstants.CURRENT_USER
	 * @return UserInfo
	 */ 
	public static UserInfo getUserInfo() {
		return ((UserInfo)getAttribute(WebConstants.CURRENT_USER));
	}
	
	/**
	 * get ApplicationContext from web  ServletContext configuration
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext(){
		return WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
	}
	
	/**
	 * get bean from spring configuration by bean id
	 * @param id
	 * @return Object
	 */
	public static Object getBean(String id){
		return getApplicationContext().getBean(id);
	}
	
	
	//below method is common HttpServlet method
	/**
	 * get Spring HttpServletRequest
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * get Http Context full Path,if port equals 80 is omitted
	 * @return String
	 * eg:http://192.168.1.20:9080/webcontext or http://www.website.com/webcontext
	 */
	public static String getHttpContextPath(){
		HttpServletRequest httpServletRequest = WebContext.getRequest();
		String httpContextPath=httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName();
		int port =httpServletRequest.getServerPort();
		if(port==443 && httpServletRequest.getScheme().equalsIgnoreCase("https")){
			
		}else if(port==80 && httpServletRequest.getScheme().equalsIgnoreCase("http")){
			
		}else{
			httpContextPath	+=	":"+port;
		}
		httpContextPath	+=	httpServletRequest.getContextPath()+"";
		return httpContextPath;
	}
	
	/**
	 * get current Session
	 * @return HttpSession
	 */
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	 * get current Session,if no session ,new Session created
	 * @return HttpSession
	 */
	public static HttpSession getSession(boolean create) {
		return getRequest().getSession(create);
	}
	
	/**
	 * set Attribute to session ,Attribute name is name,value is value
	 * @param name
	 * @param value
	 */
	public static void setAttribute(String name,Object value){
		 getSession().setAttribute(name, value);
	}
	
	/**
	 * get Attribute from session by name
	 * @param name
	 * @return
	 */
	public static Object getAttribute(String name){
		return getSession().getAttribute(name);
	}
	
	/**
	 * remove Attribute from session by name
	 * @param name
	 */
	public static void removeAttribute(String name){
		 getSession().removeAttribute(name);
	}
	

	/**
	 * get Request Parameter by name
	 * @param name
	 * @return String
	 */
	public static String getParameter(String name){
		return getRequest().getParameter(name);
	}
	

	

	/**
	 * get locale from Spring Resolver,if locale is null,get locale from Spring SessionLocaleResolver
	 * this is  from internationalization 
	 * @return Locale
	 */
	public static Locale getLocale(){
		Locale locale=null;
		try{
			CookieLocaleResolver cookieLocaleResolver=(CookieLocaleResolver) getBean("localeResolver");
			locale= cookieLocaleResolver.resolveLocale(getRequest());
			
		}catch(Exception e){
			LogFactory.getLog(WebContext.class).debug("getLocale() error . ");
			e.printStackTrace();
			locale= RequestContextUtils.getLocale(getRequest());
		}
		
		return locale;
	}

	/**
	 * get Request IpAddress,for current Request
	 * @return String,100.167.216.100
	 */
	public static final String getRequestIpAddress(){
		return getRequestIpAddress(getRequest());
	}
	
	/**
	 * get Request IpAddress by request
	 * @param request
	 * @return String
	 */
	public static final String getRequestIpAddress(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");   
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
			ipAddress = request.getHeader("Proxy-Client-IP");   
		}   
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
			ipAddress = request.getHeader("WL-Proxy-Client-IP");   
		}   
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
			ipAddress = request.getRemoteAddr();   
		}  
		LogFactory.getLog(WebContext.class).debug("getRequestIpAddress() RequestIpAddress:"+ipAddress);
		return ipAddress;
	}
	
	public static ModelAndView redirect(String redirectUrl){
		return new ModelAndView("redirect:"+redirectUrl);
	}
	
	public static ModelAndView forward(String forwardUrl){
		return new ModelAndView("forward:"+forwardUrl);
	}
	
	public static String genId(){
		return UUID.randomUUID().toString().toLowerCase();
	}
}