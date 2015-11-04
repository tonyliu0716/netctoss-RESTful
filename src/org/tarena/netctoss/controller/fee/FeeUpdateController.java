package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeeUpdateController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}

	//页面发出/fee/{id}/toedit
	@RequestMapping(value="/{id}/toedit/{page}",method=RequestMethod.GET)
	public String update(@PathVariable("id") Integer id,@PathVariable("page") Integer page, Model model){
		Cost cost = dao.findById(id);
		model.addAttribute("cost", cost);
		model.addAttribute("page", page);
		return "fee/fee_modi";
	}
	
	//页面修改资费列表详细完毕后，点击保存进入到fee/list/{page}，返回刚更新过的页面
	//页面发出的请求是/fee/{id}/{page}
	@RequestMapping(value="/{id}/{page}",method=RequestMethod.PUT)
	public String update(Cost cost,@PathVariable(value="page") Integer page){
		dao.updateCost(cost);
		return "redirect:/fee/list/" + page;
	}
	
	//页面发出/netctoss/fee/start/" + id ，进入到Controller
	//使用AJAX发送PUT请求，Controller进行更新
	@RequestMapping(value="/start/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public boolean startCost(@PathVariable("id") Integer id){
		dao.updateStatus(id);
		return true;
	}
	
}
