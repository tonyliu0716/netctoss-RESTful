package org.tarena.netctoss.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class AdminInfo implements Serializable{
	private Integer id;
	private String admin_code;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private Date enrolldate;
	//追加属性
	private List<Role> roles;
	//追加方法，JSP可以通过roleNames访问！
	public String getRoleNames(){
		String names = "";
		for(int i = 0; i < roles.size(); i++){
			if(i == roles.size() - 1){
				names += roles.get(i).getName();
			}else{
				names += roles.get(i).getName() + ", ";
			}
		}
		return names;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	//login.jsp页面传过来的验证码信息
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	
}
