package cn.edu.zucc.booklib.service;

import java.util.List;

import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface UserService {
	public List<BeanSystemUser> findAll() throws BooklibException;
	
//	public void addSystemUser(String userName, String userType, String password, String password2) throws BooklibException;
	public void addSystemUser(String userName, String password, String password2) throws BooklibException;
	
	public void deleteSystemUser(int userId) throws BooklibException;
	
	public void changePwd(int userId, String oldPwd, String newPwd, String newPwd2) throws BooklibException;
	
	public BeanSystemUser findById(int userId) throws BooklibException;
	
//	public List<BeanSystemUser> findSystemUsers(String userName, String userType) throws BooklibException;
	public List<BeanSystemUser> findSystemUsers(String userName) throws BooklibException;
	
	public BeanSystemUser checklogin(int userId, String pwd) throws BooklibException;
	
	
}
