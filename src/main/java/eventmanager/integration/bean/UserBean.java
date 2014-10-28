package eventmanager.integration.bean;


/**
 * @author Adrian
 * @ejb.bean
 *   name=UserBean
 *   description=Bean de la couche service représentant un utilisateur
 *
 */
public class UserBean extends AbstractBean {

	/**
	 * Version ID pour la serialization 
	 */
	private static final long serialVersionUID = -5094083070884521177L;
	
	private String email;
	private String pwd;
	
	public UserBean() {}
	
	/**
	 * @param email l'email de l'user
	 * @param pwd le password de l'user
	 */
	public UserBean(String email, String pwd) {
		setEmail(email);
		setPwd(pwd);
	}
	
	/**
	 * @return l'email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email email à setter
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return le password
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd le pwd à setter
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * @param userBean l'UserBean à comparer
	 * @return true si les users ont le même email et le même password
	 */
	public boolean equals(UserBean userBean) {
		return this.getEmail().equals(userBean.getEmail()) && 
				this.getPwd().equals(userBean.getPwd());
	}

}
