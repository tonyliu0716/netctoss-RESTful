package org.tarena.netctoss.controller.fee;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeeSortController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	
	private int pageSize = 3;
	
	//对应请求：monthlySortDesc/sort_desc/monthly_desc/${page}
	@RequestMapping(value="/monthlySortDesc/{jsClass}/{type}/{page}",method=RequestMethod.GET)
	public String monthlySortDesc(@PathVariable("jsClass") String jsClass, @PathVariable("type") String type,@PathVariable("page") Integer page,Model model){
		//用于JS控制class
		model.addAttribute("jsClass", jsClass);
		//开关，用于判断页面是否点击了排序，如果点击了排序，flag就是false，否则是true
		model.addAttribute("flag", false);
		List<Cost> list = dao.monthly( (page - 1) * pageSize );
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		int rows = dao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("type", type);
		return "fee/fee_list";
	}
	
	//对应请求："fee/monthly_sort/sort_asc/monthly_asc"
	@RequestMapping(value="/monthly_sortAsc/{jsClass}/{type}/{page}",method=RequestMethod.GET)
	public String monthlySortAsc(@PathVariable("jsClass") String jsClass, @PathVariable("type") String type,@PathVariable("page") Integer page, Model model){
		List<Cost> list = dao.monthlySortAsc((page - 1) * pageSize);
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		model.addAttribute("jsClass", jsClass);
		model.addAttribute("flag", false);
		int rows = dao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("type", type);
		return "fee/fee_list";
	}
	// 对应请求/netctoss/fee/base_sortAsc/sort_asc/base_asc/${page}
	@RequestMapping(value="/base_sortAsc/{jsClass}/{type}/{page}",method=RequestMethod.GET)
	public String baseSortAsc(@PathVariable("jsClass") String jsClass,@PathVariable("type") String type,@PathVariable("page") Integer page, Model model ){
		List<Cost> list = dao.baseSortAsc((page - 1) * pageSize);
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		model.addAttribute("jsClass", jsClass);
		model.addAttribute("flag", false);
		int rows = dao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("type", type);
		return "fee/fee_list";
	}
	
	//对应请求： /netctoss/fee/base_sortDesc/sort_desc/base_desc/${page}
	@RequestMapping(value="/base_sortDesc/{jsClass}/{type}/{page}",method=RequestMethod.GET)
	public String baseSortDesc(@PathVariable("jsClass") String jsClass,@PathVariable("type") String type,@PathVariable("page") Integer page, Model model ){
		List<Cost> list = dao.baseSortDesc((page - 1) * pageSize);
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		model.addAttribute("jsClass", jsClass);
		model.addAttribute("flag", false);
		int rows = dao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("type", type);
		return "fee/fee_list";
	}
	//对应请求  /netctoss/fee/dura_sortAsc/sort_asc/duration_asc/${page}
	@RequestMapping("/dura_sortAsc/{jsClass}/{type}/{page}")
	public String durationSortAsc(@PathVariable("jsClass") String jsClass,@PathVariable("type") String type,@PathVariable("page") Integer page, Model model ){
		List<Cost> list = dao.durationSortAsc( (page - 1) * pageSize);
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		model.addAttribute("jsClass", jsClass);
		model.addAttribute("flag", false);
		int rows = dao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("type", type);
		return "fee/fee_list";
	}
	
	//  /netctoss/fee/dura_sortDesc/sort_desc/duration_desc/${page}
	@RequestMapping("/dura_sortDesc/{jsClass}/{type}/{page}")
	public String durationSortDesc(@PathVariable("jsClass") String jsClass, @PathVariable("type") String type,@PathVariable("page") Integer page, Model model ){
		List<Cost> list = dao.durationSortDesc( (page - 1) * pageSize);
		model.addAttribute("costs", list);
		model.addAttribute("page", page);
		model.addAttribute("jsClass", jsClass);
		model.addAttribute("flag", false);
		int rows = dao.countCost();
		model.addAttribute("count", rows);
		int totalPage = 1;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("type", type);
		return "fee/fee_list";
	}
}
