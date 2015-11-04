package org.tarena.netctoss.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.RoleMapperDao;

import org.tarena.netctoss.entity.Role;

@Controller
@RequestMapping("/role")
public class RoleListController {
	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/role_list/{page}")
	public String execute(@PathVariable("page") Integer page, Model model){
		int pageSize = 3;
		Integer id = (page - 1) * pageSize + 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", id);
		map.put("end", id + pageSize - 1);
		List<Role> list = dao.findAll(map);
		Integer rows = dao.countAll();
		
		if(rows % pageSize == 0){
			rows = rows / pageSize;
		}else{
			rows = rows / pageSize + 1;
		}
		int totalPage = rows;
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("roles", list);
		return "role/role_list";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	//return 普通的布尔值
	public boolean f0(){
		return true;
	}
	
	@RequestMapping("/test1")
	@ResponseBody
	//return一个Map数据，以JSON字符串格式输出到页面
	public Map<String, Object> f1(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1001);
		map.put("name", "Tom");
		return map;
	}
	
	
	
	//return从DAO中查询的值，以JSON字符串格式输出到页面
//	public List<Role> f2(){
//		//List<Role> list = dao.findAll(3);
//		//return list;
//	}
}
