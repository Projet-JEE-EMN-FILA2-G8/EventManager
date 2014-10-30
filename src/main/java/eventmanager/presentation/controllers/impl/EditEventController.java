package eventmanager.presentation.controllers.impl;

import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;

import eventmanager.integration.EventsServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.impl.EventsServicesImpl;
import eventmanager.presentation.controllers.AbstractController;
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
 * Servlet implementation class EditEventController
 */
@WebServlet({"/EditEvent/*", "/CreateEvent/*"})
public class EditEventController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AbstractController#AbstractController()
     */
    public EditEventController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see eventmanager.presentation.controllers.AbstractController#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, eventmanager.presentation.utils.HttpMethod)
	 */
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, HttpMethod method) throws ServletException, IOException {
		
		String servlet = request.getServletPath();
		String parametersString = request.getPathInfo();
		String[] parameters = parametersString!=null ? parametersString.split("/") : new String[]{"/1"};
		String eventId = parameters.length > 1 ? parameters[1] : null;
		
		EventsServices eServices = new EventsServicesImpl();
		EventBean eventBean = new EventBean();
		
		if(method==HttpMethod.POST) {
			
			String nom = request.getParameter("nom");	
			String description = request.getParameter("description");
			String adresse = request.getParameter("adresse");	
			String datedeb = request.getParameter("datedeb");
			String datefin = request.getParameter("datefin");
			
			try {
				eventBean.setId(Integer.parseInt(eventId!=null?eventId:""));
				eventBean.setNom(nom);
				eventBean.setDescription(description);
				eventBean.setAdresse(adresse);
				eventBean.setDatedeb(new Date(Date.parse(datedeb!=null?datedeb:"")));
				eventBean.setDatefin(new Date(Date.parse(datefin!=null?datefin:"")));
				
				eServices.createEvent(eventBean);
				
			}
			catch(Exception e) {
				
			}
			
			localRedirect(response, Constants.SERVLET_EVENT_LIST);
		}
		else
		{
			System.out.println(Constants.SERVLET_EDIT_EVENT + ":" + (servlet));
			if(Constants.SERVLET_EDIT_EVENT.equals(servlet)) {
				if(eventId!=null) {
					/*try {
					 * 
						eventBean = eServices.getEventByUrl(new URL(eventId));
					} catch (Exception e) {
						e.printStackTrace();
						localRedirect(response, Constants.SERVLET_CREATE_EVENT);*/
						eventBean.setId(Integer.parseInt(eventId));
						eventBean.setNom("Java Dev Conférence");
						eventBean.setDescription("Conférence autour de Java 8 et des technologies associées");
						eventBean.setDatedeb(new Date(2014,10,29,8,15));
						eventBean.setDatefin(new Date(2014,10,30,20,30));
						eventBean.setAdresse("Centre des congrès de Nantes - 44000 Nantes");
					//}
						request.setAttribute("event", eventBean);
				}
				else
				{
					localRedirect(response, Constants.SERVLET_CREATE_EVENT);
				}
			}
			showEditEventPage(request, response);
		}
	}
	
	private void showEditEventPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_EDIT_EVENT);	
		rd.include(request, response);
	}


}
