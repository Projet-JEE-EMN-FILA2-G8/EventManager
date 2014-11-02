package eventmanager.presentation.controllers.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventmanager.integration.EventsServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.EventsServicesImpl;
import eventmanager.presentation.controllers.AbstractController;
import eventmanager.presentation.utils.Constants;
import eventmanager.presentation.utils.HttpMethod;
import eventmanager.tools.MailingEngine;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet implementation class MyEventsController
 */
@WebServlet(description = "Controleur de la liste des evenements", urlPatterns = { "/MyEvents" })
public class MyEventsController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractController#AbstractController()
     */
    public MyEventsController() {
        super();
    }

	/* (non-Javadoc)
	 * @see eventmanager.presentation.controllers.AbstractController#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, eventmanager.presentation.utils.HttpMethod)
	 */
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, HttpMethod method) throws ServletException, IOException {

		EventsServices eServices = new EventsServicesImpl();
		
		if(method==HttpMethod.POST) {
			
			String action = request.getParameter(Constants.ACTION);
			String eventId = request.getParameter("eventId");
						
			if(Constants.ACTION_SHOW.equals(action) && eventId != null
					&& !"".equals(eventId)) {
				
				// Récupération de l'évènement
				EventBean eventBean = eServices.getEventById(Integer.parseInt(eventId));
				if (eventBean != null && 
						eServices.publishEvent(eventBean)) {
					// Envoi du mail de confirmation
					MailingEngine mailEngine = MailingEngine.getInstance();
					String url = request.getScheme() + 
							"://" + request.getServerName() + ":" 
							+ request.getServerPort() + ":" 
							+ request.getContextPath() +
							"/Event/" + eventBean.getId();
					mailEngine.sendPublishingConfirmationMail(eventBean, url);
				}
			}
		}
		
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		
		List<EventBean> userEvents = null;
		try {
			userEvents = eServices.getHostedEvents(user);
		}catch(Exception e) {
			// TODO error handling + log4j here
			e.printStackTrace();
		}
		
		request.setAttribute("myEvents", userEvents);
		showMyEventsPage(request, response);
	}
	
	
	private void showMyEventsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_EVENT_LIST);	
		rd.include(request, response);
	}

}
