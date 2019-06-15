package cn.edu.zucc.booklib.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.booklib.dao.UserDAO;
import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<BeanSystemUser> findAll() {
		Session session = getCurrentSession();
		List<BeanSystemUser> result = new ArrayList<>();
		
		String hql = "from BeanSystemUser u where u.isDelete=0";
		result = session.createQuery(hql).list();
		
		return result;
	}

	@Override
//	public void addSystemUser(String userName, String userType, String password) {
	public void addSystemUser(String userName, String password) {
		Session session = getCurrentSession();
		BeanSystemUser user = new BeanSystemUser();
		user.setUserName(userName);
		user.setUserType("管理员");
		user.setPassword(password);
		user.setCreateDate(new Date(System.currentTimeMillis()));
		session.save(user);
	}

	@Override
	public void deleteSystemUser(BeanSystemUser user) {
		Session session = getCurrentSession();
		user.setIsDelete(1);
		session.update(user);	
	}

	@Override
	public void changePwd(BeanSystemUser user, String newPwd) {
		Session session = getCurrentSession();
		user.setPassword(newPwd);
		session.update(user);
	}

	@Override
	public BeanSystemUser findById(int userId) {
		Session session = getCurrentSession();
		BeanSystemUser user = session.get(BeanSystemUser.class, userId);
		if (user == null || user.getIsDelete() == 1)
			return null;
		
		return user;
	}

	@Override
//	public List<BeanSystemUser> findSystemUsers(String userName, String userType) {
	public List<BeanSystemUser> findSystemUsers(String userName) {
		Session session = getCurrentSession();
		List<BeanSystemUser> result = new ArrayList<>();
		String hql = "from BeanSystemUser where 1=1 and isDelete=0";
	    if(userName!=null&&!("").equals(userName)){
	        hql=hql+" and userName='"+userName+"'";
	    }
//	    if(userType!=null&&!("").equals(userType)){
//	        hql=hql+" and userType='"+userType+"'";
//	    }
	    result = session.createQuery(hql).list();
		return result;
	}

}
