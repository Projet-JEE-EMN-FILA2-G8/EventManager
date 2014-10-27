package eventmanager.integration.bean;

import java.util.Date;
import java.util.List;

/**
 * @author Adrian
 *
 */
public class EventBean extends AbstractBean {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -4164191614723734898L;
	
	/**
	 * L'id de l'event, utilis� dans l'URL
	 */
	private Integer id;
	/**
	 * Lieu de l'�venement
	 */
	private String adresse;
	/**
	 * Date de d�but 
	 */
	private Date datedeb;
	/**
	 * Date de fin
	 */
	private Date datefin;
	/**
	 * Visible (publi�) ou non
	 */
	private boolean visible;
	/**
	 * Nom de l'�venement
	 */
	private String nom;
	/**
	 * Description de l'�venement
	 */
	private String description;
	/**
	 * Liste des participants � l'�venement
	 */
	private List<ParticipantBean> listParticipants;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Date getDatedeb() {
		return datedeb;
	}
	public void setDatedeb(Date datedeb) {
		this.datedeb = datedeb;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ParticipantBean> getListParticipants() {
		return listParticipants;
	}
	public void setListParticipants(List<ParticipantBean> listParticipants) {
		this.listParticipants = listParticipants;
	}

}
