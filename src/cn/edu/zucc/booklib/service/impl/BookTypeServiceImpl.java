package cn.edu.zucc.booklib.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.BookDAO;
import cn.edu.zucc.booklib.dao.BookTypeDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.BookTypeService;

@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeService {

	@Autowired
	private BookTypeDAO bookTypeDao;
	@Autowired
	private BookDAO bookDao;
	
	@Override
	public void addBookType(String booktypeName, String picture) throws BooklibException {
		if(booktypeName==null || ("").equals(booktypeName)) throw new BooklibException("请输入类别名！");
		if (picture==null || ("").equals(picture)) throw new BooklibException("请输入图片地址！");
//		BeanBookType bookType = new BeanBookType();
//		bookType = this.bookTypeDao.findTypeByName(booktypeName);
		if((this.bookTypeDao.findTypeByName(booktypeName))!=null)throw new BooklibException("图书类别已存在");
		
		this.bookTypeDao.addBookType(booktypeName, picture);
	}

	@Override
	public void deleteBookType(int booktypeId) throws BooklibException {
		BeanBookType bookType = this.bookTypeDao.findTypeById(booktypeId);
		if(bookType==null) throw new BooklibException("类别不存在！");
		List<BeanBook> books = this.bookDao.findBooks(null, null, bookType);
		if(books.size()>0) throw new BooklibException("该类别下有图书，无法删除");
		
		this.bookTypeDao.deleteBookType(bookType);
	}

	@Override
	public void modifyBookType(int booktypeId, String newName, String picture) throws BooklibException {
		BeanBookType bookType = this.bookTypeDao.findTypeById(booktypeId);
		if(bookType==null) throw new BooklibException("类别不存在！");
		
		this.bookTypeDao.modifyBookType(bookType, newName, picture);
	}

	@Override
	public List<BeanBookType> loadBookTypes() throws BooklibException {
		return this.bookTypeDao.loadBookTypes();
	}
	
	@Override
	public List<BeanBookType> fuzzyQueryTypeByName(String booktypeName) throws BooklibException {
		return this.bookTypeDao.fuzzyQueryTypeByName(booktypeName);
	}
}
