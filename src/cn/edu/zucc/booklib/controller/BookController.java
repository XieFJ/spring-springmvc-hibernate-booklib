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

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.pojo.BookPOJO;
import cn.edu.zucc.booklib.pojo.BookTypePOJO;
import cn.edu.zucc.booklib.service.BookService;
import sun.print.resources.serviceui_es;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/books/{typeName}", method=RequestMethod.GET)
	public String loadAType(@PathVariable("typeName") String typeName, HttpSession session) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(null, null, typeName);
		session.setAttribute("books", result);
		session.setAttribute("typeName", typeName);
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/books";
	}
	
	@RequestMapping(value="/loadBook", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanBook> loadBooks(HttpSession session) {
		List<BeanBook> result = new ArrayList<>();
		result = (List<BeanBook>) session.getAttribute("books");
		return result;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> loadBooks(@RequestBody String content) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.fuzzyQueryBooks(content);
		return result;
	}
	
	@RequestMapping(value="/addBook/{bookName}/{booktypeName}/{author}/{publisher}/{stockNumber}/{picture}/{introduction}", method=RequestMethod.POST)
	@ResponseBody
	public String addBook(@PathVariable("bookName") String bookName, @PathVariable("booktypeName") String booktypeName, @PathVariable("author") String author, @PathVariable("publisher") String publisher, @PathVariable("stockNumber") int stockNumber, @PathVariable("picture") String picture, @PathVariable("introduction") String introduction) throws BooklibException {
		bookService.addBook(bookName, author, publisher, stockNumber, introduction, picture, booktypeName);
		return null;
	}

	@RequestMapping(value="/deleteBook/{bookId}", method=RequestMethod.GET)
	public void deleteBook(@PathVariable("bookId") int bookId) throws BooklibException {
		bookService.deleteBook(bookId);
	}

	@RequestMapping(value="/modifyBook", method=RequestMethod.POST)
	public void modifyBook(@RequestBody BeanBook book) throws BooklibException {
		bookService.modifyBook(book.getBookId(), book.getBookName(), book.getAuthor(), book.getPublisher(), book.getStockNumber(), null, book.getPicture(), book.getIntroduction());
	}
	
	@RequestMapping(value="/findBooks/{bookName}/{author}/{booktypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks(@PathVariable("bookName") String bookName,@PathVariable("author") String author,@PathVariable("booktypeName") String booktypeName) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(bookName, author, booktypeName);
		return result;
	}
	
	@RequestMapping(value="/findBooks2/{bookName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks2(@PathVariable("bookName") String bookName) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(bookName, null, null);
		return result;
	}
	
	@RequestMapping(value="/findBooks3/{author}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks3(@PathVariable("author") String author) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(null, author, null);
		return result;
	}
	
	@RequestMapping(value="/findBooks4/{booktypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks4(@PathVariable("booktypeName") String booktypeName) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(null, null, booktypeName);
		return result;
	}
	
	@RequestMapping(value="/findBooks5/{bookName}/{author}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks5(@PathVariable("bookName") String bookName,@PathVariable("author") String author) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(bookName, author, null);
		return result;
	}
	
	@RequestMapping(value="/findBooks6/{bookName}/{booktypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks6(@PathVariable("bookName") String bookName, @PathVariable("booktypeName") String booktypeName) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(bookName, null, booktypeName);
		return result;
	}
	
	@RequestMapping(value="/findBooks7/{author}/{booktypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks7(@PathVariable("author") String author,@PathVariable("booktypeName") String booktypeName) throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(null, author, booktypeName);
		return result;
	}
	
	@RequestMapping(value="/findBooks8", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBook> findBooks8() throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(null, null, null);
		return result;
	}
	
	@RequestMapping(value="/loadAllBooks", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanBook> loadAllBooks() throws BooklibException {
		List<BeanBook> result = new ArrayList<>();
		result = bookService.findBooks(null, null, null);
		return result;
	}
}
