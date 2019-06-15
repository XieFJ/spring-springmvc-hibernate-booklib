package cn.edu.zucc.booklib.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanSystemUser;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.pojo.UserPOJO;
import cn.edu.zucc.booklib.service.BookService;
import cn.edu.zucc.booklib.service.DetailService;
import cn.edu.zucc.booklib.service.LendRecordService;
import cn.edu.zucc.booklib.service.ReaderService;

@Controller
public class LendRecordController {

	@Autowired
	private BookService bookService;
	@Autowired
	private LendRecordService lendRecordService;
	@Autowired
	private DetailService detailService;
	@Autowired
	private ReaderService readerService;
	
	@RequestMapping(value="/borrow", method=RequestMethod.GET)
	public String borrow(HttpSession session) throws BooklibException {
		Bookshelf bookshelf = (Bookshelf) session.getAttribute("bookshelf");
		UserPOJO user = (UserPOJO) session.getAttribute("USER_SESSION");
		int userId = user.getUserId();
		if (bookshelf != null) {
			session.removeAttribute("bookshelf");
			for(BookshelfItem item:bookshelf.getItems()) {
				BeanBook book = item.getItem();
				for(int i=0; i<item.getQuantity(); i++) {
					if(readerService.addLendNum(userId, 1)==1) {
						bookService.reduceStock(book.getBookId());
					}
				}
				BeanLendRecord record = lendRecordService.addLendRecord(userId);
				for(int i=0; i<item.getQuantity(); i++) {
					detailService.addBookDetail(userId, book.getBookId(), record.getLendrecordId());
				}
			}
			
		}
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/lendrecord";
	}
	
	@RequestMapping(value="/showRecord", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanDetail> showDetailsOfAReader(HttpSession session) throws BooklibException {
		UserPOJO user = (UserPOJO) session.getAttribute("USER_SESSION");
		int userId = user.getUserId();
		List<BeanDetail> lendDetails = detailService.findDetails(userId, null, null, -1, -1, -1.0);
		
		return lendDetails;
	}
	
	@RequestMapping(value="/renew/{detailId}", method=RequestMethod.GET)
	public String renewBook(@PathVariable("detailId") int detailId) throws BooklibException {
		detailService.renewDueDate(detailId);
		
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/lendrecord";
	}
	
	@RequestMapping(value="/return/{detailId}", method=RequestMethod.GET)
	public String returnBook(@PathVariable("detailId") int detailId) throws BooklibException {
		int bookId = detailService.findById(detailId).getBook().getBookId();
		detailService.calPenalty(detailId);
		detailService.returnBook(detailId);
		bookService.addStock(bookId);
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/lendrecord";
	}
	
	@RequestMapping(value="/calPenalty", method=RequestMethod.GET)
	public String calPenalty() throws BooklibException {
		List<BeanDetail> details = detailService.findDetails(-1, null, null, -1, -1, -1.0);
		for(BeanDetail detail:details) {
			detailService.calPenalty(detail.getDetailId());
		}
		
		return "redirect:http://localhost:18080/spring-springmvc-hibernate-booklib/lendrecord";
	}
	
	@RequestMapping(value="/loadAllDetails", method=RequestMethod.GET)
	@ResponseBody	
	public List<BeanDetail> loadAllDetails() throws BooklibException {
		List<BeanDetail> details = detailService.findDetails(-1, null, null, -1, -1, -1.0);
		for(BeanDetail detail:details) {
			detailService.calPenalty(detail.getDetailId());
		}
		
		return details;
	}
	
	@RequestMapping(value="/findDetails/{bookId}/{readerId}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanDetail> findDetails(@PathVariable("bookId") int bookId,@PathVariable("readerId") int readerId) throws BooklibException {
		List<BeanDetail> result = new ArrayList<>();
		result = detailService.findDetails(readerId, null, null, bookId, -1, -1.0);
		return result;
	}
	
	@RequestMapping(value="/findDetails2/{bookId}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanDetail> findDetails2(@PathVariable("bookId") int bookId) throws BooklibException {
		List<BeanDetail> result = new ArrayList<>();
		result = detailService.findDetails(-1, null, null, bookId, -1, -1.0);
		return result;
	}
	
	@RequestMapping(value="/findDetails3/{readerId}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanDetail> findDetails3(@PathVariable("readerId") int readerId) throws BooklibException {
		List<BeanDetail> result = new ArrayList<>();
		result = detailService.findDetails(readerId, null, null, -1, -1, -1.0);
		return result;
	}
	
	@RequestMapping(value="/findDetails4", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanDetail> findDetails4() throws BooklibException {
		List<BeanDetail> result = new ArrayList<>();
		result = detailService.findDetails(-1, null, null, -1, -1, -1.0);
		return result;
	}
}
