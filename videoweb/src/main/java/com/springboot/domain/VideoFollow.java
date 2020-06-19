package com.springboot.domain;

import java.sql.Date;

import javax.persistence.*;
@Entity
public class VideoFollow 
{
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int fid;
	private String uid;
	private String vid;
	private Date time;
}
