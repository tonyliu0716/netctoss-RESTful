package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeeAddController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	//对应/fee/toadd
	@RequestMapping(value="/toadd",method=RequestMethod.GET)
	public String toAdd(){
		return "fee/fee_add";
	}
	
	//对应/fee/add请求.将表单参数封装成Cost对象传入。
	//要求表单中组件的name属性与Cost中的属性名一致！
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@Transactional
	public String add(Cost c){
		int pageSize = 3;
		int page = 0;
		//调用dao.save()
		dao.save(c);
		int rows = dao.countCost();
		System.out.println(rows);
		if(rows % pageSize == 1){
			page = rows / pageSize + 1;
		}else if(rows % pageSize == 2){
			page = rows / pageSize + 1;
		}else{
			page = rows / pageSize;
		}
		return "redirect:/fee/list/"+page;
	}
	
}
