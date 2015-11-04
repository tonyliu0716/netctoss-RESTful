package org.tarena.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.ServiceMapperDao;
import org.tarena.netctoss.entity.ServicePage;
import org.tarena.netctoss.vo.ServiceLine;

public class TestServiceDao {
	public static void main(String[] args){
		String conf = "org/tarena/netctoss/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		ServiceMapperDao dao = ac.getBean("serviceMapperDao", ServiceMapperDao.class);
		ServicePage page = new ServicePage();
		page.setIdcardno("*");
		page.setOsusername("*");
		page.setStatus("-1");
		page.setUnixhost("*");
		List<ServiceLine> list = dao.findByCondition(page);
		for(ServiceLine s : list){
			System.out.println(s.getIdcard_no() + " " + s.getOs_username() + " " + s.getCost_name() + " " + s.getCost_descr());
		}
	}
}
