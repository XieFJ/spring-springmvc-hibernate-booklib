package cn.edu.zucc.booklib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.booklib.dao.ReaderTypeDAO;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.databean.BeanReaderType;

@Repository
public class ReaderTypeDaoImpl implements ReaderTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	 
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addReaderType(String readertypeName, int lendLimitted, int dueTime) {
		Session session = getCurrentSession();
		BeanReaderType readerType = new BeanReaderType();
		readerType.setReadertypeName(readertypeName);
		readerType.setLendLimitted(lendLimitted);
		readerType.setDueTime(dueTime);
		session.save(readerType);
	}

	@Override
	public void deleteReaderType(BeanReaderType readerType) {
		Session session = getCurrentSession();
		readerType.setIsDelete(1);
		session.update(readerType);
	}

	@Override
	public void modifyReaderType(BeanReaderType readerType, String newName, int newLimit, int newDueTime) {
		Session session = getCurrentSession();
		if(newName!=null&&!("").equals(newName)) {
			 readerType.setReadertypeName(newName);
		}
		if(newLimit>0) {
			 readerType.setLendLimitted(newLimit);
		}
		if(newDueTime>0) {
			 readerType.setDueTime(newDueTime);
		}
		session.update(readerType);
	}

	@Override
	public BeanReaderType findTypeById(int readertypeId) {
		Session session = getCurrentSession();
		BeanReaderType readerType = session.get(BeanReaderType.class, readertypeId);
		if(readerType.getIsDelete()==1) return null;
		return readerType;
	}

	@Override
	public BeanReaderType findTypeByName(String readertypeName) {
		Session session = getCurrentSession();
		String hql = "from BeanReaderType where readertypeName = '"+readertypeName+"' and isDelete=0";
		List<BeanReaderType> types = session.createQuery(hql).list();
		if(types.size()<=0)return null;
		BeanReaderType readerType = types.get(0);
		
		return readerType;
	}

	@Override
	public List<BeanReaderType> loadReaderTypes() {
		Session session = getCurrentSession();
		String hql = "from BeanReaderType where 1=1 and isDelete=0";
		List<BeanReaderType> readerTypes = session.createQuery(hql).list();
		return readerTypes;
	}
	
	@Override
	public List<BeanReaderType> fuzzyQueryTypeByName(String readertypeName) {
		Session session = getCurrentSession();
		String hql = "from BeanReaderType where readertypeName like '%"+readertypeName+"%' and isDelete=0";
		
		List<BeanReaderType> types = session.createQuery(hql).list();
		if(types.size()<=0)return null;
		return types;
	}

}
