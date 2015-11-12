package com.connsec.web.socialsignon;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.connsec.client.oauth.builder.ServiceBuilder;
import com.connsec.client.oauth.builder.api.Api;
import com.connsec.client.oauth.builder.api.OAuthApi20;
import com.connsec.client.oauth.model.SignatureType;
import com.connsec.client.oauth.oauth.OAuthService;
import com.connsec.web.socialsignon.service.SocialSignOnProvider;

public class OAuthServiceBuilder {
	private static Logger _logger = LoggerFactory.getLogger(OAuthServiceBuilder.class);
	
	private SocialSignOnProvider socialSignOnProvider;
	
	private Api api;
	
	
	/**
	 * 
	 */
	public OAuthServiceBuilder() {

	}


	/**
	 * @param socialSignOnProvider
	 */
	public OAuthServiceBuilder(SocialSignOnProvider socialSignOnProvider) {
		
		this.socialSignOnProvider = socialSignOnProvider;
		String callbackUrl=getHttpContextPath()+ "/logon/oauth20/callback/"+socialSignOnProvider.getProvider();
		
		socialSignOnProvider.setCallBack(callbackUrl);
		
		api  = new OAuthApi20(socialSignOnProvider.getAuthorizeUrl(),
				socialSignOnProvider.getAccessTokenUrl(),
				socialSignOnProvider.getAccessTokenMethod());
		
		_logger.debug("api : "+api);
	}


	public OAuthService builderOAuthService() {
		
		if(socialSignOnProvider.getScope()==null||socialSignOnProvider.getScope().equals("")){
			return new ServiceBuilder().provider(api)
								.apiKey(socialSignOnProvider.getClientId())
							    .apiSecret(socialSignOnProvider.getClientSecret())
							    .callback(socialSignOnProvider.getCallBack())
							    .signatureType(SignatureType.QueryString)
							    .debug()
							    .build();
		}else{
			return new ServiceBuilder().provider(api)
								.apiKey(socialSignOnProvider.getClientId())
							    .apiSecret(socialSignOnProvider.getClientSecret())
							    .scope(socialSignOnProvider.getScope())
							    .callback(socialSignOnProvider.getCallBack())
							    .signatureType(SignatureType.QueryString)
							    .debug()
							    .build();
		}
	}

	

	public SocialSignOnProvider getSocialSignOnProvider() {
		return socialSignOnProvider;
	}


	public void setSocialSignOnProvider(SocialSignOnProvider socialSignOnProvider) {
		this.socialSignOnProvider = socialSignOnProvider;
	}


	public Api getApi() {
		return api;
	}


	public void setApi(Api api) {
		this.api = api;
	}
	
	/**
	 * get Http Context full Path,if port equals 80 is omitted
	 * @return String
	 * eg:http://192.168.1.20:9080/webcontext or http://www.website.com/webcontext
	 */
	public static String getHttpContextPath(){
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
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
}
