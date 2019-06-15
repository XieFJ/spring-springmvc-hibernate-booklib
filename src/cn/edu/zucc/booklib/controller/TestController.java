//package cn.edu.zucc.booklib.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import cn.edu.zucc.booklib.databean.BeanBook;
//import cn.edu.zucc.booklib.databean.BeanDetail;
//import cn.edu.zucc.booklib.databean.BeanLendRecord;
//import cn.edu.zucc.booklib.databean.BeanReader;
//import cn.edu.zucc.booklib.databean.BeanReaderType;
//import cn.edu.zucc.booklib.databean.BeanSystemUser;
//import cn.edu.zucc.booklib.service.BookService;
//import cn.edu.zucc.booklib.service.BookTypeService;
//import cn.edu.zucc.booklib.service.DetailService;
//import cn.edu.zucc.booklib.service.LendRecordService;
//import cn.edu.zucc.booklib.service.ReaderService;
//import cn.edu.zucc.booklib.service.ReaderTypeService;
//import cn.edu.zucc.booklib.service.UserService;
//
//@Controller
//public class TestController {
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private BookTypeService bookTypeService;
//    @Autowired
//    private DetailService detailService;
//    @Autowired
//    private LendRecordService lendRecordService;
//    @Autowired
//    private ReaderService readerService;
//    @Autowired
//    private ReaderTypeService readerTypeService;
//    @Autowired
//    private UserService userService;
//	
//	@RequestMapping(value="/addBookType")
//    public String handleRequest1(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookTypeService.addBookType("科幻a","sd");
////    	bookTypeService.modifyBookType(21, "科技");
////    	bookTypeService.loadBookTypes();
////    	System.out.println(bookTypeService.loadBookTypes().get(0).getBooktypeName());
////    	bookTypeService.deleteBookType(21);
//		return "test";
//	}
//	@RequestMapping(value="/addBook")
//    public String handleRequest2(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.addBook("aa", "aa", "aa",3, "introduction", "picture", "科幻a");
//		return "test";
//	}
//	@RequestMapping(value="/deleteBook")
//    public String handleRequest3(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.deleteBook(551);
//		return "test";
//	}
//	@RequestMapping(value="/modifyBook")
//    public String handleRequest4(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.modifyBook(556, "hahaha", null, null, -1, null);
//		return "test";
//	}
//	@RequestMapping(value="/findBooks")
//    public String handleRequest5(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	List<BeanBook> book = bookService.findBooks(null, null, "古典名著");
//    	for(BeanBook b:book)
//    		System.out.println(b.getBookName());
//		return "test";
//	}
//
//
//	
//	
////	
//	@RequestMapping(value="/borrowBook")
//    public String handleRequest66(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.reduceStock(555);
//    	bookService.reduceStock(556);
//		BeanLendRecord record = lendRecordService.addLendRecord(1);
//    	detailService.addBookDetail(1, 555, record.getLendrecordId());
//    	detailService.addBookDetail(1, 556, record.getLendrecordId());
//    	return "test";
//	}
//	@RequestMapping(value="/borrowBook2")
//    public String handleRequest62(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.reduceStock(555);
//		BeanLendRecord record = lendRecordService.addLendRecord(2);
//    	detailService.addBookDetail(1, 555, record.getLendrecordId());
//    	return "test";
//	}
//	@RequestMapping(value="/borrowBook3")
//    public String handleRequest63(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.reduceStock(556);
//		BeanLendRecord record = lendRecordService.addLendRecord(1);
//    	detailService.addBookDetail(1, 556, record.getLendrecordId());
//    	return "test";
//	}
//	@RequestMapping(value="/borrowBook4")
//    public String handleRequest64(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	bookService.reduceStock(552);
//		BeanLendRecord record = lendRecordService.addLendRecord(1);
//    	detailService.addBookDetail(1, 552, record.getLendrecordId());
//    	return "test";
//	}
//	@RequestMapping(value="/findRecords")
//    public String handleRequest67(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	 
//    	List<BeanLendRecord> record = lendRecordService.findRecords(1, null);
//    	for(BeanLendRecord r:record)
//    		System.out.println(r.getLendrecordId());
//		return "test";
//	}
//	
//
//	@RequestMapping(value="/returnBook")
//    public String handleRequest6(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	detailService.deleteBookDetail(9);
//		return "test";
//	}
//	@RequestMapping(value="/findById")
//    public String handleRequest7(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	BeanDetail detail = detailService.findById(9);
//    	System.out.println(detail.getDetailId());
//		return "test";
//	}
//	@RequestMapping(value="/findDetails")
//    public String handleRequest8(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	List<BeanDetail> details = detailService.findDetails(1, null, null, -1, -1, -1.0);
//    	for(BeanDetail d:details) System.out.println(d.getDetailId());
//		return "test";
//	}
//	@RequestMapping(value="/findDetails2")
//    public String handleRequest88(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    	String datestr = "2019-06-03";
//		Date date = simpleDateFormat.parse(datestr);
//		List<BeanDetail> details = detailService.findDetails(-1, date, null, -1, -1, -1.0);
//    	for(BeanDetail d:details) System.out.println(d.getDetailId());
//		return "test";
//	}
//	@RequestMapping(value="/findDetails3")
//    public String handleRequest78(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	List<BeanDetail> details = detailService.findDetails(-1, null, null, -1, -1, 0.0);
//    	for(BeanDetail d:details) System.out.println(d.getDetailId());
//		
//    	return "test";
//	}
//	@RequestMapping(value="/findDetails4")
//    public String handleRequest89(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	List<BeanDetail> details = detailService.findDetails(-1, null, null, 556, -1, -1.0);
//    	for(BeanDetail d:details) System.out.println(d.getDetailId());
//		
//    	
//    	
//		return "test";
//	}
//	@RequestMapping(value="/renewDueDate")
//    public String handleRequest9(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	detailService.renewDueDate(9);
//		return "test";
//	}
//	@RequestMapping(value="/calPenalty")
//    public String handleRequest10(HttpServletRequest request) throws Exception {
//    	request.setAttribute("msg", "hello world");
//    	double p = detailService.calPenalty(9);
//    	System.out.println(p);
//		return "test";
//	}
//	
//	
//	
//
////	@RequestMapping(value="/addReader")
////    public String handleRequest16(HttpServletRequest request) throws Exception {
////    	request.setAttribute("msg", "hello world");
////    	readerService.addReader("hxy", "学生");
////    	return "test";
////	}
////	@RequestMapping(value="/deleteReader")
////    public String handleRequest17(HttpServletRequest request) throws Exception {
////    	request.setAttribute("msg", "hello world");
////    	readerService.deleteReader(1);
////    	return "test";
////	}
////	@RequestMapping(value="/modifyReader")
////    public String handleRequest18(HttpServletRequest request) throws Exception {
////    	request.setAttribute("msg", "hello world");
////    	readerService.modifyReader(1, "xfj", null);
////    	return "test";
////	}
////	@RequestMapping(value="/findReaders")
////    public String handleRequest19(HttpServletRequest request) throws Exception {
////    	request.setAttribute("msg", "hello world");
////    	List<BeanReader> reader = readerService.findReaders(null, "学生");
////    	for(BeanReader r:reader) System.out.println(r.getReaderName());
////    	return "test";
////	}
////	@RequestMapping(value="/changePwd2")
////    public String handleRequest20(HttpServletRequest request) throws Exception {
////    	request.setAttribute("msg", "hello world");
////    	readerService.changePwd(1, "123456", "31601186", "31601186");
////    	return "test";
////	}
////	@RequestMapping(value="/checklogin2")
////    public String handleRequest21(HttpServletRequest request) throws Exception {
////    	request.setAttribute("msg", "hello world");
////    	readerService.checklogin(1, "adsdf");
////    	
////    	return "test";
////	}
//}
