/**
 * 
 */
package com.connsec.web.socialsignon;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.connsec.client.oauth.model.OAuthRequest;
import com.connsec.client.oauth.model.Response;
import com.connsec.client.oauth.model.Token;
import com.connsec.client.oauth.model.Verb;
import com.connsec.client.oauth.model.Verifier;
import com.connsec.client.oauth.oauth.OAuthService;
import com.connsec.util.JsonUtils;
import com.connsec.util.StringUtils;
import com.connsec.web.WebContext;
import com.connsec.web.socialsignon.service.SocialSignOnProvider;
import com.connsec.web.socialsignon.service.SocialSignOnProviderService;
import com.connsec.web.socialsignon.service.SocialSignOnUserTokenService;

/**
 * @author Crystal.Sea
 *
 */
public class AbstractSocialSignOnEndpoint {
	final static Logger _logger = LoggerFactory.getLogger(AbstractSocialSignOnEndpoint.class);

	protected final static String SOCIALSIGNON_SESSION_REDIRECT_URI="socialsignon_session_redirect_uri";
	
	protected final static String SOCIALSIGNON_REDIRECT_URI="redirect_uri";
	
	public  final static String SOCIALSIGNON_TYPE_SESSION="socialsignon_type_session";
	
	public  final static String SOCIALSIGNON_OAUTH_SERVICE_SESSION="socialsignon_oauth_service_session";
	
	public  final static String SOCIALSIGNON_PROVIDER_SESSION="socialsignon_provider_session";
	
	
	public final static class SOCIALSIGNON_TYPE{
		public  final static String SOCIALSIGNON_TYPE_LOGON="socialsignon_type_logon";
		public  final static String SOCIALSIGNON_TYPE_BIND="socialsignon_type_bind";
	}
	
	protected Token accessToken;
	
	protected SocialSignOnProvider socialSignOnProvider;
	
	protected OAuthService oauthService;
	
	protected String accountJsonString;
	
	protected String accountId;
	
	protected String provider;
	
	@Autowired
	protected SocialSignOnProviderService socialSignOnProviderService;
	
	@Autowired
	protected SocialSignOnUserTokenService socialSignOnUserTokenService;
	
	
 	
  	protected OAuthService buildOAuthService(String provider){
  		
		SocialSignOnProvider socialSignOnProvider = socialSignOnProviderService.get(provider);
		_logger.debug("socialSignOn Provider : "+socialSignOnProvider);
		
		if(socialSignOnProvider!=null){
			OAuthServiceBuilder oAuthServiceBuilder=new OAuthServiceBuilder(socialSignOnProvider);
			oauthService=oAuthServiceBuilder.builderOAuthService();
			WebContext.setAttribute(SOCIALSIGNON_OAUTH_SERVICE_SESSION, socialSignOnProvider);
			WebContext.setAttribute(SOCIALSIGNON_PROVIDER_SESSION, oauthService);
			return oauthService;
		}
		return null;
	}
  	
  	/**
  	 * get accessToken
  	 * @param service
  	 * @return
  	 */
  	protected Token getAccessToken() {
  		
  		socialSignOnProvider=(SocialSignOnProvider)WebContext.getAttribute(SOCIALSIGNON_OAUTH_SERVICE_SESSION);
  		oauthService=(OAuthService)WebContext.getAttribute(SOCIALSIGNON_PROVIDER_SESSION);
  		String oauthVerifier = WebContext.getRequest().getParameter(socialSignOnProvider.getVerifierCode());
  		WebContext.removeAttribute(SOCIALSIGNON_OAUTH_SERVICE_SESSION);
  		WebContext.removeAttribute(SOCIALSIGNON_PROVIDER_SESSION);
		if(StringUtils.isNullOrBlank(socialSignOnProvider.getVerifierCode()))
			return null;
		// getting access token
		Verifier verifier = new Verifier(oauthVerifier);
		this.accessToken=oauthService.getAccessToken(null, verifier);
		this.accountJsonString=null;
		return accessToken;
  	}
  	
  	protected String requestAccountJson() {
  		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, this.convertAccountUrl(socialSignOnProvider.getAccountUrl(),socialSignOnProvider.getProvider(), accessToken));
  		oauthService.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		accountJsonString=oauthResponse.getBody();
		_logger.debug("requestAccountJson : "+accountJsonString);
		return accountJsonString;
  	}
  	
  	

 	@SuppressWarnings("unchecked")
	protected String  getAccountId() {
 		if(StringUtils.isNullOrBlank(accountJsonString)) {
 			requestAccountJson();
 		}
 		
 		Map<String,Object> map = new HashMap<String,Object>();
 		if(this.provider.equals("qq")){
 			accountJsonString=accountJsonString.substring(accountJsonString.indexOf("{"), accountJsonString.indexOf("}")+1);
 		}
 		map=(HashMap<String,Object>)JsonUtils.json2Object(accountJsonString, map);
 		if(this.provider.equals("qqweibo")){
 			if(accessToken.getResponseObject().get(socialSignOnProvider.getAccountId())!=null){
 	 			accountId=accessToken.getResponseObject().get(socialSignOnProvider.getAccountId()).toString();
 	 		}
 		}else if(this.provider.equals("qq")){
 			accountId=map.get(socialSignOnProvider.getAccountId()).toString();

 		}else{
	 		if(map.get(socialSignOnProvider.getAccountId())!=null){
	 			accountId=map.get(socialSignOnProvider.getAccountId()).toString();
	 		}
 		}
 		
 		
 		_logger.debug("getAccountId : "+accountId);
 		return accountId;
 	}
  	
  	private String convertAccountUrl(String accountUrl,String provider,Token accessToken) {
  		if("sinaweibo".equals(provider)) {
  			Map<String,Object> map = new HashMap<String,Object>();
  	 		map=(HashMap<String,Object>)JsonUtils.json2Object(accessToken.getRawResponse(), map);
  	 		Object uid = map.get("uid");
  	 		_logger.debug("sinaweibo uid : "+map.get("uid"));
  	 		accountUrl = this.convertUrl(accountUrl, "uid", uid == null  ? "" : uid.toString());
  		}
  		return accountUrl;
  	}
  	
  	private String convertUrl(String url,String paramName,String paramVal) {
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf('?') < 0) {
			sb.append('?');
		}
		else {
			sb.append('&');
		}
		sb.append(paramName+"=").append(paramVal);
		return sb.toString();
  	}

}
