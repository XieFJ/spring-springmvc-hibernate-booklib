package cn.edu.zucc.booklib.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zucc.booklib.bookshelf.Bookshelf;
import cn.edu.zucc.booklib.bookshelf.BookshelfItem;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.BookService;

@Controller
public class BookShelfController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/addItem/{bookId}", method=RequestMethod.GET)
	public String addBookshelf(@PathVariable("bookId") int bookId, HttpSession session) {
		Bookshelf bookshelf = (Bookshelf) session.getAttribute("bookshelf");
		if (bookshelf == null) {
			bookshelf = new Bookshelf();
			session.setAttribute("bookshelf", bookshelf);
		}
		try {
			BeanBook book = new BeanBook();
			book = bookService.findBookById(bookId);
			bookshelf.add(bookId, book);
		} catch (BooklibException e) {
			e.printStackTrace();
		}
		
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/detail";
	}
	
	@RequestMapping(value="/removeItem/{bookId}", method=RequestMethod.GET)
	public String removeBookshelf(@PathVariable("bookId") int bookId, HttpSession session) {
		Bookshelf bookshelf = (Bookshelf) session.getAttribute("bookshelf");
		if (bookshelf == null) {
			bookshelf = new Bookshelf();
			session.setAttribute("bookshelf", bookshelf);
		}
		bookshelf.remove(bookId);
		
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/bookshelf";
	}
	
	@RequestMapping(value="/showItem", method=RequestMethod.GET)
	@ResponseBody
	public Collection<BookshelfItem> showBookshelf(HttpSession session) {
		Bookshelf bookshelf = (Bookshelf) session.getAttribute("bookshelf");
		Collection<BookshelfItem> bookshelfItems = bookshelf.getItems();
		
		return bookshelfItems;
	}
}
