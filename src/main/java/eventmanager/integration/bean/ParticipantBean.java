package eventmanager.integration.bean;


public class ParticipantBean extends AbstractBean {

	/**
	 * SerialID
	 */
	private static final long serialVersionUID = -4872909996952632899L;
	
	/**
	 * id en base du participant - Pose problème : l'email n'est pas considéré comme clé primaire
	 * TODO : Peut-être devrions nous supprimer cet id pour utiliser l'email.
	 */
	private Integer id;
	/**
	 * 
	 */
	private String nom;
	/**
	 * 
	 */
	private String prenom;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String societe;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
