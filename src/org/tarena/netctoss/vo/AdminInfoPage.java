package org.tarena.netctoss.vo;

import java.io.Serializable;

public class AdminInfoPage implements Serializable{
	private Integer priId;
	private String rolename;
	public Integer getPriId() {
		return priId;
	}
	public void setPriId(Integer priId) {
		this.priId = priId;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}
