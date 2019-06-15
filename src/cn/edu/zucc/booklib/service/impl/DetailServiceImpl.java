package cn.edu.zucc.booklib.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.BookDAO;
import cn.edu.zucc.booklib.dao.DetailDAO;
import cn.edu.zucc.booklib.dao.LendRecordDAO;
import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.DetailService;

@Service
@Transactional
public class DetailServiceImpl implements DetailService {

	@Autowired
	private DetailDAO detailDao;
	@Autowired
	private BookDAO bookDao;
	@Autowired
	private ReaderDAO readerDao;
	@Autowired
	private LendRecordDAO recordDao;
	
	@Override
	public void addBookDetail(int readerId, int bookId, int lendrecordId) throws BooklibException {
		if (readerId<=0)
			throw new BooklibException("请输入读者编号！");
		if (bookId<=0)
			throw new BooklibException("请输入图书编号！");
		if (lendrecordId<=0)
			throw new BooklibException("请输入借书记录编号！");
		BeanReader reader = this.readerDao.findReaderById(readerId);
		BeanBook book = this.bookDao.findBookById(bookId);
		BeanLendRecord record = this.recordDao.findById(lendrecordId);
		
		this.detailDao.addBookDetail(reader, book, record);
	}

	@Override
	public void deleteBookDetail(int detailId) throws BooklibException {
		BeanDetail detail = this.detailDao.findById(detailId);
		if(detail==null)throw new BooklibException("记录不存在");
		
		this.detailDao.deleteBookDetail(detail);
	}

	@Override
	public BeanDetail findById(int detailId) throws BooklibException {
		BeanDetail detail =  this.detailDao.findById(detailId);
		if(detail==null)throw new BooklibException("记录不存在");
		
		return detail;
	}

	@Override
	public List<BeanDetail> findDetails(int readerId, Date lendDate, Date dueDate, int bookId, int lendrecordId,
			Double penalty) throws BooklibException {
		BeanReader reader = null;
		if(readerId>0) {
			reader = this.readerDao.findReaderById(readerId);
			if(reader==null)throw new BooklibException("读者不存在");
		}
		BeanBook book = null;
		if(bookId>0) {
			book = this.bookDao.findBookById(bookId);
			if(book==null)throw new BooklibException("图书不存在");
		}
		BeanLendRecord record = null;
		if(lendrecordId>0) {
			record = this.recordDao.findById(lendrecordId);
			if(record==null)throw new BooklibException("记录不存在");
		}
		List<BeanDetail> details = this.detailDao.findDetails(reader, lendDate, dueDate, book, record, penalty);
//		if(details.size()<=0)throw new BooklibException("记录不存在");
		
		return details;
	}

	@Override
	public void renewDueDate(int detailId) throws BooklibException {
		BeanDetail detail = this.detailDao.findById(detailId);
		if(detail==null)throw new BooklibException("记录不存在");

		if(detail.getRenewStatus()==1)throw new BooklibException("续借次数已达上限");

		this.detailDao.renewDueDate(detail);
	}

	@Override
	public double calPenalty(int detailId) throws BooklibException {
		BeanDetail detail = this.detailDao.findById(detailId);
		if(detail==null)throw new BooklibException("记录不存在");
		if(detail.getReturnDate()!=null) throw new BooklibException("书已归还");
		return this.detailDao.calPenalty(detail);
	}

	@Override
	public void returnBook(int detailId) throws BooklibException {
		BeanDetail detail = this.detailDao.findById(detailId);
		if(detail==null)throw new BooklibException("记录不存在");
		if(detail.getReturnDate()!=null) throw new BooklibException("书已归还");
		this.detailDao.returnBook(detail);
		this.detailDao.deleteBookDetail(detail);
	}

}
