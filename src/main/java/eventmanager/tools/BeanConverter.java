package eventmanager.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.bean.jpa.ParticipantsEntity;
import eventmanager.business.bean.jpa.RegistrationEntity;
import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.RegistrationBean;
import eventmanager.integration.bean.UserBean;

public class BeanConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class clazz, Object value) {
		Object valToReturn = null;
		
		if (clazz == UsersEntity.class && value instanceof UserBean) {
			valToReturn = new UsersEntity();
		} else if (clazz == EventsEntity.class && value instanceof EventBean) {
			valToReturn = new EventsEntity();
			List<ParticipantBean> listToConvert = ((EventBean) value).getListParticipants();
			List<ParticipantsEntity> convertedList = new ArrayList<ParticipantsEntity>();
			for (ParticipantBean elem : listToConvert) {
				convertedList.add((ParticipantsEntity) convert(ParticipantsEntity.class, elem));
			}
			((EventsEntity) valToReturn).setListOfParticipants(convertedList);
			UserBean userToConvert = ((EventBean) value).getHote();
			((EventsEntity) valToReturn).setUsers((UsersEntity) convert(UsersEntity.class, userToConvert));
		} else if (clazz == EventBean.class && value instanceof EventsEntity) {
			valToReturn = new EventBean();
			List<ParticipantsEntity> listToConvert = ((EventsEntity) value).getListOfParticipants();
			List<ParticipantBean> convertedList = new ArrayList<ParticipantBean>();
			for (ParticipantsEntity elem : listToConvert) {
				convertedList.add((ParticipantBean) convert(ParticipantBean.class, elem));
			}
			((EventBean) valToReturn).setListParticipants(convertedList);
			UsersEntity userToConvert = ((EventsEntity) value).getUsers();
			((EventBean) valToReturn).setHote((UserBean) convert(UserBean.class, userToConvert));
		} else if (clazz == RegistrationEntity.class && value instanceof RegistrationBean) {
			valToReturn = new RegistrationEntity();
		} else if (clazz == ParticipantsEntity.class && value instanceof ParticipantBean) {
			valToReturn = new ParticipantsEntity();
			List<EventBean> listToConvert = ((ParticipantBean) value).getListEvents();
			List<EventsEntity> convertedList = new ArrayList<EventsEntity>();
			for (EventBean elem : listToConvert) {
				convertedList.add((EventsEntity) convert(EventsEntity.class, elem));
			}
			((ParticipantsEntity) valToReturn).setListOfEvents(convertedList);
		} else if (clazz == ParticipantBean.class && value instanceof ParticipantsEntity) {
			
		}
		try {
			BeanUtils.copyProperties(valToReturn, value);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Gestion des exceptions lors de la conversion des types
			e.printStackTrace();
		}
		return valToReturn;
	}

}
