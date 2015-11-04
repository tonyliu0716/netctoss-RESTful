package org.tarena.netctoss.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.AdminMapperDao;
import org.tarena.netctoss.entity.AdminInfo;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private AdminMapperDao dao;
	public void setDao(AdminMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping(value="/login/{code}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> login(@PathVariable("code") String code, 
			@RequestHeader("name") String name, 
			@RequestHeader("password") String password, 
			HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", false);//先假设登录失败
		//先检查验证码：
		String scode = (String) session.getAttribute("checkcode");
		if( !code.equals(scode)){
			//验证码错误
			map.put("code_error", "Code was wrong!");
			return map;
		}
		AdminInfo admin = new AdminInfo();
		admin.setAdmin_code(name);
		admin.setPassword(password);
		int record = dao.findByAdminCodeAndPwd(admin);
		if(record == 1){
			map.put("login", true);	
		}else{
			map.put("error", "Username or password was not correct!");
		}
		return map;
	}
	
	@RequestMapping(value="/toIndex",method=RequestMethod.GET)
	public String toIndex(){
		return "index";
	}
}
