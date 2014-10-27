/*
 * Created on 26 oct. 2014 ( Time 19:52:13 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package eventmanager.business.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.persistence.commons.jpa.GenericJpaService;
import eventmanager.business.persistence.commons.jpa.JpaOperation;
import eventmanager.business.persistence.services.EventsPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Events" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class EventsPersistenceJPA extends GenericJpaService<EventsEntity, Integer> implements EventsPersistence {

	/**
	 * Constructor
	 */
	public EventsPersistenceJPA() {
		super(EventsEntity.class);
	}

	@Override
	public EventsEntity load( Integer id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Integer id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(EventsEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getId() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("EventsEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}