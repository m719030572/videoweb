package com.springboot.domain;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Uhistory
{

	public int getHid() {
		return hid;
	}
	public void setHid(int cid) {
		this.hid = cid;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int hid;
	private String uid;
	private String vid;
	private Date date;

}
