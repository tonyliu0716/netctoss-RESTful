package org.tarena.netctoss.controller.fee;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeePageController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}

	//假设点击第几页返回的fee/page.from?page=...
	@RequestMapping("/page")
	@Transactional(readOnly=true)
	public String page(@RequestParam(value="page",required=false) Integer page, Model model){
		List<Cost> list = dao.findPage( (page - 1) * 3);
		model.addAttribute("costs", list);
		return "redirect:/netctoss/jsp/fee/fee_list.jsp";
	}
}
