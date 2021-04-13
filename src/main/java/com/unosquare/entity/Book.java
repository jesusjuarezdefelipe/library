package com.unosquare.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "book")
public class Book {
	
	  @Id
	  private String id;
	  
	  @Field("author")
	  private String author;
	  @Field("title")
	  private String title;
	  @Field("year")
	  private Integer year;
	  @Field("ISBN")
	  private String ISBN;
	  @JsonIgnore
	  @Field("user_id")
	  private String userID;
	  @Field("state")
	  private Boolean state;
	
	  
    public Book() {}
	  

	public Book(String id, String author, String title, Integer year, String iSBN, String userID, Boolean state) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.year = year;
		ISBN = iSBN;
		this.userID = userID;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public Boolean getState() {
		return state;
	}


	public void setState(Boolean state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", year=" + year + ", ISBN=" + ISBN
				+ ", userID=" + userID + ", state=" + state + "]";
	}


	
	  
}
