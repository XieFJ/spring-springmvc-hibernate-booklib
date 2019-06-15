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
import cn.edu.zucc.booklib.databean.BeanReaderType;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.pojo.BookPOJO;
import cn.edu.zucc.booklib.pojo.BookTypePOJO;
import cn.edu.zucc.booklib.pojo.ReaderTypePOJO;
import cn.edu.zucc.booklib.service.BookService;
import cn.edu.zucc.booklib.service.ReaderTypeService;
import sun.print.resources.serviceui_es;

@Controller
public class ReaderTypeController {

	@Autowired
	private ReaderTypeService readerTypeService;
	
	@RequestMapping(value="/addReaderType", method=RequestMethod.POST)
	@ResponseBody
	public String addReaderType(@RequestBody ReaderTypePOJO readerType) throws BooklibException {
		readerTypeService.addReaderType(readerType.getReadertypeName(), readerType.getLendLimitted(), readerType.getDueTime());
		return null;
	}

	@RequestMapping(value="/deleteReaderType/{readertypeId}", method=RequestMethod.GET)
	@ResponseBody
	public String deleteReaderType(@PathVariable("readertypeId") int readertypeId) throws BooklibException {
		readerTypeService.deleteReaderType(readertypeId);
		return null;
	}

	@RequestMapping(value="/modifyReaderType", method=RequestMethod.POST)
	@ResponseBody
	public String modifyReaderType(@RequestBody BeanReaderType readerType) throws BooklibException {
		readerTypeService.modifyReaderType(readerType.getReadertypeId(), readerType.getReadertypeName(), readerType.getLendLimitted(), readerType.getDueTime());
		return null;
	}

	@RequestMapping(value= "/findReaderType/{readertypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanReaderType> findType(@PathVariable("readertypeName") String readertypeName) throws BooklibException {
		return readerTypeService.fuzzyQueryTypeByName(readertypeName);
	}
	
	@RequestMapping(value= "/findReaderType", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanReaderType> findType2() throws BooklibException {
		return readerTypeService.fuzzyQueryTypeByName("");
	}
	
	@RequestMapping(value="/loadReaderTypes", method=RequestMethod.GET)
	@ResponseBody
//	public List<BeanReaderType> loadReaderTypes(@RequestBody String content) throws BooklibException {
	public List<BeanReaderType> loadReaderTypes() throws BooklibException {
		List<BeanReaderType> result = new ArrayList<>();
		result = readerTypeService.loadReaderTypes();
		return result;
	}
}
