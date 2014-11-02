package eventmanager.test.services;

import org.junit.Before;
import org.junit.Ignore;

import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.UsersServicesImpl;
import eventmanager.tools.MailingEngine;

public class EmailSendingTest {
	private MailingEngine mEngine;
	private UsersServices uServices;
	
	@Before
	public void setUp() {
		mEngine = MailingEngine.getInstance();
		uServices = new UsersServicesImpl();
	}
	
	@Ignore
	public void testEmail() {
		UserBean user = new UserBean("slimwhity@gmail.com", "password");
		
		if (uServices.createUser(user)) {
			mEngine.sendUserCreationConfirmation(user);
		}
	}
}
