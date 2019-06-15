package cn.edu.zucc.booklib.service;

import cn.edu.zucc.booklib.exception.BooklibException;

public interface borrowService {
	
	public void borrowBook(int readerId, int bookId, int lendrecordId) throws BooklibException;
	
	public void returnBook(int readerId, int detailId) throws BooklibException;
	
}
