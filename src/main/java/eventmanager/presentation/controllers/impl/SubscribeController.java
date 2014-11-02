package eventmanager.presentation.controllers.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.UsersServicesImpl;
import eventmanager.presentation.controllers.AbstractController;
import eventmanager.presentation.utils.Authentication;
import eventmanager.presentation.utils.Constants;
import eventmanager.presentation.utils.HttpMethod;
import eventmanager.tools.MailingEngine;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet implementation class SubscribeController
 */
@WebServlet(description = "Controleur pour l'enregistrement des utilisateurs", urlPatterns = { "/Subscribe" })
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
			
			if(method==HttpMethod.POST) {
				
				String user = request.getParameter("email");
		        String pwd = request.getParameter("pwd");
		        String confirm = request.getParameter("confirm");
		        UserBean userBean = new UserBean(user, pwd);
		        
		        if(confirm==null || !confirm.equals(pwd)) {
		        	
		        	request.setAttribute("notMatchingPwd", true);
		        	request.setAttribute("user", userBean);  // Pour la récupération des valeurs côté JSP
		        
		        } else {
		        	
		        	UsersServices uServices = new UsersServicesImpl();
			        
			        try {
			        	
				        if(uServices.createUser(userBean)){
			        		HttpSession session = request.getSession();
			        		session.setAttribute("user", userBean);
			        		session.setMaxInactiveInterval(30*60);
			        		// Envoi du mail de confirmation
			        		MailingEngine.getInstance().sendUserCreationConfirmation(userBean); 
			        		showMainPage(response);
			        		return;
			        		
				        } else {
				        	request.setAttribute("alreadyRegistered", true);
				        }
				        
			        } catch (Exception e) {
			        	// log4j ici
			        	this.context.log("Impossible de creer l'user");
			        	request.setAttribute("errorOccured", true);
			        }
			       
		        }
			} 
			showSubscribePage(request, response);		
		}
		
	}
	
	private void showSubscribePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_SUBSCRIBE);	
		rd.include(request, response);
	}
	
	private void showMainPage(HttpServletResponse response) throws IOException {
		localRedirect(response, Constants.SERVLET_MAIN);
	}

}
