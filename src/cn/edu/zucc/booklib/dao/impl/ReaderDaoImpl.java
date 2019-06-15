package cn.edu.zucc.booklib.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.databean.BeanReaderType;

@Repository
public class ReaderDaoImpl implements ReaderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addReader(String readerName, BeanReaderType readerType) {
		Session session = getCurrentSession();
		BeanReader reader = new BeanReader();
		reader.setReaderName(readerName);
		reader.setPassword("123456");
		reader.setLendNumber(0);
		reader.setCreateDate(new Date());
		reader.setReaderType(readerType);
		session.save(reader);
	}

	@Override
	public void deleteReader(BeanReader reader) {
		Session session = getCurrentSession();
		reader.setIsDelete(1);
		session.update(reader);
	}
	
	@Override
	public void addLendNum(BeanReader reader, int num) {
		Session session = getCurrentSession();
		int lendNum = reader.getLendNumber();
		reader.setLendNumber(lendNum+num);
		session.update(reader);
	}
	
	@Override
	public void modifyReader(BeanReader reader, String readerName, BeanReaderType readerType) {
		Session session = getCurrentSession();
		if(readerName!=null&&!("").equals(readerName)){
	        reader.setReaderName(readerName);
	    }
	    if(readerType!=null&&!("").equals(readerType)){
	        reader.setReaderType(readerType);
	    }
	    System.out.println(reader.getReaderName()+" "+reader.getReaderType().getReadertypeName());
	    session.update(reader);
	}

	@Override
	public BeanReader findReaderById(int readerId) {
		Session session = getCurrentSession();
		BeanReader reader = session.get(BeanReader.class, readerId);
		if(reader==null || reader.getIsDelete()==1) return null;
		return reader;
	}

	@Override
	public List<BeanReader> findReaders(String readerName, BeanReaderType readerType) {
		Session session = getCurrentSession();
		String hql = "from BeanReader where 1=1 and isDelete=0";
	    if(readerName!=null&&!("").equals(readerName)){
	        hql=hql+" and readerName='"+readerName+"'";
	    }
	    if(readerType!=null&&!("").equals(readerType)){
	        hql=hql+" and readerType='"+readerType.getReadertypeId()+"'";
	    }
	    System.out.println(readerType);
	    List<BeanReader> readers=session.createQuery(hql).list();
		return readers;
	}

	@Override
	public void changePwd(BeanReader reader, String newPwd) {
		Session session = getCurrentSession();
		reader.setPassword(newPwd);
		session.update(reader);
	}

}
