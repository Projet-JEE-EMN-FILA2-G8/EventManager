package eventmanager.integration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.bean.jpa.ParticipantsEntity;
import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.persistence.PersistenceConfig;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.EventsPersistence;
import eventmanager.business.persistence.services.ParticipantsPersistence;
import eventmanager.business.persistence.services.RegistrationPersistence;
import eventmanager.integration.EventsServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class EventsServicesImpl implements EventsServices {
	private static final String SELECT_EVENT_BY_USER = "EventsEntity.selectByUser";
	private static final String SELECT_PARTICIPANT_BY_EMAIL = "ParticipantsEntity.selectByEmail";
	private EventsPersistence daoEvents;
	private ParticipantsPersistence daoParticipants;
	private RegistrationPersistence daoRegistration;
	private ConvertUtilsBean beanConverter;

	public EventsServicesImpl() {
		daoEvents = PersistenceServiceProvider.getService(
				EventsPersistence.class, PersistenceConfig.JPA);
		daoParticipants = PersistenceServiceProvider.getService(
				ParticipantsPersistence.class, PersistenceConfig.JPA);
		daoRegistration = PersistenceServiceProvider.getService(
				RegistrationPersistence.class, PersistenceConfig.JPA);
		beanConverter = new ConvertUtilsBean();
		beanConverter.register(new BeanConverter(), EventsEntity.class);
		beanConverter.register(new BeanConverter(), ParticipantsEntity.class);
		beanConverter.register(new BeanConverter(), UsersEntity.class);
		beanConverter.register(new BeanConverter(), EventBean.class);
	}

	@Override
	public EventBean createOrUpdateEvent(EventBean event) {
		if (event.getNom() != null && event.getHote() != null) {
			EventsEntity eventEntity = (EventsEntity) beanConverter.convert(
					event, EventsEntity.class);
			if (eventEntity.getListOfParticipants() == null) {
				eventEntity
						.setListOfParticipants(new ArrayList<ParticipantsEntity>());
			}
			EventsEntity createdEvent = daoEvents.save(eventEntity);
			return (EventBean) beanConverter.convert(createdEvent,
					EventBean.class);
		}
		return null;
	}

	/**
	 * @param event
	 *            l'evenement à publier
	 * @return l'URL de l'évenement
	 * @see eventmanager.integration.EventsServices#publishEvent(eventmanager.integration.bean.EventBean)
	 */
	@Override
	public boolean publishEvent(EventBean event) {
		event.setVisible(true);
		EventsEntity eventEntity = daoEvents.save((EventsEntity) 
				beanConverter.convert(event, EventsEntity.class));

		return eventEntity.getVisible() == 1;
	}

	@Override
	public List<EventBean> getHostedEvents(UserBean user) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams
				.put("users", beanConverter.convert(user, UsersEntity.class));
		List<EventsEntity> resultsList = daoEvents.loadByNamedQuery(
				SELECT_EVENT_BY_USER, queryParams);

		if (resultsList != null && !resultsList.isEmpty()) {
			List<EventBean> listToReturn = new ArrayList<EventBean>();
			for (EventsEntity elem : resultsList) {
				listToReturn.add((EventBean) beanConverter.convert(elem,
						EventBean.class));
			}
			return listToReturn;
		}

		return null;
	}

	@Override
	public boolean registerParticipant(EventBean event,
			ParticipantBean participant) {
		if (participant != null && event != null) {
			ParticipantsEntity participantEntity = (ParticipantsEntity) beanConverter
					.convert(participant, ParticipantsEntity.class);

			// Récupération du participant en base, s'il existe
			participantEntity.setId(getParticipantIdByEmail(participant
					.getEmail()));

			if (participantEntity.getId() != null
					&& isRegistered(event, participantEntity)) {
				// Si le participant existe déjà en base, on vérifie qu'il n'est
				// pas déjà inscrit à l'evenement
				return false;
			}

			// Save or update
			participantEntity = daoParticipants.save(participantEntity);

			// Récupération de l'evenement
			EventsEntity eventEntity = daoEvents.load(event.getId());
			eventEntity.getListOfParticipants().add(participantEntity);

			// Sauvegarde
			daoEvents.save(eventEntity);
			return true;
		}
		return false;
	}

	/**
	 * Récupère un participant en base via son email.
	 * 
	 * @param email
	 *            l'email du participant
	 * @return null si le participant n'existe pas en base, un
	 *         ParticipantsEntity sinon
	 */
	private Integer getParticipantIdByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		List<ParticipantsEntity> entityList = daoParticipants.loadByNamedQuery(
				SELECT_PARTICIPANT_BY_EMAIL, params);
		if (entityList != null && !entityList.isEmpty()) {
			return entityList.get(0).getId();
		} else
			return null;
	}

	/**
	 * @param event
	 *            L'evenement
	 * @param participant
	 *            le participant
	 * @return true si le participant est inscrit à l'evenement
	 */
	public boolean isRegistered(EventBean event, ParticipantsEntity participant) {
		if (participant.getId() == null) 
			participant.setId(getParticipantIdByEmail(participant.getEmail()));
		return daoRegistration.load(event.getId(), participant.getId()) != null;
	}

	@Override
	public EventBean getEventById(int idEvent) {
		EventsEntity loadedEvent = daoEvents.load(new Integer(idEvent));
		return loadedEvent == null?
				null:(EventBean) beanConverter.convert(loadedEvent, EventBean.class);
	}

}
