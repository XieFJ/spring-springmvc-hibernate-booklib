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
@Table(name = "lend_record")
public class BeanLendRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "lendrecord_id", unique = true, nullable = false)
	private int lendrecordId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@Column(name = "lend_date")
	private Date lendDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@Column(name = "due_date")
	private Date dueDate;
	
	@ManyToOne(fetch=FetchType.LAZY) //fetch: 设置了延迟加载 ，默认为立即加载，不设置则会和bookType表外连接查询
    @JoinColumn(name="reader_id")
	private BeanReader reader;
	
	public BeanReader getReader() {
		return reader;
	}
	public void setReader(BeanReader reader) {
		this.reader = reader;
	}
	public int getLendrecordId() {
		return lendrecordId;
	}
	public void setLendrecordId(int lendrecordId) {
		this.lendrecordId = lendrecordId;
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
	
}
