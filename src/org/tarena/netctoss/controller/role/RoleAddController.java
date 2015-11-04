package org.tarena.netctoss.controller.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.RoleMapperDao;
import org.tarena.netctoss.entity.Role;
import org.tarena.netctoss.entity.Role_privilege;

@Controller
@RequestMapping("/role")
public class RoleAddController {
	@Resource
	private RoleMapperDao dao;
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String execute(){
		return "role/role_add";
	}
	
	@RequestMapping("/role_add")
	public String toAdd(@RequestParam(value="name") String name, @RequestParam(value="checkName") List<String> checkes){
		int id = dao.countAll() + 1;
		Role r = new Role();
		r.setId(id);
		r.setName(name);
		dao.insertOne(r);
		for(int i = 0; i < checkes.size(); i++){
			System.out.println(checkes.get(i));
			Role_privilege p = new Role_privilege();
			p.setRole_id(id);
			p.setPrivilege_id(Integer.parseInt(checkes.get(i) ));
			dao.insertPrivilege(p);
		}
		return "redirect:/netctoss/role/role_list/1";
		
	}
}
