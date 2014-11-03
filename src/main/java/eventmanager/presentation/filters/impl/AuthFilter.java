package eventmanager.presentation.filters.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventmanager.presentation.filters.AbstractFilter;
import eventmanager.presentation.utils.Authentication;
import eventmanager.presentation.utils.Constants;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(description = "Filtrage des requ�tes selon l'authentification du client", urlPatterns = { "/AuthFilter" })
public final class AuthFilter extends AbstractFilter implements Filter {

	/**
	 * @see AbstractFilter#AbstractFilter()
	 */
	public AuthFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eventmanager.presentation.filters.AbstractFilter#process(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain)
	 */
	public void process(HttpServletRequest request,	HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		boolean isResource = isAuthorizedResource(request);
		boolean isAuthenticated = Authentication.userIsAuthenticated(request);
		boolean needAuthentication = targetNeedAuthentication(request);

		if (isAuthenticated || isResource || (! needAuthentication)) {
			chain.doFilter(request, response);
		} else {
			this.context.log("Acc�s non autoris� pour la requ�te : " + request.getRequestURI());
			localRedirect(response, Constants.SERVLET_LOGIN);
		}
	}

	/**
	 *  M�thode indiquant si la requ�te entrante designe une ressource pour laquelle il n'est pas n�cessaire d'�tre authentifi�
	 * @param request la requ�te entrante
	 * @return True si la requ�te designe une ressource pour laquelle il n'est pas n�cessaire d'�tre authentifi�
	 */
	private boolean isAuthorizedResource(HttpServletRequest request) {
		String requestedPath = request.getRequestURI();
		
		boolean authorizedTarget= false;
		
		if(requestedPath!=null) {
			for(String path : Authentication.getNotRestrictedPaths()) {
				authorizedTarget |= requestedPath.contains(path);
			}
		}	
		
		if(!authorizedTarget) {
			for(String path : Authentication.getNotRestrictedFiles()) {
				authorizedTarget |= requestedPath.endsWith(path);
			}
		}
		
		return authorizedTarget;
	}
	
	/**
	 *  M�thode indiquant si la requ�te entrante designe une servlet pour laquelle il faut �tre authentifi�
	 * @param request la requ�te entrante
	 * @return True si la requ�te designe une servlet pour laquelle il faut �tre authentifi�
	 */
	private boolean targetNeedAuthentication(HttpServletRequest request) {
		
		String servlet = request.getServletPath();
		
		boolean authorizedServlet = false;
		
		for(String serv : Authentication.getNotRestrictedServlets()) {
			authorizedServlet |= serv.equals(servlet);
		}
		
		return !authorizedServlet;
	}

}
