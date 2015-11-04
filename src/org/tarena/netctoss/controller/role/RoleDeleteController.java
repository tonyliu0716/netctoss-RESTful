package org.tarena.netctoss.controller.role;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.RoleMapperDao;

@Controller
@RequestMapping("/role")
public class RoleDeleteController {
	@Resource
	private RoleMapperDao dao;
	@RequestMapping(value="/role_delete/{id}",method=RequestMethod.DELETE)
	public String toDelete(@PathVariable("id") Integer id){
		int total = dao.countAll();
		dao.deleteOneRole(id);
		dao.deleteOneRP(id);
		System.out.println("id: " + id);
		System.out.println("total: " + total);
		if(total - id > 0){
			for(int i = id; i < total; i++){
				System.out.println("***** " + i + " *****");
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("old_id", i);
				map.put("new_id", i + 1);
				dao.updateRole(map);
				Map<String, Integer> map1 = new HashMap<String, Integer>();
				map1.put("old_id", i);
				map1.put("new_id", i + 1);
				dao.updateRp(map1);
				System.out.println("I am the middle one!");
			}
			return "redirect:/nectoss/role/role_list/1";
		}else{
			return "redirect:/netctoss/role/role_list/1";
		}
		
		
	}
}
