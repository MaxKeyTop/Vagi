package com.connsec.web;

import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public final class CaptchaValidateAction extends AbstractAction {
	private static final Logger _logger = LoggerFactory.getLogger(CaptchaValidateAction.class);
	private String j_captcha_parameter = "j_captcha";

	protected Event doExecute(final RequestContext context) {
		
		String j_captcha = context.getRequestParameters().get(j_captcha_parameter);
		_logger.debug("j_captcha  "+j_captcha);
		if (j_captcha != null) {
			String id = WebUtils.getHttpServletRequest(context).getSession().getId();
			if (id != null) {
				if(WebUtils.getHttpServletRequest(context).getSession().getAttribute(CaptchaController.KAPTCHA_SESSION_KEY)!=null){
					String session_captcha=WebUtils.getHttpServletRequest(context).getSession().getAttribute(CaptchaController.KAPTCHA_SESSION_KEY).toString();
					_logger.debug("session_captcha  "+session_captcha);
					_logger.debug("j_captcha equalsIgnoreCase session_captcha"+j_captcha.equalsIgnoreCase(session_captcha));
					if(j_captcha.equalsIgnoreCase(session_captcha)){
						return success();
					}
				}
			}
		}

		context.getRequestScope().put("captchaValidatorError", "bad");
		return error();
	}

	public void setJ_captcha_parameter(String j_captcha_parameter) {
		this.j_captcha_parameter = j_captcha_parameter;
	}


	
}