package cn.edu.zucc.booklib.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.BookService;
import cn.edu.zucc.booklib.service.DetailService;
import cn.edu.zucc.booklib.service.LendRecordService;

@Controller
public class DetailController {

	@Autowired
	private BookService bookService;
	@Autowired
	private DetailService detailService;
	
	@RequestMapping(value="/detail/{bookName}", method=RequestMethod.GET)
	public String loadAll(@PathVariable("bookName") String bookName, HttpSession session) {
		try {
			List<BeanBook> books = bookService.findBooks(bookName, null, null);
			session.setAttribute("bookDetail", books.get(0));
		} catch (BooklibException e) {
			e.printStackTrace();
		}
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/detail";
	}
	
	@RequestMapping(value="/loadDetail", method=RequestMethod.GET)
	@ResponseBody
	public BeanBook loadDetail(HttpSession session) {
		BeanBook result = (BeanBook) session.getAttribute("bookDetail");
		return result;
	}
	
	@RequestMapping(value="/loadRecord", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanDetail> loadRecord(HttpSession session) {
		BeanBook book = (BeanBook) session.getAttribute("bookDetail");
		List<BeanDetail> result = new ArrayList<>();
		try {
			result = detailService.findDetails(-1, null, null, book.getBookId(), -1, -1.0);
		} catch (BooklibException e) {
			
			e.printStackTrace();
		}
		return result;
	}
}
