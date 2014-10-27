package eventmanager.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.junit.Test;

import eventmanager.business.bean.jpa.EventsEntity;
import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.integration.bean.EventBean;
import eventmanager.integration.bean.ParticipantBean;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class ConvertBeanTest {
	
	@Test
	public void testUserBean() {
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), UsersEntity.class);
		UserBean bean = new UserBean("test@mail.com", "password");
		UsersEntity convertedBean = (UsersEntity) converter.convert(bean, UsersEntity.class);
		
		assertNotNull(convertedBean);
		assertEquals("mail attribute copy OK", bean.getEmail(), convertedBean.getEmail());
		assertEquals("pwd attribute copy OK", bean.getPwd(), convertedBean.getPwd());
	}		ConvertUtilsBean converter = new ConvertUtilsBean();
	
	@Test
	public void testEventBean() {
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), EventsEntity.class);
		ParticipantBean p1 = new ParticipantBean();
		p1.setNom("Fraisse");
		p1.setPrenom("Adrian");
		p1.setEmail("adrian.fraisse@gmail.com");
		p1.setSociete("Atos");
		
		ParticipantBean p2 = new ParticipantBean();
		p2.setNom("Gerard");
		p2.setPrenom("Hadrien");
		p2.setEmail("hadrien.gerard@etudiant.mines-nantes.fr");
		p2.setSociete("Sopra");
		
		List<ParticipantBean> listBeans = new ArrayList<ParticipantBean>();
		listBeans.add(p1);
		listBeans.add(p2);
		
		EventBean eventBean = new EventBean();
		eventBean.setNom("Tests Unitaires");
		eventBean.setAdresse("Eclipse");
		eventBean.setDatedeb(new Date());
		eventBean.setDatefin(new Date());
		eventBean.setVisible(false);
		eventBean.setDescription("Les tests unitaires c'est bon pour la santé");
		eventBean.setListParticipants(listBeans);
		
		assertNotNull(converter.convert(eventBean));
	}
}
