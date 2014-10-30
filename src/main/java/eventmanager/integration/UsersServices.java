 package eventmanager.integration;

import eventmanager.integration.bean.UserBean;

public interface UsersServices {
	
	/**
	 * @param user l'utilisateur à créer
	 * @return false si l'utilisateur est déjà présent en base, true sinon
	 */
	public boolean createUser(UserBean user);
	/** 
	 * @param user l'user à authentifier
	 * @return true si l'user est présent en base et si le pwd correspond
	 * @see eventmanager.integration.UsersServices#authenticateUser(eventmanager.integration.bean.UserBean)
	 */
	public boolean authenticateUser(UserBean user);
	/**
	 * @param l'user à mettre à jour
	 * @return true si l'user a bien été mis à jour
	 * @see eventmanager.integration.UsersServices#updatePwd(eventmanager.integration.bean.UserBean)
	 */
	public boolean updatePwd(UserBean user);
	/**
	 * @param user l'utilisateur à supprimer
	 * @return true si la suppression a réussi
	 */
	public boolean deleteUser(UserBean user);
}
