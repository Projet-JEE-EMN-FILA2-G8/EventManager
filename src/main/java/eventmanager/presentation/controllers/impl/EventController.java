package eventmanager.presentation.controllers.impl;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventmanager.integration.EventsServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.EventsServicesImpl;
import eventmanager.presentation.controllers.AbstractController;
import eventmanager.presentation.utils.Authentication;
import eventmanager.presentation.utils.Constants;
import eventmanager.presentation.utils.HttpMethod;

/**
 * @author Hadrien
 * 
 */
/**
 * Servlet implementation class EventController
 */
@WebServlet("/Event/*")
public class EventController extends AbstractController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see AbstractController#AbstractController()
	 */
	public EventController() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eventmanager.presentation.controllers.AbstractController#process(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * eventmanager.presentation.utils.HttpMethod)
	 */
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, HttpMethod method) throws ServletException, IOException {

		String[] parameters = request.getPathInfo().split("/");
		String eventId = parameters.length > 1 ? parameters[1] : null;

		if (eventId != null) {

			EventsServices eServices = new EventsServicesImpl();
			EventBean eventBean = new EventBean();

			// R�cup�ration de l'�v�nement associ�
			try {
				eventBean = eServices.getEventById(Integer.parseInt(eventId));
			} catch (Exception e) {
				this.context.log(e.getMessage());
				//Si on ne peut pas r�cup�rer l'�v�nement on affiche un message d'erreur
				request.setAttribute("eventError", true);
				// TODO log4j here - il faut distinguer un plantage couche
				// service d'une erreur de parsing
			}

			if ((eventBean != null) && (eventBean.isVisible())) {

				request.setAttribute("event", eventBean);
				request.setAttribute("eventId", Long.parseLong(eventId));
				request.setAttribute("eventurl", request.getRequestURL());

				if (method == HttpMethod.POST) {

					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					String email = request.getParameter("email");
					String societe = request.getParameter("societe");

					try {

						ParticipantBean participantBean = new ParticipantBean();
						participantBean.setNom(nom);
						participantBean.setPrenom(prenom);
						participantBean.setEmail(email);
						participantBean.setSociete(societe);

						boolean registered = eServices.registerParticipant(
								eventBean, participantBean);

						request.setAttribute("registered", registered);

						if (!registered) {
							request.setAttribute("alreadyRegistered", true);
						}

						HttpSession session = request.getSession(true);
						@SuppressWarnings("unchecked")
						TreeMap<Long, Boolean> participationMap = (TreeMap<Long, Boolean>) session
								.getAttribute("participateToEvent");
						if (participationMap == null) {
							participationMap = new TreeMap<Long, Boolean>();
						}
						participationMap.put(Long.parseLong(eventId), true);
						session.setAttribute("participateToEvent", participationMap);

					} catch (Exception ex) {
						this.context.log(ex.toString());
						request.setAttribute("errorOccured", true);
					}
				}

				if (Authentication.userIsAuthenticated(request)) {
					UserBean user = (UserBean) request.getSession().getAttribute("user");
					if (eventBean.getHote().getEmail().equals(user.getEmail())) {
						request.setAttribute("participants", eventBean.getListParticipants());
					}
				}

			} else {
				request.setAttribute("hiddenOrUnknownEvent", true);
			}

			showEventPage(request, response);
		} else {
			localRedirect(response, Constants.SERVLET_EVENT_LIST);
		}
	}

	private void showEventPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.context.getRequestDispatcher(Constants.JSP_EVENT);
		rd.include(request, response);
	}

}
