package org.tarena.netctoss.controller.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.RoleMapperDao;
import org.tarena.netctoss.entity.Role;
import org.tarena.netctoss.entity.Role_privilege;

@Controller
@RequestMapping("/role")
public class RoleModifyController {
	@Resource
	private RoleMapperDao dao;
	
	@RequestMapping("/toModify/{id}/{page}")
	public String modify(@PathVariable("id") String id, @PathVariable("page") Integer page, Model model){
		Role r = dao.findById( Integer.parseInt(id) );
		model.addAttribute("role", r);
		List<Role_privilege> list = r.getPrivileges();
		List<Integer> list1 = new ArrayList<Integer>();
		for(Role_privilege p : list){
			list1.add(p.getPrivilege_id());
		}
		model.addAttribute("id", id);
		model.addAttribute("pids", list1);
		model.addAttribute("page", page);
		return "role/role_modi";
	}
	
	@RequestMapping(value="/toUpdate/{page}", method=RequestMethod.POST)
	public String toUpdate( @PathVariable("page") Integer page, @RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("privileges") String[] privileges){
		System.out.println(id);
		System.out.println(name + "*****");
		for(int i = 0; i < privileges.length; i++){
			System.out.println(privileges[i]);
		}
		
		//首先更新名字： 
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		dao.updateById(map);
		dao.deleteRP(Integer.parseInt(id));
		for(int i =0; i < privileges.length; i++){
			Role_privilege rp = new Role_privilege();
			rp.setRole_id(Integer.parseInt(id));
			rp.setPrivilege_id(Integer.parseInt(privileges[i]));
			dao.insertRP(rp);
		}
		return "redirect:../role_list/" + page;
		
	}
}
