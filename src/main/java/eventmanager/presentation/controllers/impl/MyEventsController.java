package eventmanager.presentation.controllers.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see eventmanager.presentation.controllers.AbstractController#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, eventmanager.presentation.utils.HttpMethod)
	 */
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, HttpMethod method) throws ServletException, IOException {

		EventsServices eServices = new EventsServicesImpl();
		
		if(method==HttpMethod.POST) {
			
			String action = request.getParameter(Constants.ACTION);
			String eventId = request.getParameter("eventid");
						
			if(Constants.ACTION_SHOW.equals(action)) {
				
				EventBean eventBean = new EventBean();
				eventBean.setId(Integer.parseInt(eventId));
				eServices.publishEvent(eventBean);
			}
		}
		
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		
		List<EventBean> userEvents = null;
		try {
			userEvents = eServices.getHostedEvents(user);
		}catch(Exception e) {
			e.printStackTrace();
			userEvents = new ArrayList<EventBean>();
			
			EventBean eventBean = new EventBean();
			eventBean.setId(1);
			eventBean.setNom("Java Dev Conférence");
			eventBean.setDescription("Conférence autour de Java 8 et des technologies associées");
			eventBean.setDatedeb(new Date(2014,10,29,8,15));
			eventBean.setDatefin(new Date(2014,10,30,20,30));
			eventBean.setAdresse("Centre des congrès de Nantes - 44000 Nantes");
			eventBean.setVisible(true);
			userEvents.add(eventBean);
			userEvents.add(eventBean);
		}
		
		
		request.setAttribute("myEvents", userEvents);
		
		showMyEventsPage(request, response);
		
	}
	
	
	private void showMyEventsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_EVENT_LIST);	
		rd.include(request, response);
	}

}
