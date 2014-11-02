package eventmanager.presentation.controllers.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		// Récupération de l'evenement si on est en édition
		EventBean eventBean = (EventBean) (request.getAttribute("event") != null?
					request.getAttribute("event"):new EventBean());
		
		if(method==HttpMethod.POST) {
			
			String nom = request.getParameter("nom");	
			String description = request.getParameter("description");
			String adresse = request.getParameter("adresse");	
			String datedeb = request.getParameter("datedeb");
			String datefin = request.getParameter("datefin");
			
			try {
				eventBean.setId(eventId == null || "".equals(eventId)?
						null:
						Integer.parseInt(eventId));
				eventBean.setNom(nom);
				eventBean.setDescription(description);
				eventBean.setAdresse(adresse);
				
				// TODO : Set dates as required
				try {
					eventBean.setDatedeb(datedeb == null || "".equals(datedeb)?
							new Date():
							DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).parse(datedeb));
				} catch (ParseException e) {
					// Si le parse ne fonctionne pas, on ne modifie pas la date ?
					// TODO log4j here
					eventBean.setDatedeb(new Date());
				}
				try {
					eventBean.setDatefin(datefin == null || "".equals(datefin)?
							new Date():
							DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).parse(datefin));
				} catch (ParseException e) {
					// TODO log4j here
					eventBean.setDatefin(new Date());
				}
				
				// Récupération de l'utilisateur créateur de l'evenement
				eventBean.setHote((UserBean) request.getSession().getAttribute("user"));
				
				// TODO si cette methode renvoie null, l'insertion  a fail
				eServices.createOrUpdateEvent(eventBean);
			}
			catch(Exception e) {
				e.printStackTrace();
				// TODO log4j needed here
			}
			
			localRedirect(response, Constants.SERVLET_EVENT_LIST);
		}
		else
		{
			System.out.println(Constants.SERVLET_EDIT_EVENT + ":" + (servlet));
			if(Constants.SERVLET_EDIT_EVENT.equals(servlet)) {
				if(eventId!=null) {
					try {
						eventBean = eServices.getEventById(Integer.parseInt(eventId));
					} catch (Exception e) {
						// TODO error handling + log4j here
						e.printStackTrace();
						localRedirect(response, Constants.SERVLET_CREATE_EVENT);
					}
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
