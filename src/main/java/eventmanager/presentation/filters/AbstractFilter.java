package eventmanager.presentation.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet Filter implementation class AbstractFilter
 */
/*at WebFilter("/*")*/
public abstract class AbstractFilter implements Filter {

	protected FilterConfig filterConfig = null;
	protected ServletContext context;
	
    /**
     * Default constructor. 
     */
    public AbstractFilter() {
       super();
    }

    /**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}
    
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
			process((HttpServletRequest) request, (HttpServletResponse) response, chain);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * Effectue une redirection par rapport au context path
	 * @param response
	 * @param localPath
	 * @throws IOException
	 */
	protected void localRedirect(HttpServletResponse response, String localPath) throws IOException {
		response.sendRedirect(context.getContextPath() + localPath);
	}
	
	/**
	 * Traite la requete entrante
	 * @param request la requête entrante
	 * @param response la réponse de la servlet
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void process(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
	}

}
