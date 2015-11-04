package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.CostMapperDao;

@Controller
@RequestMapping("/fee")
public class FeeDeleteController {
	@Resource
	private CostMapperDao mapperDao;
	public void setMapperDao(CostMapperDao mapperDao) {
		this.mapperDao = mapperDao;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public boolean execute(@PathVariable("id") Integer id){
		if(id != null){
			mapperDao.deleteByID(id);
			String s = null;
			s.length();
		}
		return true;
	}
}
