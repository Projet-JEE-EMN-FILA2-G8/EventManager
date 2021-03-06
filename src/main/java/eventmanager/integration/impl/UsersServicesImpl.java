package eventmanager.integration.impl;

import org.apache.commons.beanutils.ConvertUtilsBean;

import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.persistence.PersistenceConfig;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.UsersPersistence;
import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class UsersServicesImpl implements UsersServices {
	
	private UsersPersistence dao;
	private ConvertUtilsBean beanConverter;
	
	/**
	 * Constructeur. Utilise une factory de la couche mod�le pour g�n�rer le Dao.
	 */
	public UsersServicesImpl() {
		dao = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		beanConverter = new ConvertUtilsBean();
		beanConverter.register(new BeanConverter(), UsersEntity.class);
	}

	@Override
	public boolean createUser(UserBean user) {
		if (dao.load(user.getEmail()) == null && user.getPwd() != null 
				&& !"".equals(user.getPwd())) {
			UsersEntity entity = (UsersEntity) beanConverter.convert(user, UsersEntity.class);
			dao.insert(entity);
			return true;
		}
		return false;
	}

	@Override
	public boolean authenticateUser(UserBean user) {
		if (user.getEmail() != null && user.getPwd() != null) {
			UsersEntity userEntity = dao.load(user.getEmail());
			return userEntity != null && userEntity.getPwd().equals(user.getPwd());
		}
		return false;
	}

	@Override
	public boolean updatePwd(UserBean user) {
		UsersEntity userEntity = dao.load(user.getEmail());
		if (userEntity != null) {
			userEntity.setPwd(user.getPwd());
			dao.save(userEntity);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(UserBean user) {
		return dao.delete(user.getEmail());
	}

}
