package eventmanager.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.junit.Test;

import eventmanager.business.bean.jpa.UsersEntity;
import eventmanager.integration.bean.UserBean;
import eventmanager.tools.BeanConverter;

public class ConverterUtilsTest {

	@Test
	public void test() {
		UserBean bean = new UserBean("test@mail.com", "password");
		ConvertUtilsBean converter = new ConvertUtilsBean();
		converter.register(new BeanConverter(), UsersEntity.class);
		UsersEntity convertedBean = (UsersEntity) converter.convert(bean, UsersEntity.class);
		
		assertNotNull(convertedBean);
		assertEquals("mail attribute copy OK", bean.getEmail(), convertedBean.getEmail());
		assertEquals("pwd attribute copy OK", bean.getPwd(), convertedBean.getPwd());
	}

}
