package org.tarena.netctoss.controller.fee;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
@Transactional(readOnly=true)
public class FeeListController {
	//生成的实例向CostMapperDao接口注入
	@Resource
	private CostMapperDao mapperDao;
	public void setMapperDao(CostMapperDao mapperDao) {
		this.mapperDao = mapperDao;
	}
	
	private int pageSize = 3;
	
	//对应/fee/list/1请求，显示第一页
	@RequestMapping(value="/list/{page}", method=RequestMethod.GET)
	public String execute(@PathVariable("page") Integer page, Model model){
		List<Cost> list = mapperDao.findPage( (page - 1) * pageSize );
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		int rows = mapperDao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("jsClass", "sort_asc");
		model.addAttribute("flag", true);
		return "fee/fee_list";
	}
	
}
