package eventmanager.integration;

import java.util.List;

import eventmanager.business.bean.jpa.ParticipantsEntity;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;

public interface EventsServices {
	
	/**
	 * Cr�er un evenement.
	 * @param event l'evenement � cr��r
	 * @return true si la cr�ation a r�ussi
	 */
	public EventBean createOrUpdateEvent(EventBean event);
	/**
	 * Publie un evenement. Marque l'indicateur visible � True et g�n�re une URL
	 * @param event l'evenement � publier
	 * @return
	 */
	public boolean publishEvent(EventBean event);
	/**
	 * Retourne une liste d'evenement dont l'h�te est l'utilisateur pass� en param�tre
	 * @param user l'utilisateur h�te des evenements � rechercher
	 * @return la liste des evenements cr��s par l'utilisateur
	 */
	public List<EventBean> getHostedEvents(UserBean user);
	/**
	 * @param id l'id de l'evenement
	 * @return le bean de l'evenement point� par l'id (r�cup�r� depuis une URL), null s'il n'existe pas ou s'il n'a pas �t� publi�
	 */
	public EventBean getEventById(int idEvent);
	/**
	 * Enregistre un participant � un evenement
	 * @param event l'evenement auquel le participant s'inscrit
	 * @param participant le participant � inscrire
	 * @return true si l'inscription a r�ussi
	 */
	public boolean registerParticipant(EventBean event, ParticipantBean participant);
	
	/**
	 * @param event L'evenement
	 * @param participant le participant
	 * @return true si le participant est inscrit � l'evenement
	 */
	public boolean isRegistered(EventBean event, ParticipantsEntity participant);

}
