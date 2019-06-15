package	cn.edu.zucc.booklib.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_type")
public class BeanBookType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "booktype_id", unique = true, nullable = false)
	private int booktypeId;
	@Column(name = "booktype_name")
	private String booktypeName;
	@Column(name = "picture")
	private String picture;
	@Column(name = "is_delete")
	private int isDelete;

	public void setBooktypeId(int booktypeId){
		this.booktypeId = booktypeId;
	}
	public int getBooktypeId(){
		return booktypeId;
	}
	public void setBooktypeName(String booktypeName){
		this.booktypeName = booktypeName;
	}
	public String getBooktypeName(){
		return booktypeName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setIsDelete(int isDelete){
		this.isDelete = isDelete;
	}
	public int getIsDelete(){
		return isDelete;
	}
}
