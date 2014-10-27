package eventmanager.test.services;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.UsersPersistence;
import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.UsersServicesImpl;

public class UsersServicesTest {
	
	private static final String CREATED_EMAIL = "test@email.com";
	private static final String INSERTED_PWD = "password";
	private static final String INSERTED_MAIL = "abdel@gmail.com";
	private static final String UPDATED_PWD = "password_updated";
	private static final String DELETED_MAIL = "deleted@gmail.com";
	private static final String AUTHENTICATE_MAIL = "bon_jovi@gmail.com";
	private static final String AUTHENTICATE_PWD = "itsmylife";
	
	/**
	 * The service to test
	 */
	private UsersServices service;

	/**
	 * Launched once before
	 */
	@BeforeClass
	public static void setUpClass() {
		// Inserts utiles pour certains tests
		UsersPersistence dao = PersistenceServiceProvider.getService(UsersPersistence.class);
		UsersEntity inserted = new UsersEntity();
		inserted.setEmail(INSERTED_MAIL);
		inserted.setPwd(INSERTED_PWD);
		dao.insert(inserted);
		
		inserted.setEmail(DELETED_MAIL);
		inserted.setPwd(INSERTED_PWD);
		dao.insert(inserted);
		
		inserted.setEmail(AUTHENTICATE_MAIL);
		inserted.setPwd(AUTHENTICATE_PWD);
		dao.insert(inserted);
	}
	
	@Before
	public void setUp() {
		service = new UsersServicesImpl();
	}
	
	@Test
	public void testCreateUsers() {
		UserBean user = new UserBean(CREATED_EMAIL, INSERTED_PWD);
		assertTrue(service.createUser(user));
	}
	
	@Test
	public void testAuthenticateUser() {
		UserBean user = new UserBean(AUTHENTICATE_MAIL, AUTHENTICATE_PWD);
		assertTrue(service.authenticateUser(user));
		assertFalse(service.authenticateUser(new UserBean("fake@mail.com", "fakePwd")));
	}
	
	@Test
	public void testUpdatePwd() {
		UserBean user = new UserBean(INSERTED_MAIL, UPDATED_PWD);
		assertTrue(service.updatePwd(user));
		assertEquals("Pwd updated", UPDATED_PWD, PersistenceServiceProvider.getService(UsersPersistence.class).load(INSERTED_MAIL).getPwd());
	}
	
	@Test
	public void testDeleteUser() {
		UserBean user = new UserBean(DELETED_MAIL, INSERTED_MAIL);
		assertTrue(service.deleteUser(user));
		assertNull(PersistenceServiceProvider.getService(UsersPersistence.class).load(DELETED_MAIL));
	}
	
	/**
	 * Launched once after
	 */
	@AfterClass
	public static void tearDownClass() {
		PersistenceServiceProvider.getService(UsersPersistence.class).delete(CREATED_EMAIL);
		PersistenceServiceProvider.getService(UsersPersistence.class).delete(INSERTED_MAIL);
		PersistenceServiceProvider.getService(UsersPersistence.class).delete(AUTHENTICATE_MAIL);
	}
	
	
}
