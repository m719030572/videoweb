package com.springboot.vo;

public class FindPasswdVo {
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRepasswd() {
		return repasswd;
	}
	public void setRepasswd(String repasswd) {
		this.repasswd = repasswd;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	private String uid;
	private String newpasswd;
	private String repasswd;
	private String passwd;
	private String checkcode;
	private String email;
	


}
