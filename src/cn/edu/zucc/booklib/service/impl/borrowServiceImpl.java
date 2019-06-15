package cn.edu.zucc.booklib.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.BookDAO;
import cn.edu.zucc.booklib.dao.DetailDAO;
import cn.edu.zucc.booklib.dao.LendRecordDAO;
import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.borrowService;

@Service
@Transactional
public class borrowServiceImpl implements borrowService {

	@Autowired
	private DetailDAO detailDao;
	@Autowired
	private BookDAO bookDao;
	@Autowired
	private ReaderDAO readerDao;
	@Autowired
	private LendRecordDAO recordDao;
	
	@Override
	public void borrowBook(int readerId, int bookId, int lendrecordId) throws BooklibException {
		

	}

	public void returnBook(int readerId, int detailId) throws BooklibException {
		

	}

}
