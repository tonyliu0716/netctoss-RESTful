package org.tarena.netctoss.entity;

import java.io.Serializable;

public class ServicePage implements Serializable{
	//和页面对应
	private String osusername;
	private String unixhost;
	private String idcardno;
	private String status;
	public String getOsusername() {
		return osusername;
	}
	public void setOsusername(String osusername) {
		this.osusername = osusername;
	}
	public String getUnixhost() {
		return unixhost;
	}
	public void setUnixhost(String unixhost) {
		this.unixhost = unixhost;
	}
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
