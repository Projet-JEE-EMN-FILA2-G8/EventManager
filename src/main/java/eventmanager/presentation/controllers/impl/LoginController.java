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

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractController#AbstractController()
     */
    public LoginController() {
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
		        
		        UsersServices uServices = new UsersServicesImpl();
		        UserBean userBean = new UserBean(user, pwd);
		        
		        try {
		        	if(uServices.authenticateUser(userBean)){
			            HttpSession session = request.getSession();
			            session.setAttribute("user", userBean);
			            session.setMaxInactiveInterval(30*60);
			            showMainPage(response);
			            return;
			        }else {
			        	request.setAttribute("badLoginOrPassword", true);
			        }
		        }catch(Exception e) {
		        	request.setAttribute("errorOccured", true);
		        }
			} 
			showLoginPage(request, response);		
		}
	}
	
	private void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("isLoginPage", true);
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_LOGIN);	
		rd.include(request, response);
	}
	
	private void showMainPage(HttpServletResponse response) throws IOException {
		localRedirect(response, Constants.SERVLET_MAIN);
	}
}
