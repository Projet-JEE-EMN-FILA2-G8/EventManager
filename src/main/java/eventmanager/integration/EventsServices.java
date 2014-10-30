package eventmanager.integration;

import java.util.List;

import eventmanager.business.bean.jpa.ParticipantsEntity;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;

public interface EventsServices {
	
	/**
	 * Créer un evenement.
	 * @param event l'evenement à créér
	 * @return true si la création a réussi
	 */
	public EventBean createOrUpdateEvent(EventBean event);
	/**
	 * Publie un evenement. Marque l'indicateur visible à True et génère une URL
	 * @param event l'evenement à publier
	 * @return
	 */
	public boolean publishEvent(EventBean event);
	/**
	 * Retourne une liste d'evenement dont l'hôte est l'utilisateur passé en paramètre
	 * @param user l'utilisateur hôte des evenements à rechercher
	 * @return la liste des evenements créés par l'utilisateur
	 */
	public List<EventBean> getHostedEvents(UserBean user);
	/**
	 * @param id l'id de l'evenement
	 * @return le bean de l'evenement pointé par l'id (récupéré depuis une URL), null s'il n'existe pas ou s'il n'a pas été publié
	 */
	public EventBean getEventById(int idEvent);
	/**
	 * Enregistre un participant à un evenement
	 * @param event l'evenement auquel le participant s'inscrit
	 * @param participant le participant à inscrire
	 * @return true si l'inscription a réussi
	 */
	public boolean registerParticipant(EventBean event, ParticipantBean participant);
	
	/**
	 * @param event L'evenement
	 * @param participant le participant
	 * @return true si le participant est inscrit à l'evenement
	 */
	public boolean isRegistered(EventBean event, ParticipantsEntity participant);

}
