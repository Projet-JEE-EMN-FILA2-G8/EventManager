/*
 * Created on 27 oct. 2014 ( Time 17:22:43 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package eventmanager.test.persistence;


import org.junit.Assert;
import org.junit.Test;

import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.business.mock.UsersEntityMock;
import eventmanager.business.persistence.PersistenceServiceProvider;
import eventmanager.business.persistence.services.UsersPersistence;

/**
 * JUnit test case for Users persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class UsersPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		UsersPersistence service = PersistenceServiceProvider.getService(UsersPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Users persistence : delete + load ..." );
		
		UsersPersistence service = PersistenceServiceProvider.getService(UsersPersistence.class);
		
		UsersEntityMock mock = new UsersEntityMock();
		
		process( service, mock, "A"  );
		// process( service, mock, ... );
	}

	private void process(UsersPersistence service, UsersEntityMock mock, String email ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		UsersEntity entity = service.load( email );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( email ) ;
			Assert.assertNotNull(entity);

			// No reference : insert is possible 
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );

			System.out.println(" . delete : " );
			boolean deleted = service.delete( email );
			System.out.println("   deleted = " + deleted);
			Assert.assertTrue(deleted) ;
		}		
	}
}
