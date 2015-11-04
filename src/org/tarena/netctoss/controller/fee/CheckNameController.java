package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.CostMapperDao;

@Controller
@RequestMapping("/fee")
public class CheckNameController {
	@Resource
	private CostMapperDao dao;
	
	@RequestMapping(value="/check/{name}",method=RequestMethod.GET)
	@ResponseBody
	public boolean check(@PathVariable("name") String name){
		System.out.println(name);
		Integer id = dao.findByName(name);
		if(id == null){
			//没有查询到记录，可以用
			return true;
		}else{
			return false;
		}
	}
}
