package com.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFollow
{
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUidto() {
		return uidto;
	}
	public void setUidto(String uidto) {
		this.uidto = uidto;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int cid;
	private String uid;
	private String uidto;
}