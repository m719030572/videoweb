package com.springboot.domain;

import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name="barrage")
public class Barrage 
{

	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
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
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private	int bid;
	private	String vid;
	private	String uid;
	private Time time;
}
