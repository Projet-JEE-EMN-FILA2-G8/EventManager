 package eventmanager.integration;

import eventmanager.integration.bean.UserBean;

public interface UsersServices {
	
	public boolean createUser(UserBean user);
	public boolean authenticateUser(UserBean user);
	public boolean updatePwd(UserBean user);
	public boolean deleteUser(UserBean user);
}
