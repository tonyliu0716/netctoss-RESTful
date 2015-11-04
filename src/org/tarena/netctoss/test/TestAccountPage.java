package org.tarena.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.AccountMapperDao;
import org.tarena.netctoss.entity.Account;
import org.tarena.netctoss.entity.AccountPage;

public class TestAccountPage {
	public static void main(String[] args){
		String conf = "org/tarena/netctoss/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		AccountMapperDao dao = ac.getBean("accountMapperDao", AccountMapperDao.class);
		AccountPage page = new AccountPage();
		page.setStatus("-1");
		page.setLogin_name("dgbf70");
		List<Account> list = dao.findByCondition(page);
		for(Account a : list){
			System.out.println(a.getId() + " " + a.getLogin_name());
		}
	}
}
