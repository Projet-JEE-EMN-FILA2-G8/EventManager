package eventmanager.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.UserBean;

public class MailingEngine {
	private static MailingEngine singleton = new MailingEngine();

	// Constructeur privé
	private MailingEngine() {}
	
	/**
	 * @return le singleton MailingEngine
	 */
	public static MailingEngine getInstance() {
		return singleton;
	}
	
	/**
	 * Envoi un email contenant le lien de l'evenement pour confirmer sa publication 
	 * @param event l'evenement à partager
	 */
	public void sendPublishingConfirmationMail(EventBean event, String url) {
		String topic = "Confirmation publication d'événement - EventManager FILA2-G8";
		String content = "Votre événement "+ event.getNom() + " a été publié.\n\n" + 
				"Vous pouvez le retrouver dans votre espace personnel sur notre site.\n" +
				"Vous pouvez également inviter des participants en leur transmettant le lien suivant : " + url +
				"\n\n Cordialement,\n Le groupe 8.";
		sendEmail(event.getHote().getEmail(), content, topic);
	}
	
	public void sendRegisteringConfirmation() {}
	
	/**
	 * Envoi un mail de confirmation de création de compte à l'utilisateur passé en paramètre
	 * @param user le nouvel utilisateur
	 */
	public void sendUserCreationConfirmation(UserBean user) {
		String topic = "Confirmation inscription - EventManager FILA2-G8";
		String content = "Bienvenue sur EventManager FILA2-G8\n\n" + 
				"Votre compte a bien été créé avec les informations suivantes : \n" + 
				"\t - mail : " + user.getEmail() +
				"\n\t - password : " + user.getPwd() +
				"\n\n Cordialement,\n Le groupe 8.";
		sendEmail(user.getEmail(), content, topic);
	}
	
	private void sendEmail(String destinataire, String content, String topic) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",	"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		 
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("event.manager.jee.fila2","automailsender");
			}
		});
		 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("event.manager.jee.fila2@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(destinataire));
			message.setSubject(topic);
			message.setText(content);
			 
			Transport.send(message);
		} catch (MessagingException e) {}
	}
}
