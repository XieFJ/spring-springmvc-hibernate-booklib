package cn.edu.zucc.booklib.service;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface BookService {
	
	public void addBook(String bookName, String author, String publisher, int stockNumber, String introduction, String picture, String booktypeName) throws BooklibException;

	public void deleteBook(int bookId) throws BooklibException;
	
	public void reduceStock(int bookId) throws BooklibException;
	
	public void addStock(int bookId) throws BooklibException;
	
	public void modifyBook(int bookId, String bookName, String author, String publisher, int stockNumber, String booktypeName, String picture, String introduction)throws BooklibException ;

	public BeanBook findBookById(int bookId) throws BooklibException;
	
	public List<BeanBook> findBooks(String bookName, String author, String booktypeName)throws BooklibException ;

	public List<BeanBook> fuzzyQueryBooks(String query) throws BooklibException;
	
}
