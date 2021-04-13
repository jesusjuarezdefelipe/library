package com.unosquare.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	
	@Id
	private String id;
	private String userName;
	private String role;
	private String password;
	private String token;
	private Boolean enabled;
	private List<String> bookReceived;
	private List<String> bookSended;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<String> getBookReceived() {
		return bookReceived;
	}
	public void setBookReceived(List<String> bookReceived) {
		this.bookReceived = bookReceived;
	}
	public List<String> getBookSended() {
		return bookSended;
	}
	public void setBookSended(List<String> bookSended) {
		this.bookSended = bookSended;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", role=" + role + ", password=" + password + ", token="
				+ token + ", enabled=" + enabled + ", bookReceived=" + bookReceived + ", bookSended=" + bookSended
				+ "]";
	}

}
