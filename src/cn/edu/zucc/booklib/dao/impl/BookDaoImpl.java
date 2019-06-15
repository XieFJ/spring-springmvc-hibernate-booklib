package cn.edu.zucc.booklib.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.messaging.saaj.soap.StringDataContentHandler;

import cn.edu.zucc.booklib.dao.BookDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.exception.BooklibException;

@Repository
public class BookDaoImpl implements BookDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void addBook(String bookName, String author, String publisher, int stockNumber, String introduction, String picture, BeanBookType bookType) {
		Session session = getCurrentSession();
		BeanBook book = new BeanBook();
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setStockNumber(stockNumber);
		book.setIntroduction(introduction);
		book.setPicture(picture);
		book.setBookType(bookType);
		System.out.println(book.getStockNumber());
		session.save(book);
		System.out.println(book.getStockNumber());
	}

	@Override
	public void deleteBook(BeanBook book)  {		
		Session session = getCurrentSession();
		book.setIsDelete(1);
		session.update(book);
	}

	@Override
	public void modifyBook(BeanBook book, String bookName, String author, String publisher, int stockNumber,BeanBookType bookType, String picture, String introduction)  {
		Session session = getCurrentSession();
		if(bookName!=null&&!("").equals(bookName)){
	        book.setBookName(bookName);
	    }
	    if(author!=null&&!("").equals(author)){
	        book.setAuthor(author);
	    }
	    if(publisher!=null&&!("").equals(publisher)){
	        book.setPublisher(publisher);
	    }
	    if(stockNumber>=0){
	        book.setStockNumber(stockNumber);
	    }
	    if(bookType!=null&&!("").equals(bookType)){
	        book.setBookType(bookType);
	    }
	    if(picture!=null&&!("").equals(picture)){
	        book.setPicture(picture);
	    }
	    if(introduction!=null&&!("").equals(introduction)){
	        book.setIntroduction(introduction);;
	    }
	    session.update(book);
	}

	@Override
	public BeanBook findBookById(int bookId)  {
		Session session = getCurrentSession();
		BeanBook book = session.get(BeanBook.class, bookId);
		if(book.getIsDelete()==1) return null;
		System.out.println(bookId);
		System.out.println(book.getBookId());
		return book;
	}

	@Override
	public List<BeanBook> findBooks(String bookName, String author, BeanBookType bookType)  {
		Session session = getCurrentSession();
		String hql = "from BeanBook where 1=1 and isDelete=0";
	    if(bookName!=null&&!("").equals(bookName)){
	        hql=hql+" and bookName like '%"+bookName+"%'";
	    }
	    if(author!=null&&!("").equals(author)){
	        hql=hql+" and author like '%"+author+"%'";
	    }
	    if(bookType!=null&&!("").equals(bookType)){
	        hql=hql+" and bookType='"+bookType.getBooktypeId()+"'";
	    }
	    List<BeanBook> books=session.createQuery(hql).list();
		
	    return books;
	}

}
