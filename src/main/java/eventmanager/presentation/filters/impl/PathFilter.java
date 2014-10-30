package eventmanager.presentation.filters.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventmanager.presentation.filters.AbstractFilter;
import eventmanager.presentation.utils.Constants;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet Filter implementation class PathFilter
 */
@WebFilter(description = "Filtre les chemins", urlPatterns = { "/PathFilter" })
public class PathFilter extends AbstractFilter implements Filter {
       
    /**
     * @see AbstractFilter#AbstractFilter()
     */
    public PathFilter() {
        super();
        // TODO Auto-generated constructor stub
    }


	/* (non-Javadoc)
	 * @see eventmanager.presentation.filters.AbstractFilter#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void process(HttpServletRequest request,	HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		String servletPath = request.getServletPath();
		
		if(servletPath==null || servletPath.replace("/", "").equals("")) {
			localRedirect(response, Constants.SERVLET_MAIN);
		}
		else
		{
			//ServletContext context = getServletContext();
			//rd = context.getRequestDispatcher("/page.html");
			//rd = context.getNamedDispatcher("name");
			
			chain.doFilter(request, response);
		}
	
	}

}
