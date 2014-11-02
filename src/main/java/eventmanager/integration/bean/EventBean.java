package eventmanager.integration.bean;

import java.text.DateFormat;
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
	 * L'id de l'event, utilisé par l'URL.
	 */
	private Integer id;
	
	/**
	 * L'hôte de l'évenement
	 */
	private UserBean hote;
	/**
	 * Lieu de l'évenement
	 */
	private String adresse;
	/**
	 * Date de début 
	 */
	private Date datedeb;
	/**
	 * Date de fin
	 */
	private Date datefin;
	/**
	 * Visible (publié) ou non
	 */
	private boolean visible;
	/**
	 * Nom de l'évenement
	 */
	private String nom;
	/**
	 * Description de l'évenement
	 */
	private String description;
	/**
	 * Liste des participants à l'évenement
	 */
	private List<ParticipantBean> listParticipants;
	
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
	public UserBean getHote() {
		return hote;
	}
	public void setHote(UserBean hote) {
		this.hote = hote;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDatedebToString() {
		return formatDate(datedeb);
	}
	
	public String getDatefinToString() {
		return formatDate(datefin);
	}
	
	private String formatDate(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date);
	}

}
