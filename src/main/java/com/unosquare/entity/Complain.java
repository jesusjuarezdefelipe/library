package com.unosquare.entity;

import java.io.Serializable;
import java.util.Date;


public class Complain implements Serializable{
	
	private static final long serialVersionUID = -7197502794842989576L;
	private String user;
	  private String complain;
	  private Date date;
	  
	  public Complain() {}
	  
	public Complain(String user, String complain, Date date) {
		super();
		this.user = user;
		this.complain = complain;
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getComplain() {
		return complain;
	}
	public void setComplain(String complain) {
		this.complain = complain;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Complain [user=" + user + ", complain=" + complain + ", date=" + date + "]";
	}

}
