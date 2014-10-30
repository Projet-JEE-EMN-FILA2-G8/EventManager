 package eventmanager.integration;

import eventmanager.integration.bean.UserBean;

public interface UsersServices {
	
	/**
	 * @param user l'utilisateur � cr�er
	 * @return false si l'utilisateur est d�j� pr�sent en base, true sinon
	 */
	public boolean createUser(UserBean user);
	/** 
	 * @param user l'user � authentifier
	 * @return true si l'user est pr�sent en base et si le pwd correspond
	 * @see eventmanager.integration.UsersServices#authenticateUser(eventmanager.integration.bean.UserBean)
	 */
	public boolean authenticateUser(UserBean user);
	/**
	 * @param l'user � mettre � jour
	 * @return true si l'user a bien �t� mis � jour
	 * @see eventmanager.integration.UsersServices#updatePwd(eventmanager.integration.bean.UserBean)
	 */
	public boolean updatePwd(UserBean user);
	/**
	 * @param user l'utilisateur � supprimer
	 * @return true si la suppression a r�ussi
	 */
	public boolean deleteUser(UserBean user);
}
