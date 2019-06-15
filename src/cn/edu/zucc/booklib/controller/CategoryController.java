package cn.edu.zucc.booklib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.pojo.BookTypePOJO;
import cn.edu.zucc.booklib.service.BookTypeService;

@Controller
public class CategoryController {

	@Autowired
	private BookTypeService bookTypeService;
	
	@RequestMapping(value="/loadCategory", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanBookType> loadAll() throws BooklibException {
		return bookTypeService.loadBookTypes();
	}
	
	@RequestMapping(value="/addBookType", method=RequestMethod.POST)
	@ResponseBody
	public String addBookType(@RequestBody BookTypePOJO bookType) throws BooklibException {
		bookTypeService.addBookType(bookType.getBooktypeName(), bookType.getPicture());
		return null;
	}

	@RequestMapping(value="/deleteBookType/{booktypeId}", method=RequestMethod.GET)
	@ResponseBody
	public String deleteBookType(@PathVariable("booktypeId") int booktypeId) throws BooklibException {
		bookTypeService.deleteBookType(booktypeId);
		return null;
	}

	@RequestMapping(value="/modifyBookType", method=RequestMethod.POST)
	@ResponseBody
	public String modifyBookType(@RequestBody BeanBookType bookType) throws BooklibException {
		bookTypeService.modifyBookType(bookType.getBooktypeId(), bookType.getBooktypeName(), bookType.getPicture());
		return null;
	}
	
	@RequestMapping(value="/findBookType/{booktypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBookType> findType(@PathVariable("booktypeName") String booktypeName) throws BooklibException {
		return bookTypeService.fuzzyQueryTypeByName(booktypeName);
	}
	
	@RequestMapping(value="/findBookType", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanBookType> findType2() throws BooklibException {
		return bookTypeService.fuzzyQueryTypeByName("");
	}
}
