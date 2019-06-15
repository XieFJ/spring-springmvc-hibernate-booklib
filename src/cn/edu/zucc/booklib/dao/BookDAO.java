package cn.edu.zucc.booklib.dao;

import java.util.List;

import org.hibernate.Query;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface BookDAO {
	public void addBook(String bookName, String author, String publisher, int stockNumber, String introduction, String picture, BeanBookType bookType);
	
	public void deleteBook(BeanBook book);
	
	public void modifyBook(BeanBook book, String bookName, String author, String publisher, int stockNumber, BeanBookType bookType, String picture, String introduction) ;
	
	public BeanBook findBookById(int bookId) ;

	public List<BeanBook> findBooks(String bookName, String author, BeanBookType bookType) ;
	
}
