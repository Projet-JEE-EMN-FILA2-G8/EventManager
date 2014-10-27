package eventmanager.tools;

import java.lang.reflect.InvocationTargetException;

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
		} else if (clazz == RegistrationEntity.class && value instanceof RegistrationBean) {
			valToReturn = new RegistrationEntity();
		} else if (clazz == ParticipantsEntity.class && value instanceof ParticipantBean) {
			valToReturn = new ParticipantsEntity();
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
