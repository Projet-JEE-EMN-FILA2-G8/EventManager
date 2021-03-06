/*
 * Created on 27 oct. 2014 ( Time 17:22:42 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a composite Primary Key  


package eventmanager.business.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;

/**
 * Persistent class for entity stored in table "REGISTRATION"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="REGISTRATION", schema="APP" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="RegistrationEntity.countAll", query="SELECT COUNT(x) FROM RegistrationEntity x" )
} )
public class RegistrationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private RegistrationEntityKey compositePrimaryKey ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public RegistrationEntity() {
		super();
		this.compositePrimaryKey = new RegistrationEntityKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdevent( Integer idevent ) {
        this.compositePrimaryKey.setIdevent( idevent ) ;
    }
    public Integer getIdevent() {
        return this.compositePrimaryKey.getIdevent() ;
    }
    public void setIdparticipant( Integer idparticipant ) {
        this.compositePrimaryKey.setIdparticipant( idparticipant ) ;
    }
    public Integer getIdparticipant() {
        return this.compositePrimaryKey.getIdparticipant() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        return sb.toString(); 
    } 

}
