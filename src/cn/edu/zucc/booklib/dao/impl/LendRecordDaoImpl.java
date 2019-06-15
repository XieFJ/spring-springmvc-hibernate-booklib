package cn.edu.zucc.booklib.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.booklib.dao.LendRecordDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;

@Repository
public class LendRecordDaoImpl implements LendRecordDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public BeanLendRecord addLendRecord(BeanReader reader) {
		Session session = getCurrentSession();
		BeanLendRecord record = new BeanLendRecord();
		Calendar calendar = new GregorianCalendar();
		Date lendDate = new Date();
		calendar.setTime(lendDate);
		int lendLimitted = reader.getReaderType().getLendLimitted();
		calendar.add(calendar.DATE,+lendLimitted);
		Date dueDate = calendar.getTime();
		record.setLendDate(lendDate);
		record.setDueDate(dueDate);
		record.setReader(reader);
		session.save(record);
		
		return record;
	}

	@Override
	public BeanLendRecord findById(int lendrecordId) {
		Session session = getCurrentSession();
		BeanLendRecord record = session.get(BeanLendRecord.class, lendrecordId);
		return record;
	}

	@Override
	public List<BeanLendRecord> findRecords(BeanReader reader, Date lendDate) {
		Session session = getCurrentSession();
		String hql = "from BeanLendRecord where 1=1 and dueDate!=null";
	    if(reader!=null&&!("").equals(reader)){
	        hql=hql+" and reader='"+reader.getReaderId()+"'";
	    }
	    if(lendDate!=null&&!("").equals(lendDate)){
	        hql=hql+" and lendDate='"+lendDate+"'";
	    }
	    List<BeanLendRecord> records=session.createQuery(hql).list();
		 
		return records;
	}

}
