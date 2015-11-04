package org.tarena.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.AdminMapperDao;
import org.tarena.netctoss.entity.AdminInfo;
import org.tarena.netctoss.entity.Role;
import org.tarena.netctoss.vo.AdminInfoPage;

public class TestAdminDao {
	public static void main(String[] args){
		String conf = "org/tarena/netctoss/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		AdminMapperDao dao = ac.getBean("adminMapperDao", AdminMapperDao.class);
		AdminInfoPage page = new AdminInfoPage();
		page.setPriId(-1);
		page.setRolename("*");
		List<AdminInfo> list = dao.findByCondition(page);
		for(AdminInfo a : list){
			
			String rolename = "";
			for(Role r : a.getRoles()){
				rolename += r.getName() + ",";
			}
			System.out.println(a.getAdmin_code() + " " + a.getId() + " " + a.getEmail() + " " + rolename);
		}
	}
}
