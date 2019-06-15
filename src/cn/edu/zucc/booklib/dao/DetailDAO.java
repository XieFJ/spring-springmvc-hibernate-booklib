package cn.edu.zucc.booklib.dao;

import java.util.Date;
import java.util.List;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface DetailDAO {

	public void addBookDetail(BeanReader reader, BeanBook book, BeanLendRecord record);
	
	public void deleteBookDetail(BeanDetail detail);
	
	public BeanDetail findById(int detailId);
	
	public List<BeanDetail> findDetails(BeanReader reader, Date lendDate, Date dueDate, BeanBook book, BeanLendRecord record, Double penalty);
	
	public void renewDueDate(BeanDetail detail);//续借修改dueDate，加三个月
    
	public double calPenalty(BeanDetail detail);

	public void returnBook(BeanDetail detail);
}
