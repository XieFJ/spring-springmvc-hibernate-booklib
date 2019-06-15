package cn.edu.zucc.booklib.dao.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.booklib.dao.DetailDAO;
import cn.edu.zucc.booklib.dao.ReaderDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.exception.BooklibException;

@Repository
public class DetailDaoImpl implements DetailDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addBookDetail(BeanReader reader, BeanBook book, BeanLendRecord record) {
		Session session = getCurrentSession();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = sdf.format(new Date());
//		ParsePosition pos = new ParsePosition(8);
//		Date lendDate = sdf.parse(dateString, pos);
		Date lendDate = new Date();
		BeanDetail detail = new BeanDetail();
		detail.setLendDate(lendDate);
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(lendDate);
		int dueTime = reader.getReaderType().getDueTime();
		calendar.add(calendar.DATE,+dueTime);
		Date dueDate = calendar.getTime();
		detail.setDueDate(dueDate);
		detail.setReader(reader);
		detail.setBook(book);
		detail.setRecord(record);
		detail.setRenewStatus(0);
		
		session.save(detail);
	}

	@Override
	public void deleteBookDetail(BeanDetail detail) {
		Session session = getCurrentSession();
		detail.setReturnDate(new Date());
		session.update(detail);
	}

	@Override
	public BeanDetail findById(int detailId) {
		Session session = getCurrentSession();
		BeanDetail detail = session.get(BeanDetail.class, detailId);
		if(detail.getReturnDate()!=null) return null;
		return detail;
	}

	@Override
	public List<BeanDetail> findDetails(BeanReader reader, Date lendDate, Date dueDate, BeanBook book, BeanLendRecord record,
			Double penalty) {
		Session session = getCurrentSession();
		String hql = "from BeanDetail where 1=1 and returnDate is null";
	    if(reader!=null&&!("").equals(reader)){
	        hql=hql+" and reader='"+reader.getReaderId()+"'";
	    }
	    if(lendDate!=null&&!("").equals(lendDate)){
	        hql=hql+" and lendDate like '"+lendDate+"'%";
	    }
	    if(dueDate!=null&&!("").equals(dueDate)){
	        hql=hql+" and dueDate like '"+dueDate+"'%";
	    }
	    if(book!=null&&!("").equals(book)){
	        hql=hql+" and book='"+book.getBookId()+"'";
	    }
	    if(record!=null&&!("").equals(record)){
	        hql=hql+" and record='"+record.getLendrecordId()+"'";
	    }
	    if(penalty>=0){
	        hql=hql+" and penalty='"+penalty+"'";
	    }
	    hql=hql+" order by lendDate desc";
	    System.out.println(hql);
	    List<BeanDetail> details=session.createQuery(hql).list();
		 
		return details;
	}

	@Override
	public void renewDueDate(BeanDetail detail) {
		Session session = getCurrentSession();
		Date dueDate = detail.getDueDate();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dueDate);
		int dueTime = detail.getReader().getReaderType().getDueTime();
		calendar.add(calendar.DATE,+dueTime);
		Date newDueDate = calendar.getTime();
		detail.setDueDate(newDueDate);
		detail.setRenewStatus(1);
		
		session.update(detail);

	}

	@Override
	public double calPenalty(BeanDetail detail){
		Session session = getCurrentSession();
		int days = (int) (((new Date()).getTime() - detail.getLendDate().getTime())/(24*60*60*1000));
		int dueTime = detail.getReader().getReaderType().getDueTime();
		double penalty = 0.0;
		if(days<=dueTime);
		else penalty = (days-dueTime)*0.1;//一天罚一毛钱
		detail.setPenalty(penalty);
		session.update(detail);
		return penalty;
	}
	
	public void returnBook(BeanDetail detail){
		Session session = getCurrentSession();
		detail.setReturnDate(new Date());
	}

}
