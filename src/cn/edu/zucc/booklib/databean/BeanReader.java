package	cn.edu.zucc.booklib.databean;
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
@Table(name = "reader")
public class BeanReader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "reader_id", unique = true, nullable = false)
	private int readerId;
	@Column(name = "reader_name")
	private String readerName;
	@Column(name = "password")
	private String password;
	@Column(name = "lend_number")
	private int lendNumber;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "is_delete")
	private int isDelete;
	
	@ManyToOne(fetch=FetchType.EAGER) //fetch: 设置了延迟加载 ，默认为立即加载，不设置则会和bookType表外连接查询
    @JoinColumn(name="readertype_id")
	private BeanReaderType readerType;
	
	public BeanReaderType getReaderType() {
		return readerType;
	}
	public void setReaderType(BeanReaderType readerType) {
		this.readerType = readerType;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
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
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}
