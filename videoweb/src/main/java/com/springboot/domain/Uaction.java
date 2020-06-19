package com.springboot.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Uaction 
{
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getBrowser() {
		return browser;
	}
	public void setBrowser(int browser) {
		this.browser = browser;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public int getEnshrine() {
		return enshrine;
	}
	public void setEnshrine(int enshrine) {
		this.enshrine = enshrine;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public int getBarrage() {
		return barrage;
	}
	public void setBarrage(int barrage) {
		this.barrage = barrage;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private	int aid;
	private	String uid;
	private	String vid;
	private String action;//browser,like,dislike,enshrine,comment,barrage,download
    @Column(insertable = false,columnDefinition = "int default 0")
	private int browser;
    @Column(insertable = false,columnDefinition = "int default 0")
	private int dislike;
    @Column(insertable = false,columnDefinition = "int default 0")
	private int enshrine;
    @Column(insertable = false,columnDefinition = "int default 0")
	private int comment;
    @Column(insertable = false,columnDefinition = "int default 0")
	private int barrage;
    @Column(insertable = false,columnDefinition = "int default 0")
	private int download;
	private Date time;
}
