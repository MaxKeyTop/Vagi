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
package com.connsec.web.oauth.flow;

import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.authentication.principal.WebApplicationService;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.connsec.web.WebContext;
import com.connsec.web.rememberme.RemeberMeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * This class represents an action to put at the beginning of the webflow.
 * <p>
 * Before any authentication, redirection urls are computed for the different clients defined as well as the theme,
 * locale, method and service are saved into the web session.</p>
 * After authentication, appropriate information are expected on this callback url to finish the authentication
 * process with the provider.
 * @author Jerome Leleu
 * @since 3.5.0
 */

public final class RemeberMeLoginAction extends AbstractAction {
    /**
     * Constant for the service parameter.
     */
    public static final String SERVICE = "service";
    /**
     * Constant for the theme parameter.
     */
    public static final String THEME = "theme";
    /**
     * Constant for the locale parameter.
     */
    public static final String LOCALE = "locale";
    /**
     * Constant for the method parameter.
     */
    public static final String METHOD = "method";


    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(RemeberMeLoginAction.class);

    /**
     * The service for CAS authentication.
     */
    @NotNull
    private final CentralAuthenticationService centralAuthenticationService;

    private final RemeberMeService remeberMeService;
    /**
     * Build the action.
     *
     * @param theCentralAuthenticationService The service for CAS authentication
     * @param theClients The clients for authentication
     */
    public RemeberMeLoginAction(final CentralAuthenticationService theCentralAuthenticationService,RemeberMeService remeberMeService) {
        this.centralAuthenticationService = theCentralAuthenticationService;
        this.remeberMeService =remeberMeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Event doExecute(final RequestContext context) throws Exception {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        final HttpServletResponse response = WebUtils.getHttpServletResponse(context);
        final HttpSession session = request.getSession();

        // retrieve parameters from web session
        final Service service = (Service) session.getAttribute(SERVICE);
        context.getFlowScope().put(SERVICE, service);
        logger.debug("retrieve service: {}", service);
        if (service != null) {
            request.setAttribute(SERVICE, service.getId());
        }
        restoreRequestAttribute(request, session, THEME);
        restoreRequestAttribute(request, session, LOCALE);
        restoreRequestAttribute(request, session, METHOD);
        
        String remeberMeString =remeberMeService.getRemeberMe(request);
        // credentials not null -> try to authenticate
        if (null	!= 	remeberMeString && !remeberMeString.equals("") && remeberMeService.validate(remeberMeString)) {
        	WebContext.setAttribute(RemeberMeService.LOGIN_REMEBERME_SESSION,RemeberMeService.LOGIN_REMEBERME_SESSION);
            final TicketGrantingTicket tgt = 
                    this.centralAuthenticationService.createTicketGrantingTicket(new UsernamePasswordCredential(remeberMeService.getUsername(remeberMeString),""));
            WebUtils.putTicketGrantingTicketInScopes(context, tgt);
            WebContext.removeAttribute(RemeberMeService.LOGIN_REMEBERME_SESSION);
            return success();
        }
                
        // no or aborted authentication : go to login page
        //prepareForLoginPage(context);
        return error();
    }

    /**
     * Prepare the data for the login page.
     *
     * @param context The current webflow context
     */
    protected void prepareForLoginPage(final RequestContext context) {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        final HttpServletResponse response = WebUtils.getHttpServletResponse(context);
        final HttpSession session = request.getSession();

        // save parameters in web session
        final WebApplicationService service = WebUtils.getService(context);
        logger.debug("save service: {}", service);
        session.setAttribute(SERVICE, service);
        saveRequestParameter(request, session, THEME);
        saveRequestParameter(request, session, LOCALE);
        saveRequestParameter(request, session, METHOD);

    }

    /**
     * Restore an attribute in web session as an attribute in request.
     *
     * @param request The HTTP request
     * @param session The HTTP session
     * @param name The name of the parameter
     */
    private void restoreRequestAttribute(final HttpServletRequest request, final HttpSession session,
            final String name) {
        final String value = (String) session.getAttribute(name);
        request.setAttribute(name, value);
    }

    /**
     * Save a request parameter in the web session.
     *
     * @param request The HTTP request
     * @param session The HTTP session
     * @param name The name of the parameter
     */
    private void saveRequestParameter(final HttpServletRequest request, final HttpSession session,
            final String name) {
        final String value = request.getParameter(name);
        if (value != null) {
            session.setAttribute(name, value);
        }
    }
}
