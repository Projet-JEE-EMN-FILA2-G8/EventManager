package eventmanager.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.persistence.PersistenceConfig;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.EventsPersistence;
import eventmanager.business.persistence.services.ParticipantsPersistence;
import eventmanager.business.persistence.services.UsersPersistence;
import eventmanager.integration.EventsServices;
import eventmanager.integration.UsersServices;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.integration.impl.EventsServicesImpl;
import eventmanager.integration.impl.UsersServicesImpl;
import eventmanager.tools.BeanConverter;


public class EventsServicesTest {
	
	private static final String PASSWORD = "password";
	private static final String HOST_GET = "test_get@gmail.com";
	private static final String HOST_EMAIL = "host@gmail.com";
	private List<EventBean> eventsToDelete;
	private EventsServices eventsServices;
	
	@Before
	public void setUp() {
		eventsServices = new EventsServicesImpl();
		eventsToDelete = new ArrayList<EventBean>();
	}
	
	@Test
	public void testCreateEvent() {
		EventBean eventBean = new EventBean();
		UserBean host = new UserBean();
		host.setEmail(HOST_EMAIL);
		host.setPwd(PASSWORD);
		
		UsersServices userService = new UsersServicesImpl();
		userService.createUser(host);
		
		eventBean.setNom("Event Test Insertion");
		eventBean.setDatedeb(new Date());
		eventBean.setDatefin(new Date());
		eventBean.setAdresse("Eclipse foundation");
		eventBean.setHote(host);
		eventBean.setVisible(false);
		eventBean.setListParticipants(new ArrayList<ParticipantBean>());
		
		EventBean insertedBean = eventsServices.createEvent(eventBean);
		assertNotNull(insertedBean);
		eventsToDelete.add(insertedBean);
		assertTrue("Hôte enregistré", eventBean.getHote().equals(insertedBean.getHote()));
		assertNotNull(eventsServices.getHostedEvents(host));
		
	}
	
	@Test
	public void testRegisterParticipant() {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		EventsEntity eventToRegister = new EventsEntity();
		eventToRegister.setDatedeb(new Date());
		eventToRegister.setDatefin(new Date());
		eventToRegister.setNom("Event Test");
		eventToRegister.setAdresse("Eclipse");
		
		UsersEntity host = new UsersEntity();
		host.setEmail("test_reg@gmail.com");
		host.setPwd(PASSWORD);
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		host = daoUsers.save(host);
		eventToRegister.setUsers(host);
		eventToRegister.setVisible((short) 1);
		
		eventToRegister = daoEvents.save(eventToRegister);
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventBean.class);
		
		EventBean convertedBean = (EventBean) converter.convert(eventToRegister, EventBean.class);

		ParticipantBean participant = new ParticipantBean();
		participant.setEmail("mail@mail.com");
		participant.setNom("Test");
		participant.setPrenom("test");
		participant.setSociete("test");
		
		eventsServices.registerParticipant(convertedBean, participant);
		
		EventsEntity createdEvent = daoEvents.load(convertedBean.getId());
		eventsToDelete.add((EventBean) converter.convert(createdEvent, EventBean.class));
		
		assertNotNull(createdEvent);
		assertNotNull(createdEvent.getListOfParticipants());
		assertEquals("Participant enregistré", participant.getNom(), createdEvent.getListOfParticipants().get(0).getNom());
	}
	
	@Test
	public void testGetHostedEvents() {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		EventsEntity eventToRegister = new EventsEntity();
		eventToRegister.setDatedeb(new Date());
		eventToRegister.setDatefin(new Date());
		eventToRegister.setNom("Event Test");
		eventToRegister.setAdresse("Eclipse");
		
		UsersEntity host = new UsersEntity();
		host.setEmail(HOST_GET);
		host.setPwd(PASSWORD);
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		host = daoUsers.save(host);
		eventToRegister.setUsers(host);
		eventToRegister.setVisible((short) 1);
		
		eventToRegister = daoEvents.save(eventToRegister);
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventBean.class);
		
		EventBean convertedBean = (EventBean) converter.convert(eventToRegister, EventBean.class);

		ParticipantBean participant = new ParticipantBean();
		participant.setEmail("mail2@mail.com");
		participant.setNom("Test");
		participant.setPrenom("test");
		participant.setSociete("test");
		
		eventsServices.registerParticipant(convertedBean, participant);
		
		EventsEntity createdEvent = daoEvents.load(convertedBean.getId());
		eventsToDelete.add((EventBean) converter.convert(createdEvent, EventBean.class));
		
		List<EventBean> hostedEvents = eventsServices.getHostedEvents(new UserBean(HOST_GET, PASSWORD));
		assertNotNull(hostedEvents);
		assertEquals("Nombre d'events", hostedEvents.size(), 1);
		assertEquals("Participants récupérés", participant.getEmail(), hostedEvents.get(0).getListParticipants().get(0).getEmail());

	}
	
	@Test
	public void testPublishEvent() {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		EventsEntity eventToRegister = new EventsEntity();
		eventToRegister.setDatedeb(new Date());
		eventToRegister.setDatefin(new Date());
		eventToRegister.setNom("Event Test");
		eventToRegister.setAdresse("Eclipse");
		
		UsersEntity host = new UsersEntity();
		host.setEmail(HOST_GET);
		host.setPwd(PASSWORD);
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		host = daoUsers.save(host);
		eventToRegister.setUsers(host);
		eventToRegister.setVisible((short) 0);
		
		eventToRegister = daoEvents.save(eventToRegister);
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventBean.class);
		
		EventBean convertedBean = (EventBean) converter.convert(eventToRegister, EventBean.class);
		eventsToDelete.add(convertedBean);
		
		URL url = eventsServices.publishEvent(convertedBean);
		assertEquals("URL correcte", EventsServicesImpl.URL_FORMAT_EVENTMANAGER + convertedBean.getId(), url.toString());
	}
	
	@Test
	public void testGetEventByUrl() throws MalformedURLException {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		EventsEntity eventToRegister = new EventsEntity();
		eventToRegister.setDatedeb(new Date());
		eventToRegister.setDatefin(new Date());
		eventToRegister.setNom("Event Test");
		eventToRegister.setAdresse("Eclipse");
		
		UsersEntity host = new UsersEntity();
		host.setEmail(HOST_GET);
		host.setPwd(PASSWORD);
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		host = daoUsers.save(host);
		eventToRegister.setUsers(host);
		eventToRegister.setVisible((short) 0);
		
		eventToRegister = daoEvents.save(eventToRegister);
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventBean.class);
		
		EventBean convertedBean = (EventBean) converter.convert(eventToRegister, EventBean.class);
		eventsToDelete.add(convertedBean);
		
		EventBean eventByUrl = eventsServices.getEventByUrl(new URL(EventsServicesImpl.URL_FORMAT_EVENTMANAGER + convertedBean.getId()));
		assertNotNull(eventByUrl);
		assertEquals("Hôte récupé", host.getEmail(), eventByUrl.getHote().getEmail());
	}
	
	@After
	public void tearDown() {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		ParticipantsPersistence daoParticipants = PersistenceServiceProvider.getService(ParticipantsPersistence.class, PersistenceConfig.JPA);
		
		for (EventBean event : eventsToDelete) {
			daoEvents.delete(event.getId());
			if (event.getListParticipants() != null) {
				for (ParticipantBean participant : event.getListParticipants()) {
					if (participant != null) daoParticipants.delete(participant.getId());
				}
			}
			daoUsers.delete(event.getHote().getEmail());
		}
	}

}
