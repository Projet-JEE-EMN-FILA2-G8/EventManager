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

	/** 
	 * @param user l'user à authentifier
	 * @return true si l'user est présent en base et si le pwd correspond
	 * @see eventmanager.integration.UsersServices#authenticateUser(eventmanager.integration.bean.UserBean)
	 */
	@Override
	public boolean authenticateUser(UserBean user) {
		if (user.getEmail() != null && user.getPwd() != null) {
			UsersEntity userEntity = dao.load(user.getEmail());
			return userEntity != null && userEntity.getPwd().equals(user.getPwd());
		}
		return false;
	}

	/**
	 * @param l'user à mettre à jour
	 * @return true si l'user a bien été mis à jour
	 * @see eventmanager.integration.UsersServices#updatePwd(eventmanager.integration.bean.UserBean)
	 */
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
