package com.springboot.domain;

import javax.persistence.*;

@Entity
public class Video 
{
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVideo_key() {
		return video_key;
	}
	public void setVideo_key(String key) {
		this.video_key = key;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getKbps() {
		return kbps;
	}
	public void setKbps(String kbps) {
		this.kbps = kbps;
	}
	public int getComcount() {
		return comcount;
	}
	public void setComcount(int comcount) {
		this.comcount = comcount;
	}
	public int getBarcount() {
		return barcount;
	}
	public void setBarcount(int barcount) {
		this.barcount = barcount;
	}
	public int getPracount() {
		return pracount;
	}
	public void setPracount(int pracount) {
		this.pracount = pracount;
	}
	public int getTrecount() {
		return trecount;
	}
	public void setTrecount(int trecount) {
		this.trecount = trecount;
	}
	public int getBrocount() {
		return brocount;
	}
	public void setBrocount(int brocount) {
		this.brocount = brocount;
	}
	public int getVideostate() {
		return videostate;
	}
	public void setVideostate(int videostate) {
		this.videostate = videostate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getColcount() {
		return colcount;
	}
	public void setColcount(int colcount) {
		this.colcount = colcount;
	}
	public int getCompermission() {
		return compermission;
	}
	public void setCompermission(int compermission) {
		this.compermission = compermission;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Id
	private	String vid;//视频唯一标识符
	private String uid;//视频用户id
	int time;//时长
	private	String definition;//清晰度
	private	String name;//视频名字
	private	String video_key;//关键字
	private	String category;//视频分类
	private	String kbps;//视频发布码率
	private String url;//文件地址
    @Column(insertable = false,columnDefinition = "int default 0")
	private int comcount;//评论量
    @Column(insertable = false,columnDefinition = "int default 0")
	private int barcount;//弹幕量
    @Column(insertable = false,columnDefinition = "int default 0")
	private int pracount;//赞量
    @Column(insertable = false,columnDefinition = "int default 0")
	private int trecount;//踩量
    @Column(insertable = false,columnDefinition = "int default 0")
	private int brocount;//浏览量
    @Column(insertable = false,columnDefinition = "int default 0")
    private int colcount;//收藏量
    @Column(insertable = false,columnDefinition = "int default 0")
    private int compermission;//评论权限
    @Column(insertable = false,columnDefinition = "int default 0")
	private int videostate;//视频状态 0审核失败 1未审核 2审核通过 3下架 4上架
    private String username;
    

}
