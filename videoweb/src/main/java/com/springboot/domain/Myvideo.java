package com.springboot.domain;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Myvideo
{
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
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
	private int mid;
	private String uid;
	private String vid;
}
