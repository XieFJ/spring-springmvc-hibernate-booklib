package cn.edu.zucc.booklib.service;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanReaderType;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface ReaderTypeService {
	public void addReaderType(String readertypeName, int lendLimitted, int dueTime) throws BooklibException;
	
	public void deleteReaderType(int readertypeId) throws BooklibException;
	
	public void modifyReaderType(int readertypeId, String newName, int newLimit, int newDueTime) throws BooklibException;
	
	public List<BeanReaderType> loadReaderTypes() throws BooklibException;

	public List<BeanReaderType> fuzzyQueryTypeByName(String readertypeName) throws BooklibException;
}
