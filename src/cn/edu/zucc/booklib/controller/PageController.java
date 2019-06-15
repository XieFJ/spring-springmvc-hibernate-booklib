package cn.edu.zucc.booklib.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

	@RequestMapping("")
	public String handleRequest(HttpServletRequest request) throws Exception {
		return "test";
	}

//	@RequestMapping(value="/login")
//	public String handleRequest1(HttpServletRequest request) throws Exception {
//		return "login";
//	}	
	
	@RequestMapping(value="/index")
	public String handleRequest2(HttpServletRequest request) throws Exception {
		return "index";
	}
	
	@RequestMapping("/category")
	public String handleRequest3(HttpServletRequest request) throws Exception {
		return "category";
	}
	
	@RequestMapping("/books")
	public String handleRequest4(HttpServletRequest request) throws Exception {
		return "books";
	}
	
	@RequestMapping("/detail")
	public String handleRequest5(HttpServletRequest request) throws Exception {
		return "detail";
	}
	
	@RequestMapping("/bookshelf")
	public String handleRequest6(HttpServletRequest request) throws Exception {
		return "bookshelf";
	}
	
	@RequestMapping("/lendrecord")
	public String handleRequest7(HttpServletRequest request) throws Exception {
		return "lendrecord";
	}
	
	@RequestMapping("/manage")
	public String handleRequest8(HttpServletRequest request) throws Exception {
		return "manage";
	}
	
	@RequestMapping("/chooseTab")
	@ResponseBody
	public int handleRequest9(HttpServletRequest request) throws Exception {
		return 1;
	}
}
