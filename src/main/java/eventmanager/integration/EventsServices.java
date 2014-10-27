package eventmanager.integration;

import java.net.URL;
import java.util.List;

import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.UserBean;

public interface EventsServices {
	
	public boolean createEvent(EventBean event);
	public URL publishEvent(EventBean event);
	public List<EventBean> getHostedEvents(UserBean user);

}
