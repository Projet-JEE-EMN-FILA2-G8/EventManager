package eventmanager.integration.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.bean.jpa.ParticipantsEntity;
import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.persistence.PersistenceConfig;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.EventsPersistence;
import eventmanager.business.persistence.services.ParticipantsPersistence;
import eventmanager.integration.EventsServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class EventsServicesImpl implements EventsServices {
	private static final String URL_FORMAT_EVENTMANAGER = "http://localhost:8080/eventmanager/events.do?event=";
	private EventsPersistence daoEvents;
	private ParticipantsPersistence daoParticipants;
	private ConvertUtilsBean beanConverter;
	
	public EventsServicesImpl() {
		daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		daoParticipants = PersistenceServiceProvider.getService(ParticipantsPersistence.class, PersistenceConfig.JPA);
		beanConverter = new ConvertUtilsBean();
		beanConverter.register(new BeanConverter(), EventsEntity.class);
		beanConverter.register(new BeanConverter(), ParticipantsEntity.class);
		beanConverter.register(new BeanConverter(), UsersEntity.class);
		beanConverter.register(new BeanConverter(), EventBean.class);
	}
	
	@Override
	public boolean createEvent(EventBean event) {
		if (event.getNom() != null && event.getHote() != null) {
			EventsEntity eventEntity = (EventsEntity) beanConverter.convert(event, EventsEntity.class);
			eventEntity.setListOfParticipants(new ArrayList<ParticipantsEntity>());
			daoEvents.save(eventEntity);
			return true;
		}
		return false;
	}

	/**
	 * @param event l'evenement à publier
	 * @return l'URL de l'évenement
	 * @see eventmanager.integration.EventsServices#publishEvent(eventmanager.integration.bean.EventBean)
	 */
	@Override
	public URL publishEvent(EventBean event) {
		event.setVisible(true);
		EventsEntity eventEntity = daoEvents.save((EventsEntity) beanConverter.convert(event, EventsEntity.class));
		try {
			URL url = new URL(URL_FORMAT_EVENTMANAGER + eventEntity.getId());
			return url;
		} catch (MalformedURLException e) {
			// TODO Gestion exception URL malformée
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<EventBean> getHostedEvents(UserBean user) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("users", beanConverter.convert(user, UsersEntity.class));
		List<EventsEntity> resultsList = daoEvents.loadByNamedQuery("selectByUser", queryParams);
		
		if (resultsList != null) {
			List<EventBean> listToReturn = new ArrayList<EventBean>();
			for (EventsEntity elem : resultsList) {
				listToReturn.add((EventBean) beanConverter.convert(elem, EventBean.class));
			}
			return listToReturn;
		}
		
		return null;
	}

	@Override
	public boolean registerParticipant(EventBean event,
			ParticipantBean participant) {
		if (participant != null && event != null) {
			ParticipantsEntity participantEntity = (ParticipantsEntity) beanConverter.convert(participant, ParticipantsEntity.class);
			participantEntity = daoParticipants.save(participantEntity);
			EventsEntity eventEntity = daoEvents.load(event.getId());
			eventEntity.getListOfParticipants().add(participantEntity);
			daoEvents.save(eventEntity);
			return true;
		}
		return false;
	}

	@Override
	public EventBean getEventByUrl(URL url) {
		String idEvent = url.getQuery().split("=")[1];
		if (idEvent != null) {
			return (EventBean) beanConverter.convert(daoEvents.load(Integer.parseInt(idEvent)), EventBean.class);
		}
		return null;
	}

}
