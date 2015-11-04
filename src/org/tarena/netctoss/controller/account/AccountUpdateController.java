package org.tarena.netctoss.controller.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tarena.netctoss.dao.AccountMapperDao;

@Controller
@RequestMapping("/account")
public class AccountUpdateController {
	@Resource
	private AccountMapperDao dao;
	
	@RequestMapping("account_update")
	public String updateStatus(){
		
		return "";
	}
}
