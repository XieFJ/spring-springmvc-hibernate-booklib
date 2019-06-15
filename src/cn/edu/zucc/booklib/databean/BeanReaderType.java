package	cn.edu.zucc.booklib.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
@Entity
@Table(name = "reader_type")
public class BeanReaderType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "readertype_id", unique = true, nullable = false)
	private int readertypeId;
	@Column(name = "readertype_name")
	private String readertypeName;
	@Column(name = "lend_limitted")
	private int lendLimitted;
	@Column(name = "due_time")
	private int dueTime;
	@Column(name = "is_delete")
	private int isDelete;
	
	public int getReadertypeId() {
		return readertypeId;
	}
	public void setReadertypeId(int readertypeId) {
		this.readertypeId = readertypeId;
	}
	public String getReadertypeName() {
		return readertypeName;
	}
	public void setReadertypeName(String readertypeName) {
		this.readertypeName = readertypeName;
	}
	public int getLendLimitted() {
		return lendLimitted;
	}
	public void setLendLimitted(int lendLimitted) {
		this.lendLimitted = lendLimitted;
	}
	public int getDueTime() {
		return dueTime;
	}
	public void setDueTime(int dueTime) {
		this.dueTime = dueTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
