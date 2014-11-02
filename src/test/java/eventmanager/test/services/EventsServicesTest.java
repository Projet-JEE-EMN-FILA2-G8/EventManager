package eventmanager.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
		
		EventBean insertedBean = eventsServices.createOrUpdateEvent(eventBean);
		assertNotNull(insertedBean);
		eventsToDelete.add(insertedBean);
		assertTrue("Hôte enregistré", eventBean.getHote().equals(insertedBean.getHote()));
		assertEquals("Dates enregistrées", eventBean.getDatedeb(), insertedBean.getDatedeb());
		assertEquals("Dates enregistrées", eventBean.getDatefin(), insertedBean.getDatefin());
		assertEquals("Nom enregistré", eventBean.getNom(), insertedBean.getNom());
		assertNotNull(eventsServices.getHostedEvents(host));
		
	}
	
	@Test
	public void testRegisterParticipant() {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		// Event n°1
		EventsEntity eventToRegister = new EventsEntity();
		eventToRegister.setDatedeb(new Date());
		eventToRegister.setDatefin(new Date());
		eventToRegister.setNom("Event Test");
		eventToRegister.setAdresse("Eclipse");
		
		// Event n°2
		EventsEntity eventToRegister2 = new EventsEntity();
		eventToRegister2.setDatedeb(new Date());
		eventToRegister2.setDatefin(new Date());
		eventToRegister2.setNom("Event Test n°2");
		eventToRegister2.setAdresse("Eclipse");
		
		UsersEntity host = new UsersEntity();
		host.setEmail("test_reg@gmail.com");
		host.setPwd(PASSWORD);
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		host = daoUsers.save(host);
		eventToRegister.setUsers(host);
		eventToRegister.setVisible((short) 1);
		eventToRegister2.setUsers(host);
		eventToRegister2.setVisible((short) 1);
		
		eventToRegister = daoEvents.save(eventToRegister);
		eventToRegister2 = daoEvents.save(eventToRegister2);
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventBean.class);
		
		EventBean convertedBean = (EventBean) converter.convert(eventToRegister, EventBean.class);
		EventBean convertedBean2 = (EventBean) converter.convert(eventToRegister2, EventBean.class);

		ParticipantBean participant = new ParticipantBean();
		participant.setEmail("mail@mail.com");
		participant.setNom("Test");
		participant.setPrenom("test");
		participant.setSociete("test");
		
		// participant registration to both events 
		eventsServices.registerParticipant(convertedBean, participant);
		eventsServices.registerParticipant(convertedBean2, participant);
		
		// Retrieving events from database
		EventsEntity createdEvent = daoEvents.load(convertedBean.getId());
		EventsEntity createdEvent2 = daoEvents.load(convertedBean2.getId());
		eventsToDelete.add((EventBean) converter.convert(createdEvent, EventBean.class));
		eventsToDelete.add((EventBean) converter.convert(createdEvent2, EventBean.class));
		
		assertNotNull(createdEvent);
		assertNotNull(createdEvent2);
		assertNotNull(createdEvent.getListOfParticipants());
		assertNotNull(createdEvent2.getListOfParticipants());
		try {
			assertEquals("Participant enregistré", participant.getNom(), createdEvent.getListOfParticipants().get(0).getNom());
			assertEquals("Participant enregistré", participant.getEmail(), createdEvent2.getListOfParticipants().get(0).getEmail());
		} catch (IndexOutOfBoundsException e) {
			fail("Participants non présents dans la liste des participants");
		}
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
		
		assertTrue(eventsServices.publishEvent(convertedBean));
	}
	
	@Test
	public void testGetEventById() {
		EventsPersistence daoEvents = PersistenceServiceProvider.getService(EventsPersistence.class, PersistenceConfig.JPA);
		EventBean eventToRegister = new EventBean();
		eventToRegister.setDatedeb(new Date());
		eventToRegister.setDatefin(new Date());
		eventToRegister.setNom("Event Test getById");
		eventToRegister.setAdresse("Eclipse");

		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventBean.class);
		converter.register(new BeanConverter(), EventsEntity.class);
		converter.register(new BeanConverter(), UserBean.class);
		converter.register(new BeanConverter(), UsersEntity.class);
		
		UsersEntity host = new UsersEntity();
		host.setEmail(HOST_GET);
		host.setPwd(PASSWORD);
		
		UsersPersistence daoUsers = PersistenceServiceProvider.getService(UsersPersistence.class, PersistenceConfig.JPA);
		daoUsers.save(host);
		eventToRegister.setHote(new UserBean(HOST_GET, PASSWORD));
		eventToRegister.setVisible(true);
		
		EventsEntity eventRegistered = daoEvents.save((EventsEntity) converter.convert(eventToRegister, EventsEntity.class));
		
		EventBean convertedBean = (EventBean) converter.convert(eventRegistered, EventBean.class);
		eventsToDelete.add(convertedBean);
		
		EventBean eventByUrl = eventsServices.getEventById(convertedBean.getId());
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
					if (participant != null) {
						try {
							daoParticipants.delete(participant.getId());
						} catch (Exception e) {
							System.out.println("Exception suppression participant - Contrainte de clé");
						}
					}
				}
			}
			try {
				daoUsers.delete(event.getHote().getEmail());
			} catch (Exception e) {
				System.out.println("Exception suppression user - Contrainte de clé");
			}
		}
	}

}
