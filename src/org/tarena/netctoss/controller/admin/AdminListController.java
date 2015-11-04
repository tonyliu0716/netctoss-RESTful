package org.tarena.netctoss.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tarena.netctoss.dao.AdminMapperDao;
import org.tarena.netctoss.entity.AdminInfo;
import org.tarena.netctoss.vo.AdminInfoPage;

@Controller
@RequestMapping("/admin")
public class AdminListController {
	@Resource
	private AdminMapperDao dao;
	
	@RequestMapping("/admin_list/{priId}/{rolename}")
	public String execute(@PathVariable("priId") Integer priId, @PathVariable("rolename") String rolename, Model model){
		AdminInfoPage page = new AdminInfoPage();
		page.setPriId(priId);
		page.setRolename(rolename);
		List<AdminInfo> list = dao.findByCondition(page);
		model.addAttribute("admins", list);
		Integer rows = list.size();
		Integer pageSize = 3;
		int totalPage = 0;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("page", totalPage);
		return "admin/admin_list";
		
	}
}
