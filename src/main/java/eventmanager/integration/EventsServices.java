package eventmanager.integration;

import java.net.URL;
import java.util.List;

import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;

public interface EventsServices {
	
	/**
	 * @param event
	 * @return
	 */
	public boolean createEvent(EventBean event);
	/**
	 * @param event
	 * @return
	 */
	public URL publishEvent(EventBean event);
	/**
	 * @param user
	 * @return
	 */
	public List<EventBean> getHostedEvents(UserBean user);
	/**
	 * @param url
	 * @return
	 */
	public EventBean getEventByUrl(URL url);
	/**
	 * @param event
	 * @param participant
	 * @return
	 */
	public boolean registerParticipant(EventBean event, ParticipantBean participant);

}
