package org.tarena.netctoss.entity;

import java.io.Serializable;
import java.util.List;

import org.tarena.netctoss.util.PrivilegeReader;

public class Role implements Serializable{
	private Integer id;
	private String name;
	private List<Role_privilege> privileges;
	
	//追加一个方法，根据privileges的权限id信息，返回权限的名称
	//将来在JSP页面可以通过${role.privilegesName}进行调用！
	public String getPrivilegesName(){
		String name = "";
		for(int i = 0; i < privileges.size(); i++){
			Integer pid = privileges.get(i).getPrivilege_id();
			name += PrivilegeReader.getModuleNameById(pid + "");
			if(i < privileges.size() - 1){
				name += ",";
			}
		}
		return name;
	}
	
	public List<Role_privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Role_privilege> privileges) {
		this.privileges = privileges;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
