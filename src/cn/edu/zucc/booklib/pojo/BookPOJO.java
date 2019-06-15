package cn.edu.zucc.booklib.pojo;

import javax.persistence.Column;

import cn.edu.zucc.booklib.databean.BeanBookType;

public class BookPOJO {
	private String bookName;
	private String author;
	private String publisher;
	private int stockNumber;
	private String introduction;
	private String picture;
	private BeanBookType bookType;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public BeanBookType getBookType() {
		return bookType;
	}
	public void setBookType(BeanBookType bookType) {
		this.bookType = bookType;
	}
	
}
