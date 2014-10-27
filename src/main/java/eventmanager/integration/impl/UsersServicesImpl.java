package eventmanager.integration.impl;

import org.apache.commons.beanutils.ConvertUtilsBean;

import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.persistence.PersistenceConfig;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.UsersPersistence;
import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.AbstractBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class UsersServicesImpl implements UsersServices {
	
	private UsersPersistence dao;
	private ConvertUtilsBean beanConverter;
	
	/**
	 * Constructeur. Utilise une factory de la couche modèle pour générer le Dao.
	 */
	public UsersServicesImpl() {
		dao = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		beanConverter = new ConvertUtilsBean();
		beanConverter.register(new BeanConverter(), AbstractBean.class);
	}

	@Override
	public boolean createUser(UserBean user) {
		if (dao.load(user.getEmail()) == null && user.getPwd() != null 
				&& !"".equals(user.getPwd())) {
			dao.insert((UsersEntity) beanConverter.convert(user, UsersEntity.class));
			return true;
		}
		return false;
	}

	@Override
	public boolean authenticateUser(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePwd(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}

}
