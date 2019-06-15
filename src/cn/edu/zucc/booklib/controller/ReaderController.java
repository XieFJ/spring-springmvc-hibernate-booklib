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

import cn.edu.zucc.booklib.databean.BeanBookType;
import cn.edu.zucc.booklib.databean.BeanReader;
import cn.edu.zucc.booklib.exception.BooklibException;
import cn.edu.zucc.booklib.pojo.ReaderPOJO;
import cn.edu.zucc.booklib.service.ReaderService;
import sun.print.resources.serviceui_es;

@Controller
public class ReaderController {

	@Autowired
	private ReaderService readerService;
	
//	@RequestMapping(value="/addReader", method=RequestMethod.POST)
//	@ResponseBody
//	public String addReader(@RequestBody ReaderPOJO reader) throws BooklibException {
//		readerService.addReader(reader.getReaderName(), reader.getReaderType().getReadertypeName());
//		return null;
//	}

	@RequestMapping(value="/addReader/{readerName}/{readertypeName}", method=RequestMethod.POST)
	@ResponseBody
	public String addReader(@PathVariable("readerName") String readerName, @PathVariable("readertypeName") String readertypeName) throws BooklibException {
		readerService.addReader(readerName, readertypeName);
		return null;
	}
	
	@RequestMapping(value="/deleteReader/{readerId}", method=RequestMethod.GET)
	@ResponseBody
	public String deleteReader(@PathVariable("readerId") int readerId) throws BooklibException {
		readerService.deleteReader(readerId);
		return null;
	}

	@RequestMapping(value="/modifyReader/{readerId}/{readerName}/{readertypeName}", method=RequestMethod.POST)
	@ResponseBody
	public String modifyReader(@PathVariable("readerId") int readerId, @PathVariable("readerName") String readerName, @PathVariable("readertypeName") String readertypeName) throws BooklibException {
		readerService.modifyReader(readerId, readerName, readertypeName);
		return null;
	}
	
	@RequestMapping(value="/modifyReader2/{readerId}/{readerName}", method=RequestMethod.POST)
	@ResponseBody
	public String modifyReader2(@PathVariable("readerId") int readerId, @PathVariable("readerName") String readerName) throws BooklibException {
		readerService.modifyReader(readerId, readerName, null);
		return null;
	}
	
	@RequestMapping(value="/modifyReader3/{readerId}/{readertypeName}", method=RequestMethod.POST)
	@ResponseBody
	public String modifyReader(@PathVariable("readerId") int readerId, @PathVariable("readertypeName") String readertypeName) throws BooklibException {
		readerService.modifyReader(readerId, null, readertypeName);
		return null;
	}
	
	@RequestMapping(value="/modifyReader4/{readerId}", method=RequestMethod.POST)
	@ResponseBody
	public String modifyReader(@PathVariable("readerId") int readerId) throws BooklibException {
		readerService.modifyReader(readerId, null, null);
		return null;
	}
	
	@RequestMapping(value="/findReaders2/{readerName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanReader> findReaders2(@PathVariable("readerName") String readerName) throws BooklibException {
		List<BeanReader> result = new ArrayList<>();
		result = readerService.findReaders(readerName, null);
		return result;
	}
	
	@RequestMapping(value="/findReaders3/{readertypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanReader> findReaders3(@PathVariable("readertypeName") String readertypeName) throws BooklibException {
		List<BeanReader> result = new ArrayList<>();
		result = readerService.findReaders(null, readertypeName);
		return result;
	}
	
	@RequestMapping(value="/findReaders4", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanReader> findReaders4() throws BooklibException {
		List<BeanReader> result = new ArrayList<>();
		result = readerService.findReaders(null, null);
		return result;
	}
	
	@RequestMapping(value="/findReaders/{readerName}/{readertypeName}", method=RequestMethod.POST)
	@ResponseBody
	public List<BeanReader> findReaders(@PathVariable("readerName") String readerName, @PathVariable("readertypeName") String readertypeName) throws BooklibException {
		List<BeanReader> result = new ArrayList<>();
		result = readerService.findReaders(readerName, readertypeName);
		return result;
	}

	@RequestMapping(value="/loadReaders", method=RequestMethod.GET)
	@ResponseBody
	public List<BeanReader> loadAll() throws BooklibException {
		return readerService.findReaders(null, null);
	}
	
}
