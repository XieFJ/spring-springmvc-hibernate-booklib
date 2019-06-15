package cn.edu.zucc.booklib.pojo;

import java.util.Date;

import cn.edu.zucc.booklib.databean.BeanReaderType;

public class ReaderPOJO {
	private String readerName;
	private String password;
	private int lendNumber;
	private Date createDate;
	private ReaderTypePOJO readerType;
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLendNumber() {
		return lendNumber;
	}
	public void setLendNumber(int lendNumber) {
		this.lendNumber = lendNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public ReaderTypePOJO getReaderType() {
		return readerType;
	}
	public void setReaderType(ReaderTypePOJO readerType) {
		this.readerType = readerType;
	}

}
