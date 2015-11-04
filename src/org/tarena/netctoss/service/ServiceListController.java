package org.tarena.netctoss.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tarena.netctoss.dao.ServiceMapperDao;
import org.tarena.netctoss.entity.ServicePage;
import org.tarena.netctoss.vo.ServiceLine;

@Controller
@RequestMapping("/service")
public class ServiceListController {
	@Resource
	private ServiceMapperDao dao;
	
	@RequestMapping(value="service_list/{osusername}/{unixhost}/{idcardno}/{status}", method=RequestMethod.GET)
	public String execute(@PathVariable("osusername") String osusername, @PathVariable("unixhost") String unixhost,
			@PathVariable("idcardno") String idcardno, @PathVariable("status") String status, Model model){
		ServicePage page = new ServicePage();
		page.setIdcardno(idcardno);
		page.setOsusername(osusername);
		page.setStatus(status);
		page.setUnixhost(unixhost);
		List<ServiceLine> list = dao.findByCondition(page);
		model.addAttribute("services", list);
		return "service/service_list";
	}
}
