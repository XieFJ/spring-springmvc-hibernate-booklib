package cn.edu.zucc.booklib.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import cn.edu.zucc.booklib.pojo.ReaderPOJO;
import cn.edu.zucc.booklib.pojo.SystemUserPOJO;
import cn.edu.zucc.booklib.service.ReaderService;
import cn.edu.zucc.booklib.service.UserService;
import sun.print.resources.serviceui_es;

@Controller
public class SystemUserController {

	@Autowired
	private UserService systemUserService;
	
	//增
	@RequestMapping(value="/addSystemUser/{userName}/{password}/{password2}", method=RequestMethod.POST)
	@ResponseBody
	public String addSystemUser(@PathVariable("userName") String userName, @PathVariable("password") String password, @PathVariable("password2") String password2) throws BooklibException {
		systemUserService.addSystemUser(userName, password, password2);
		return null;
	}

	//删
	@RequestMapping(value="/deleteSystemUser/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public String deleteSystemUser(@PathVariable("userId") int userId) throws BooklibException {
		systemUserService.deleteSystemUser(userId);
		return null;
	}
	
	//查（根据管理员名字和管理员类别）
	@RequestMapping(value="/findSystemUsers/{userName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanSystemUser> findSystemUsers(@PathVariable("userName") String userName) throws BooklibException {
		List<BeanSystemUser> result = new ArrayList<>();
		result = systemUserService.findSystemUsers(userName);
		return result;
	}
	
	@RequestMapping(value="/findSystemUsers", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanSystemUser> findSystemUsers() throws BooklibException {
		List<BeanSystemUser> result = new ArrayList<>();
		result = systemUserService.findSystemUsers(null);
		return result;
	}
	
	//展示所有管理员
	@RequestMapping(value="/loadUsers", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanSystemUser> loadAll() throws BooklibException {
		List<BeanSystemUser> result = new ArrayList<>();
		result = systemUserService.findAll();
		return result;
	}
}
