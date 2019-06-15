package cn.edu.zucc.booklib.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.dao.BookDAO;
import cn.edu.zucc.booklib.dao.BookTypeDAO;
import cn.edu.zucc.booklib.dao.DetailDAO;
import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.databean.BeanDetail;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDAO bookDao;
	@Autowired
	private BookTypeDAO bookTypeDao;
	@Autowired
	private DetailDAO detailDao;
	
	@Override
	public void addBook(String bookName, String author, String publisher, int stockNumber, String introduction, String picture, String booktypeName)throws BooklibException {
		if (bookName==null || ("").equals(bookName)) throw new BooklibException("请输入书名！");
		if (author==null || ("").equals(author)) throw new BooklibException("请输入作者！");
		if (publisher==null || ("").equals(publisher)) throw new BooklibException("请输入出版社！");
		if(stockNumber<=0) throw new BooklibException("请输入库存量");
		if (introduction==null || ("").equals(introduction)) throw new BooklibException("请输入简介！");
		if (picture==null || ("").equals(picture)) throw new BooklibException("请输入图片地址！");
		BeanBookType bookType = this.bookTypeDao.findTypeByName(booktypeName);
		if (bookType==null || ("").equals(bookType)) throw new BooklibException("请选择图书类别！");
		
		this.bookDao.addBook(bookName, author, publisher, stockNumber, introduction, picture, bookType);
	}

	@Override
	public void deleteBook(int bookId) throws BooklibException {
		BeanBook book = this.bookDao.findBookById(bookId);
		if(book==null) throw new BooklibException("图书不存在");
		List<BeanDetail> details = this.detailDao.findDetails(null, null, null, book, null, -1.0);
		if(details.size()>0) throw new BooklibException("图书被借出，无法删除");
		
		this.bookDao.deleteBook(book);
	}

	@Override
	public void modifyBook(int bookId, String bookName, String author, String publisher, int stockNumber, String booktypeName, String picture, String introduction) throws BooklibException {
		BeanBook book = this.bookDao.findBookById(bookId);
		if(book==null) throw new BooklibException("图书不存在");
		BeanBookType bookType = null;
		if(booktypeName!=null) {
			bookType = this.bookTypeDao.findTypeByName(booktypeName);
			if(bookType==null)throw new BooklibException("类别不存在");
		}
		this.bookDao.modifyBook(book, bookName, author, publisher, stockNumber, bookType, picture, introduction);
	}

	@Override
	public BeanBook findBookById(int bookId) throws BooklibException {
		BeanBook book = this.bookDao.findBookById(bookId);
		if(book==null)throw new BooklibException("图书不存在");
		
		return book;
	}
	
	@Override
	public List<BeanBook> findBooks(String bookName, String author, String booktypeName) throws BooklibException {
		BeanBookType bookType = null;
		if(booktypeName!=null) {
			bookType = this.bookTypeDao.findTypeByName(booktypeName);
			if(bookType==null)throw new BooklibException("类别不存在");
		}
		return this.bookDao.findBooks(bookName, author, bookType);
	}

	@Override
	public void reduceStock(int bookId) throws BooklibException {
		BeanBook book = this.bookDao.findBookById(bookId);
		if(book==null||("".equals(book))) throw new BooklibException("图书不存在");
		int stock = book.getStockNumber();
		if(stock<=0) throw new BooklibException("库存不足");
		this.bookDao.modifyBook(book, null, null, null, stock-1, null, null, null);
	}

	@Override
	public void addStock(int bookId) throws BooklibException {
		BeanBook book = this.bookDao.findBookById(bookId);
		if(book==null||("".equals(book))) throw new BooklibException("图书不存在");
		int stock = book.getStockNumber();
		this.bookDao.modifyBook(book, null, null, null, stock+1, null, null, null);
	}
	
	@Override
	public List<BeanBook> fuzzyQueryBooks(String query) throws BooklibException {
		List<BeanBookType> bookType = this.bookTypeDao.fuzzyQueryTypeByName(query);
		List<BeanBook> books = new ArrayList<>();
		List<BeanBook> booksbyName = this.bookDao.findBooks(query, null, null);
		List<BeanBook> booksbyAuthor = this.bookDao.findBooks(null, query, null);
		List<BeanBook> booksbyTypeName = new ArrayList<>();
		if(bookType!=null) {
			for(BeanBookType type:bookType) {
				System.out.println(type.getBooktypeName());
				List<BeanBook> bookss = this.bookDao.findBooks(null, null, type);
				for(BeanBook b:bookss) {
					System.out.println("name:"+b.getBookName()+ " "+ b.getAuthor()+ " "+ b.getBookType().getBooktypeName());
					booksbyTypeName.add(b);
				}
			}
		}
		
		if(booksbyName!=null) {
			for(BeanBook b:booksbyName) {
				if(!books.contains(b)) {
					books.add(b);
				}
			}
		}
		if(booksbyAuthor!=null) {
			for(BeanBook b:booksbyAuthor){
				if(!books.contains(b)) {
					books.add(b);
				}
			}
		}
		if(booksbyTypeName!=null) {
			for(BeanBook b:booksbyTypeName){
				if(!books.contains(b)) {
					books.add(b);
				}
			}
		}
		for(BeanBook b:books)
    		System.out.println(b.getBookName()+ " "+ b.getAuthor()+ " "+ b.getBookType().getBooktypeName());
		if(books.size()<=0) return null;
		return books;
	}
}
