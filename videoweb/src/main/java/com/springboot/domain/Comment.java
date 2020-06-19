package com.springboot.domain;


import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Comment 
{
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	public int getIsgood() {
		return isgood;
	}
	public void setIsgood(int isgood) {
		this.isgood = isgood;
	}
	
	public int getCommentat() {
		return commentat;
	}
	public void setCommentat(int commentat) {
		this.commentat = commentat;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}


	public int getFavour() {
		return favour;
	}
	public void setFavour(int favour) {
		this.favour = favour;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private	int cid;//评论唯一标识符
	private	String vid;//评论的视频
	private	String uid;//评论的用户
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date time;//评论时间
	@Column(insertable = false,columnDefinition = "int default 0")
	private	int commentat;//是否是回复评论，若是，则指向回复的那个cid；
	@Column(insertable = false,columnDefinition = "int default 0")
	private int isgood;
	private String data;//数据
	@Column(insertable = false,columnDefinition = "int default 0")
	private int favour;
	@Column(insertable = false,columnDefinition = "int default 0")
	private int dislike;
}
