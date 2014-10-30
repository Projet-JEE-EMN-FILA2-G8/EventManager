package eventmanager.integration;

import java.net.URL;
import java.util.List;

import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;

public interface EventsServices {
	
	/**
	 * Créer un evenement.
	 * @param event l'evenement à créér
	 * @return true si la création a réussi
	 */
	public EventBean createEvent(EventBean event);
	/**
	 * Publie un evenement. Marque l'indicateur visible à True et génère une URL
	 * @param event l'evenement à publier
	 * @return
	 */
	public URL publishEvent(EventBean event);
	/**
	 * Retourne une liste d'evenement dont l'hôte est l'utilisateur passé en paramètre
	 * @param user l'utilisateur hôte des evenements à rechercher
	 * @return la liste des evenements créés par l'utilisateur
	 */
	public List<EventBean> getHostedEvents(UserBean user);
	/**
	 * @param url l'URL de l'evenement
	 * @return le bean de l'evenement pointé par l'url, null s'il n'existe pas
	 */
	public EventBean getEventByUrl(URL url);
	/**
	 * Enregistre un participant à un evenement
	 * @param event l'evenement auquel le participant s'inscrit
	 * @param participant le participant à inscrire
	 * @return true si l'inscription a réussi
	 */
	public boolean registerParticipant(EventBean event, ParticipantBean participant);

}
