package cn.edu.zucc.booklib.dao;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.databean.BeanReaderType;

public interface ReaderDAO {
	//密码默认123456
	public void addReader(String readerName, BeanReaderType  readerType);
    
	public void deleteReader(BeanReader reader);
	
	public void addLendNum(BeanReader reader, int num);
	
	public void modifyReader(BeanReader reader, String readerName, BeanReaderType readerType);
	
	public BeanReader findReaderById(int readerId);
	
	public List<BeanReader> findReaders(String readerName, BeanReaderType readerType);

	public void changePwd(BeanReader reader, String newPwd);
	
}
