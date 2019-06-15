package cn.edu.zucc.booklib.dao;

import java.util.Date;
import java.util.List;

import cn.edu.zucc.booklib.databean.BeanBook;
import cn.edu.zucc.booklib.databean.BeanLendRecord;
import cn.edu.zucc.booklib.databean.BeanReader;

public interface LendRecordDAO {

	public BeanLendRecord addLendRecord(BeanReader reader);
	
	public BeanLendRecord findById(int lendrecordId);
	
	public List<BeanLendRecord> findRecords(BeanReader reader, Date lendDate);

	
}
