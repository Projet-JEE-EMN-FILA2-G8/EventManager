package eventmanager.test.services;

import org.junit.Before;
import org.junit.Test;

import eventmanager.integration.EventsServices;
import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.EventsServicesImpl;
import eventmanager.integration.impl.UsersServicesImpl;
import eventmanager.tools.MailingEngine;

public class EmailSendingTest {
	private MailingEngine mEngine;
	private UsersServices uServices;
	private EventsServices eServices;
	
	@Before
	public void setUp() {
		mEngine = new MailingEngine();
		uServices = new UsersServicesImpl();
		eServices = new EventsServicesImpl();
	}
	
	@Test
	public void testEmail() {
		UserBean user = new UserBean("slimwhity@gmail.com", "password");
		
		if (uServices.createUser(user)) {
			mEngine.sendUserCreationConfirmation(user);
		}
	}
}
