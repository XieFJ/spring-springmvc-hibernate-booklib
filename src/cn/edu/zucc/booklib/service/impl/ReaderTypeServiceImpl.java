package cn.edu.zucc.booklib.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.dao.ReaderTypeDAO;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.databean.BeanReaderType;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.ReaderService;
import cn.edu.zucc.booklib.service.ReaderTypeService;

@Service
@Transactional
public class ReaderTypeServiceImpl implements ReaderTypeService {

	@Autowired
	private ReaderTypeDAO readerTypeDao;
	@Autowired
	private ReaderDAO readerDao;
	
	@Override
	public void addReaderType(String readertypeName, int lendLimitted, int dueTime) throws BooklibException {
		if(readertypeName==null || ("").equals(readertypeName)) throw new BooklibException("请输入类别名！");
		if(lendLimitted<=0) throw new BooklibException("请输入借书数量上限！");
		if(dueTime<=0) throw new BooklibException("请输入借书期限！");
		BeanReaderType readerType = this.readerTypeDao.findTypeByName(readertypeName);
		if(readerType!=null) throw new BooklibException("读者类别已存在");
		
		this.readerTypeDao.addReaderType(readertypeName, lendLimitted, dueTime);
	}

	@Override
	public void deleteReaderType(int readertypeId) throws BooklibException {
		BeanReaderType readerType = this.readerTypeDao.findTypeById(readertypeId);
		if(readerType==null) throw new BooklibException("类别不存在！");
		List<BeanReader> readers = this.readerDao.findReaders(null, readerType);
		if(readers.size()>0) throw new BooklibException("该类别下有用户，无法删除类别！");
		
		this.readerTypeDao.deleteReaderType(readerType);
	}

	@Override
	public void modifyReaderType(int readertypeId, String newName, int newLimit, int newDueTime) throws BooklibException {
		BeanReaderType readerType = this.readerTypeDao.findTypeById(readertypeId);
		if(readerType==null) throw new BooklibException("类别不存在！");
		this.readerTypeDao.modifyReaderType(readerType, newName, newLimit, newDueTime);
	}

	@Override
	public List<BeanReaderType> fuzzyQueryTypeByName(String readertypeName) throws BooklibException{
		return this.readerTypeDao.fuzzyQueryTypeByName(readertypeName);
	}
	
	@Override
	public List<BeanReaderType> loadReaderTypes() throws BooklibException{
		return this.readerTypeDao.loadReaderTypes();
	}

}
