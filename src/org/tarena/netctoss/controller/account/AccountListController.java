package org.tarena.netctoss.controller.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tarena.netctoss.dao.AccountMapperDao;
import org.tarena.netctoss.entity.Account;
import org.tarena.netctoss.entity.AccountPage;

@Controller
@RequestMapping("/account")
public class AccountListController {
	@Resource
	private AccountMapperDao dao;
	
	@RequestMapping(value="/account_list/{page}",method=RequestMethod.GET)
	public String execute(@PathVariable("page") Integer page, Model model){
		int pageSize = 3;
		AccountPage account = new AccountPage();
		account.setStatus("-1");
		int start = (page - 1) * 3;
		List<Account> list = dao.findByPage(start);
		int rows = dao.countAll();
		int totalPage = 0;
		if(rows % pageSize != 0){
			totalPage = rows / pageSize + 1;
		}else{
			totalPage = rows / pageSize;
		}
		model.addAttribute("account", account);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("accounts", list);
		model.addAttribute("flag", false);
		return "account/account_list";
	}
	
	@RequestMapping(value="/account_list/{page}/{idcard_no}/{real_name}/{login_name}/{status}",
			method=RequestMethod.GET)
	public String findCondition(
			@PathVariable("page") Integer page,
			@PathVariable("idcard_no") String idcard_no,
			@PathVariable("real_name") String real_name,
			@PathVariable("login_name") String login_name,
			@PathVariable("status") String status, Model model){
		
		int pageSize = 3;
		AccountPage account = new AccountPage();
		account.setIdcard_no(idcard_no);
		account.setLogin_name(login_name);
		account.setReal_name(real_name);
		account.setStatus(status);
		int start = (page - 1) * pageSize;
		account.setPage(start);
		List<Account> list = dao.findByCondition(account);
		model.addAttribute("accounts", list);
		int rows = dao.countByCondition(account);
		
		account.setIdcard_no(convert(account.getIdcard_no()));
		account.setLogin_name(convert(account.getLogin_name()));
		account.setReal_name(convert(account.getReal_name()));
		
		int totalPage = 0;
		if(rows % pageSize == 0){
			totalPage = rows / pageSize;
		}else{
			totalPage = rows / pageSize + 1;
		}
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("account", account);
		model.addAttribute("page", page);
		model.addAttribute("flag", true);
		return "account/account_list";
	}
	
	public String convert(String s){
		if("*".equals(s)){
			return "";
		}else{
			return s;
		}
	}
}
