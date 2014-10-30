package eventmanager.presentation.controllers.impl;

import java.io.IOException;
import java.io.PrintWriter;

import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.UsersServicesImpl;
import eventmanager.presentation.controllers.AbstractController;
import eventmanager.presentation.utils.Authentication;
import eventmanager.presentation.utils.Constants;
import eventmanager.presentation.utils.HttpMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet implementation class SubscribeController
 */
@WebServlet(description = "Controleur pour l'enristrement des utilisateurs", urlPatterns = { "/Subscribe" })
public class SubscribeController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractController#AbstractController()
     */
    public SubscribeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see eventmanager.presentation.controllers.AbstractController#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, eventmanager.presentation.utils.HttpMethod)
	 */
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, HttpMethod method) throws ServletException, IOException {
		if(Authentication.userIsAuthenticated(request)) {
			showMainPage(response);
		}
		else {
			
			String message=null;
			
			if(method==HttpMethod.POST) {
				
				String user = request.getParameter("email");
		        String pwd = request.getParameter("password");
		        
		        UsersServices uServices = new UsersServicesImpl();
		        UserBean userBean = new UserBean(user, pwd);
		        
		        boolean authenticated = false;
        		try {
        			authenticated = uServices.authenticateUser(userBean);
	        	}catch(Exception ex){ex.printStackTrace();}		
		        
		        if(!authenticated){
		        	
		        	boolean success=true;
		        	try {
		        		success = uServices.createUser(userBean);
		        	}catch(Exception ex){ex.printStackTrace();}
		        	
		        	if(success) {
		        		HttpSession session = request.getSession();
		        		session.setAttribute("user", userBean);
		        		session.setMaxInactiveInterval(30*60);
		        		showMainPage(response);
		        		return;
		        	}
		        	else 
		        	{
		        		message = "<font color=red>Impossible de creer cet utilisateur</font>";
		        	}
		            
		        }else {
		        	message = "<font color=red>Vous avez déjà un compte !</font>";
		        }
			} 
			
			showSubscribePage(request, response, message);		
		}
		
	}
	
	private void showSubscribePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("isSubscribePage", true);
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_SUBSCRIBE);
		if( !(message== null || message.isEmpty()) ) {
			PrintWriter out= response.getWriter();
	        out.println(message);
		}		
		rd.include(request, response);
	}
	
	private void showMainPage(HttpServletResponse response) throws IOException {
		localRedirect(response, Constants.SERVLET_MAIN);
	}

}
