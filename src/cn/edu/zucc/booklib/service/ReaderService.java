package cn.edu.zucc.booklib.service;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface ReaderService {
	
	public void addReader(String readerName, String readertypeName) throws BooklibException;
	
	public void deleteReader(int readerId) throws BooklibException;

	public int addLendNum(int readerId, int num) throws BooklibException;
	
	public void modifyReader(int readerId, String readerName, String readertypeName) throws BooklibException;
	
	public List<BeanReader> findReaders(String readerName, String readertypeName) throws BooklibException;
	
	public void changePwd(int readerId, String oldPwd, String newPwd, String newPwd2) throws BooklibException;

	public BeanReader checklogin(int readerId, String pwd) throws BooklibException;
}
