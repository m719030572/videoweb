package com.springboot.vo;

public class LoginVo 
{
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPasswd() {
		return password;
	}
	public void setPasswd(String passwd) {
		this.password = passwd;
	}
	public int getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(int checkcode) {
		this.checkcode = checkcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String uid;
	private String password;
	private int checkcode;
	private String email;


}
