 package eventmanager.integration;

import eventmanager.integration.bean.UserBean;

public interface UsersServices {
	
	/**
	 * @param user
	 * @return
	 */
	public boolean createUser(UserBean user);
	/**
	 * @param user
	 * @return
	 */
	public boolean authenticateUser(UserBean user);
	/**
	 * @param user
	 * @return
	 */
	public boolean updatePwd(UserBean user);
	/**
	 * @param user
	 * @return
	 */
	public boolean deleteUser(UserBean user);
}
