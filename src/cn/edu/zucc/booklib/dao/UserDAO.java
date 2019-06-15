package cn.edu.zucc.booklib.dao;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface UserDAO {
	public List<BeanSystemUser> findAll() ;
	
//	public void addSystemUser(String userName, String userType, String password) ;
	public void addSystemUser(String userName, String password) ;
	
	public void deleteSystemUser(BeanSystemUser user) ;
	
	public void changePwd(BeanSystemUser user, String newPwd) ;
	
	public BeanSystemUser findById(int userId) ;
	
//	public List<BeanSystemUser> findSystemUsers(String userName, String userType) ;
	public List<BeanSystemUser> findSystemUsers(String userName) ;
}
