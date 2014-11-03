package eventmanager.presentation.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventmanager.presentation.utils.HttpMethod;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet implementation class AbstractController
 */
@WebServlet("/AbstractController")
public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected ServletContext context;
	


    /**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(ServletConfig sConfig) throws ServletException {
		super.init(sConfig);
		this.context = sConfig.getServletContext();
	}
    
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AbstractController() {
		super();
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
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.GET);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.POST);
	}

	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.PUT);
	}

	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.DELETE);
	}

	protected void doHead(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.HEAD);
	}

	protected void doOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.OPTIONS);
	}

	protected void doTrace(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response, HttpMethod.TRACE);
	}


	/**
	 * Traite la requete entrante
	 * @param request la requête entrante
	 * @param response la réponse de la servlet
	 * @param method La methode utilisée (GET, POST, PUT, ...)
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void process(HttpServletRequest request,
			HttpServletResponse response, HttpMethod method)
			throws ServletException, IOException;

}
