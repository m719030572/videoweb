package com.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class User 
{

public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getUserstate() {
	return userstate;
}
public void setUserstate(int userstate) {
	this.userstate = userstate;
}

public int getShutup() {
	return shutup;
}
public void setShutup(int shutup) {
	this.shutup = shutup;
}
public int getBanned() {
	return banned;
}
public void setBanned(int banned) {
	this.banned = banned;
}

@Id
private	String uid;
private String account;
private String password;
private	String address;
private	String birthday;
private	String userid;
private	String name;
private	String email;
private	String phone;
@Column(insertable = false,columnDefinition = "int default 0")
private int userstate;
@Column(insertable = false,columnDefinition = "int default 0")
private int shutup;
@Column(insertable = false,columnDefinition = "int default 0")
private int banned;


}
