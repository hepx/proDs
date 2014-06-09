package com.web4j.systemplatform.dao.logs;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.systemplatform.entity.TbLogs;

@Repository("logsDao")
public class LogsDao extends AbstractDao<TbLogs> {

	public void saveTbLogs(TbLogs tblogs)throws Exception{
		save(tblogs);
	}
	public Long getCounts(){
		return counts();
	}
	
	public List<TbLogs> queryTbLogs(Integer start,Integer limit)throws Exception{
		String queryString="from TbLogs order by createTime DESC";
		return list(queryString, start, limit);
	}
	
	public void deleteTbLogs(List<TbLogs>tbLogs)throws Exception{
		delete(tbLogs);
	}
	
}
