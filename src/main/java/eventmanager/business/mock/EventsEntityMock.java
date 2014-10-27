
/*
 * Created on 26 oct. 2014 ( Time 19:52:13 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package eventmanager.business.mock;

import java.util.LinkedList;
import java.util.List;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.mock.tool.MockValues;

public class EventsEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public EventsEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public EventsEntity createInstance( Integer id ) {
		EventsEntity entity = new EventsEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setAdresse( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setDatedeb( mockValues.nextDate() ) ; // java.util.Date 
		entity.setDatefin( mockValues.nextDate() ) ; // java.util.Date 
		entity.setVisible( mockValues.nextShort() ) ; // short 
		entity.setNom( mockValues.nextString(20) ) ; // java.lang.String 
		entity.setDescription( mockValues.nextString(50) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setListOfParticipants( TODO ) ; // List<Participants> 
		// setUsers( TODO ) ; // Users 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<EventsEntity> createList(int count) {
		List<EventsEntity> list = new LinkedList<EventsEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}