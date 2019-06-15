package cn.edu.zucc.booklib.pojo;

import java.util.Date;

import cn.edu.zucc.booklib.databean.BeanReader;

public class LendRecordPOJO {
	private Date lendDate;
	private Date dueDate;
	private BeanReader reader;
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
	public BeanReader getReader() {
		return reader;
	}
	public void setReader(BeanReader reader) {
		this.reader = reader;
	}	
}
