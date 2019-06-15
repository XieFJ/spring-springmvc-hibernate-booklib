package cn.edu.zucc.booklib.service;

import java.util.Date;
import java.util.List;

import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.exception.BooklibException;

public interface LendRecordService {
	
	public BeanLendRecord addLendRecord(int readerId)throws BooklibException ;
	
	public BeanLendRecord findById(int lendrecordId)throws BooklibException ;
	
	public List<BeanLendRecord> findRecords(int readerId, Date lendDate)throws BooklibException ;

}
