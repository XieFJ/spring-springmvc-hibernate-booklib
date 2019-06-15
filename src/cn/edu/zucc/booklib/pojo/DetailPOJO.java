package cn.edu.zucc.booklib.pojo;

import java.util.Date;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;

public class DetailPOJO {
	private Date lendDate;
	private Date dueDate;
	private Date returnDate;
	private double penalty;
	private int renewStatus;
	private BeanReader reader;
	private BeanBook book;
	private BeanLendRecord record;
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	public int getRenewStatus() {
		return renewStatus;
	}
	public void setRenewStatus(int renewStatus) {
		this.renewStatus = renewStatus;
	}
	public BeanReader getReader() {
		return reader;
	}
	public void setReader(BeanReader reader) {
		this.reader = reader;
	}
	public BeanBook getBook() {
		return book;
	}
	public void setBook(BeanBook book) {
		this.book = book;
	}
	public BeanLendRecord getRecord() {
		return record;
	}
	public void setRecord(BeanLendRecord record) {
		this.record = record;
	}

}
