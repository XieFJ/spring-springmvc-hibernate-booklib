package cn.edu.zucc.booklib.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.BookDAO;
import cn.edu.zucc.booklib.dao.LendRecordDAO;
import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.databean.BeanReaderType;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.LendRecordService;

@Service
@Transactional
public class LendRecordServiceImpl implements LendRecordService {

	@Autowired
	private ReaderDAO readerDao;
	@Autowired
	private LendRecordDAO recordDao;
	@Autowired
	private BookDAO bookDAO;
	
	@Override
	public BeanLendRecord addLendRecord(int readerId) throws BooklibException {
		if (readerId<=0) throw new BooklibException("请输入读者编号！");
		BeanReader reader = this.readerDao.findReaderById(readerId);
		
		return this.recordDao.addLendRecord(reader);
	}

	@Override
	public BeanLendRecord findById(int lendrecordId) throws BooklibException {
		if(lendrecordId<=0) throw new BooklibException("请输入记录编号！");
		
		return this.recordDao.findById(lendrecordId);
	}

	@Override
	public List<BeanLendRecord> findRecords(int readerId, Date lendDate) throws BooklibException {
		BeanReader reader = null;
		BeanBook book = null;
		if(readerId>0) {
			reader = this.readerDao.findReaderById(readerId);
			if(reader==null)throw new BooklibException("读者不存在");
		}
		return this.recordDao.findRecords(reader, lendDate);
	}

}
