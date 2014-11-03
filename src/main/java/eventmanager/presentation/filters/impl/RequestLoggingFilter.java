package eventmanager.presentation.filters.impl;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventmanager.presentation.filters.AbstractFilter;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter extends AbstractFilter implements Filter {
       
    /**
     * @see AbstractFilter#AbstractFilter()
     */
    public RequestLoggingFilter() {
        super();
    }

	/* (non-Javadoc)
	 * @see eventmanager.presentation.filters.AbstractFilter#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void process(HttpServletRequest request,	HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = request.getParameter(name);
            this.context.log(request.getRemoteAddr() + " : param {"+name+"="+value+"}");
        }       
        
        try {        	
        	chain.doFilter(request, response);
        } catch (Exception e) {
        	this.context.log(e.toString());
        }
	}

}
