package com.springboot.domain;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Collection 
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
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int cid;
	private String uid;
	private String vid;
}
