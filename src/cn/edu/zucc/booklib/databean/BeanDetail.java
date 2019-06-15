package cn.edu.zucc.booklib.databean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "lend_detail")
public class BeanDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "detail_id", unique = true, nullable = false)
	private int detailId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@Column(name = "lend_date")
	private Date lendDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@Column(name = "due_date")
	private Date dueDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@Column(name = "return_date")
	private Date returnDate;
	@Column(name = "penalty")
	private double penalty;
	@Column(name = "renew_status")
	private int renewStatus;
	
	@ManyToOne(fetch=FetchType.EAGER) //fetch: 设置了延迟加载 ，默认为立即加载，不设置则会和reader表外连接查询
    @JoinColumn(name="reader_id")
	private BeanReader reader;
	
	@ManyToOne(fetch=FetchType.EAGER) 
    @JoinColumn(name="book_id")
	private BeanBook book;
	
	@ManyToOne(fetch=FetchType.EAGER) 
    @JoinColumn(name="lendrecord_id")
	private BeanLendRecord record;

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
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public BeanReader getReader() {
		return reader;
	}
	public void setReader(BeanReader reader) {
		this.reader = reader;
	}
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
	
}

