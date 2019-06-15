package cn.edu.zucc.booklib.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.UserDAO;
import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public List<BeanSystemUser> findAll() throws BooklibException {
		
		return this.userDao.findAll();
	}

	@Override
//	public void addSystemUser(String userName, String userType, String password, String password2) throws BooklibException{
	public void addSystemUser(String userName, String password, String password2) throws BooklibException{
		if (userName==null || ("").equals(userName))
			throw new BooklibException("请输入用户名！");
//		if (userType==null || ("").equals(userType))
//			throw new BooklibException("请选择用户类型！");
		if (password==null || ("").equals(password))
			throw new BooklibException("请输入密码！");
		if (password2==null || ("").equals(password2))
			throw new BooklibException("请输入确认密码！");
		if (!password.equals(password2))
			throw new BooklibException("密码不一致！");
		
		this.userDao.addSystemUser(userName, password);
	}

	@Override
	public void deleteSystemUser(int userId) throws BooklibException{
		
		BeanSystemUser user = this.userDao.findById(userId);
		if (user == null)
			throw new BooklibException("用户不存在！");
		this.userDao.deleteSystemUser(user);
	}

	@Override
	public void changePwd(int userId, String oldPwd, String newPwd, String newPwd2) throws BooklibException{
		BeanSystemUser user = this.userDao.findById(userId);
		if (user == null)
			throw new BooklibException("用户不存在！");
		if (!user.getPassword().equals(oldPwd)) 
			throw new BooklibException("原始密码错误！");
		if (newPwd==null || ("").equals(newPwd))
			throw new BooklibException("请输入新密码！");
		if (!newPwd.equals(newPwd2))
			throw new BooklibException("密码不一致！");
		this.userDao.changePwd(user, newPwd);
	}

	@Override
	public BeanSystemUser findById(int userId) throws BooklibException{
		BeanSystemUser user = this.userDao.findById(userId);
		if (user == null)
			throw new BooklibException("用户不存在！");
		return user;
	}

	@Override
//	public List<BeanSystemUser> findSystemUsers(String userName, String userType) throws BooklibException{
	public List<BeanSystemUser> findSystemUsers(String userName) throws BooklibException{	
		List<BeanSystemUser> result = new ArrayList<>();
		result = this.userDao.findSystemUsers(userName);
		return result;
	}

	@Override
	public BeanSystemUser checklogin(int userId, String pwd) throws BooklibException{
		BeanSystemUser user = this.userDao.findById(userId);
		if (user == null)
			throw new BooklibException("用户不存在！");
		if (!user.getPassword().equals(pwd)) 
			throw new BooklibException("密码错误！");
		
		return user;
	}

}
