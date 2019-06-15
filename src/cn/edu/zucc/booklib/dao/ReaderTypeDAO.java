package cn.edu.zucc.booklib.dao;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanReaderType;;

public interface ReaderTypeDAO {

	public void addReaderType(String readertypeName, int lendLimitted, int dueTime);
	
	public void deleteReaderType(BeanReaderType readerType);
	
	public void modifyReaderType(BeanReaderType readerType, String newName, int newLimit, int newDueTime);

	public BeanReaderType findTypeById(int readertypeId);
	
	public BeanReaderType findTypeByName(String readertypeName);
	
	public List<BeanReaderType> loadReaderTypes();
    
	public List<BeanReaderType> fuzzyQueryTypeByName(String readertypeName);
}
