package cn.edu.zucc.booklib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.booklib.dao.BookTypeDAO;
import cn.edu.zucc.booklib.databean.BeanBookType;

@Repository
public class BookTypeDaoImpl implements BookTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addBookType(String booktypeName, String picture) {
		 Session session = getCurrentSession();
		 BeanBookType bookType = new BeanBookType();
		 bookType.setBooktypeName(booktypeName);
		 bookType.setPicture(picture);
		 session.save(bookType);
	}

	@Override
	public void deleteBookType(BeanBookType bookType) {
		 Session session = getCurrentSession();
		 bookType.setIsDelete(1);
		 session.update(bookType);
	}

	@Override
	public void modifyBookType(BeanBookType bookType, String newName, String picture) {
		Session session = getCurrentSession();
		if(newName!=null&&!("").equals(newName)) {
			bookType.setBooktypeName(newName);
		}
		if(picture!=null&&!("").equals(picture)) {
			bookType.setPicture(picture);;
		}
		session.update(bookType);
	}

	@Override
	public BeanBookType findTypeById(int booktypeId) {
		Session session = getCurrentSession();
		BeanBookType bookType = session.get(BeanBookType.class, booktypeId);
		if(bookType.getIsDelete()==1) return null;
		return bookType;
	}

	@Override
	public BeanBookType findTypeByName(String booktypeName) {
		Session session = getCurrentSession();
		String hql = "from BeanBookType where booktypeName = '"+booktypeName+"' and isDelete=0";
		List<BeanBookType> types = session.createQuery(hql).list();
		if(types.size()<=0)return null;
		BeanBookType bookType = types.get(0);
		return bookType;
	}

	@Override
	public List<BeanBookType> loadBookTypes() {
		Session session = getCurrentSession();
		String hql = "from BeanBookType where 1=1 and isDelete=0";
		List<BeanBookType> bookTypes = session.createQuery(hql).list();
		return bookTypes;
	}
	
	@Override
	public List<BeanBookType> fuzzyQueryTypeByName(String booktypeName) {
		Session session = getCurrentSession();
		String hql = "from BeanBookType where booktypeName like '%"+booktypeName+"%' and isDelete=0";
		List<BeanBookType> types = session.createQuery(hql).list();
		if(types.size()<=0)return null;
		return types;
	}

}
