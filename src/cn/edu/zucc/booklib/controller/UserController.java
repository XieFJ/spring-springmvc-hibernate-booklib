package cn.edu.zucc.booklib.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.pojo.SystemUserPOJO;
import cn.edu.zucc.booklib.pojo.UserPOJO;
import cn.edu.zucc.booklib.service.ReaderService;
import cn.edu.zucc.booklib.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ReaderService readerService;
	
	//跳转到登录页面接受的是GET方式提交的方法
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
	
	//用户登录接受的是POST方式提交的方法
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public UserPOJO login(@RequestBody UserPOJO user, HttpSession session){
		int userId = user.getUserId();
		String userpwd = user.getUserPwd();
		String userType = user.getUserType();
		
		System.out.println("userId:" + user.getUserId() + "   userpwd:" + user.getUserPwd() + "   userType:"+user.getUserType());
		if("".equals(userType)) {
			user.setResultInfo("类型为空");
			return user;
		}
		if("管理员".equals(userType)) {
			BeanSystemUser bUser;
			try {
				bUser = userService.checklogin(userId, userpwd);
				user.setUserName(bUser.getUserName());
				session.setAttribute("USER_SESSION", user);
			} catch (BooklibException e) {
				user.setResultInfo(e.getMessage());
				return user;
			}
		}
		if("读者".equals(userType)) {
			BeanReader reader;
			try {
				reader = readerService.checklogin(userId, userpwd);
				user.setUserName(reader.getReaderName());
				session.setAttribute("USER_SESSION", user);
			} catch (BooklibException e) {
				user.setResultInfo(e.getMessage());
				return user;
			}	
		}
		
		return user;	
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:index";
	}
	
	@RequestMapping("/checkUser")
	@ResponseBody
	public UserPOJO checkUser(HttpSession session) {
		UserPOJO user = (UserPOJO) session.getAttribute("USER_SESSION");
		
		return user;
	}

}
