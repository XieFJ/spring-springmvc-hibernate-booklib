package cn.edu.zucc.booklib.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.DetailDAO;
import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.dao.ReaderTypeDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.databean.BeanReaderType;
import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.ReaderService;

@Service
@Transactional
public class ReaderServiceImpl implements ReaderService {
	
	@Autowired
	private ReaderDAO readerDao;
	@Autowired
	private ReaderTypeDAO readerTypeDao;
	@Autowired
	private DetailDAO detailDao;
	
	@Override
	public void addReader(String readerName, String readertypeName) throws BooklibException {
		if (readerName==null || ("").equals(readerName))
			throw new BooklibException("请输入读者名！");
		BeanReaderType readerType = this.readerTypeDao.findTypeByName(readertypeName);
		if (readerType==null || ("").equals(readerType))
			throw new BooklibException("请选择读者类别！");
		
		this.readerDao.addReader(readerName, readerType);
	}

	@Override
	public void deleteReader(int readerId) throws BooklibException {
		BeanReader reader = null;
		if(readerId>0) {
			reader = this.readerDao.findReaderById(readerId);
			if(reader==null)throw new BooklibException("读者不存在");
		}
		 
		List<BeanDetail> details = null;
		details = this.detailDao.findDetails(reader, null, null, null, null, -1.0);
		if(details.size()>0) throw new BooklibException("该读者还有未还图书，无法删除");
		
		this.readerDao.deleteReader(reader);
	}

	@Override
	public int addLendNum(int readerId, int num) throws BooklibException {
		int flag = 0;
		BeanReader reader = this.readerDao.findReaderById(readerId);
		if(reader==null)throw new BooklibException("读者不存在");
		int lendlimit = reader.getReaderType().getLendLimitted();
		int lendNum = reader.getLendNumber();
		if((lendlimit-lendNum)<num) throw new BooklibException("借阅已达上限");
		this.readerDao.addLendNum(reader, num);
		flag = 1;
		return flag;
	}
	
	@Override
	public void modifyReader(int readerId, String readerName, String readertypeName) throws BooklibException {
		BeanReader reader = this.readerDao.findReaderById(readerId);
		if(reader==null)throw new BooklibException("读者不存在");
		BeanReaderType readerType = null;
		if(readertypeName!=null&&!("").equals(readertypeName)) {
			readerType = this.readerTypeDao.findTypeByName(readertypeName);
			if(readerType==null)throw new BooklibException("类别不存在");
		}
		this.readerDao.modifyReader(reader, readerName, readerType);
	}

	@Override
	public List<BeanReader> findReaders(String readerName, String readertypeName) throws BooklibException {
		BeanReaderType readerType = null;
		if(readertypeName!=null&&!("").equals(readertypeName)) {
			readerType = this.readerTypeDao.findTypeByName(readertypeName);
			if(readerType==null)throw new BooklibException("类别不存在");
		}
		return this.readerDao.findReaders(readerName, readerType);
	}

	@Override
	public void changePwd(int readerId, String oldPwd, String newPwd, String newPwd2) throws BooklibException {
		BeanReader reader = this.readerDao.findReaderById(readerId);
		if (reader == null)
			throw new BooklibException("读者不存在！");
		if (!reader.getPassword().equals(oldPwd)) 
			throw new BooklibException("原始密码错误！");
		if (newPwd==null || ("").equals(newPwd))
			throw new BooklibException("请输入新密码！");
		if (!newPwd.equals(newPwd2))
			throw new BooklibException("密码不一致！");
		
		this.readerDao.changePwd(reader, newPwd);
	}

	@Override
	public BeanReader checklogin(int readerId, String pwd) throws BooklibException {
		BeanReader reader = this.readerDao.findReaderById(readerId);
		if (reader == null)
			throw new BooklibException("读者不存在！");
		if (!reader.getPassword().equals(pwd)) 
			throw new BooklibException("密码错误！");
		
		return reader;
	}

}
