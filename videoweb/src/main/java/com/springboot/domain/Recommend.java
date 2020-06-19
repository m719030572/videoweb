package com.springboot.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recommend 
{
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getVideolist() {
		return videolist;
	}
	public void setVideolist(String videolist) {
		this.videolist = videolist;
	}
	public Date getBuilddate() {
		return builddate;
	}
	public void setBuilddate(Date builddate) {
		this.builddate = builddate;
	}
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int rid;
	private String uid;
	private String videolist;
	private String vid;
	private Date builddate;

}
