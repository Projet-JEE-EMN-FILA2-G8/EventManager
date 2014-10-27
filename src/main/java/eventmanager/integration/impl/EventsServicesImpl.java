package eventmanager.integration.impl;

import java.net.URL;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtilsBean;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.persistence.PersistenceConfig;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.EventsPersistence;
import eventmanager.integration.EventsServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class EventsServicesImpl implements EventsServices {
	private EventsPersistence dao;
	private ConvertUtilsBean beanConverter;
	
	public EventsServicesImpl() {
		dao = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		beanConverter = new ConvertUtilsBean();
		beanConverter.register(new BeanConverter(), EventsEntity.class);
	}
	
	@Override
	public boolean createEvent(EventBean event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public URL publishEvent(EventBean event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventBean> getHostedEvents(UserBean user) {
		// TODO Auto-generated method stub
		return null;
	}

}
