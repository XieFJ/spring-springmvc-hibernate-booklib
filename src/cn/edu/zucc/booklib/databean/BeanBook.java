package	cn.edu.zucc.booklib.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "book")
public class BeanBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "book_id", unique = true, nullable = false)
	private int bookId;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "author")
	private String author;
	@Column(name = "publisher")
	private String publisher;
	@Column(name = "stock_number")
	private int stockNumber;
	@Column(name = "introduction")
	private String introduction;
	@Column(name = "picture")
	private String picture;
	@Column(name = "is_delete")
	private int isDelete;
	
	@ManyToOne(fetch=FetchType.EAGER) //fetch: 设置了延迟加载 ，默认为立即加载，不设置则会和bookType表外连接查询
    @JoinColumn(name="booktype_id")
	private BeanBookType bookType;

	public BeanBookType getBookType() {
		return bookType;
	}
	public void setBookType(BeanBookType bookType) {
		this.bookType = bookType;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
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
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
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
}
