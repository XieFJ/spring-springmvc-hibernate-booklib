package cn.edu.zucc.booklib.service;

import java.util.Date;
import java.util.List;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface DetailService {
	
	public void addBookDetail(int readerId, int bookId, int lendrecordId) throws BooklibException;
	
	public void deleteBookDetail(int detailId) throws BooklibException;
	
	public BeanDetail findById(int detailId) throws BooklibException;
	
	public List<BeanDetail> findDetails(int readerId, Date lendDate, Date dueDate, int bookId, int lendrecordId, Double penalty) throws BooklibException;
	
	public void renewDueDate(int detailId) throws BooklibException;//续借修改dueDate，加三个月
	
	public void returnBook(int detailId) throws BooklibException;
    
	public double calPenalty(int detailId) throws BooklibException;
}
