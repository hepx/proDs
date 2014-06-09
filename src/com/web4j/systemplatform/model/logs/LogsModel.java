package com.web4j.systemplatform.model.logs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.systemplatform.dao.logs.LogsDao;
import com.web4j.systemplatform.entity.TbLogs;
import com.web4j.systemplatform.pojo.logs.LogsPojo;

@Service("logsModel")
public class LogsModel {

	@Autowired
	private LogsDao logsDao;
	
	public void saveLogsPojo(LogsPojo logsPojo)throws Exception{
		TbLogs tbLogs=new TbLogs();
		BeanUtils.copyProperties(tbLogs, logsPojo);
		logsDao.saveTbLogs(tbLogs);
	}
	
	public void insertOneLog(String logs)throws Exception{
		TbLogs tbLogs= new TbLogs();
		tbLogs.setUser("admin");
		tbLogs.setIp("127.0.0.1");
		tbLogs.setLogs(logs);
		tbLogs.setCreateTime(new Date());
		logsDao.saveTbLogs(tbLogs);
	}
	
	public Long getLogsCounts(){
		return logsDao.getCounts();
	}
	
	public List<LogsPojo> queryLogsPojos(Integer start,Integer limit)throws Exception{
		List<TbLogs> tbLogs=logsDao.queryTbLogs(start,limit);
		List<LogsPojo> logsPojos=new ArrayList<LogsPojo>();
		for(TbLogs logs:tbLogs){
			LogsPojo logsPojo=new LogsPojo(logs.getId(), logs.getUser(), logs.getIp(), logs.getCreateTime(), logs.getLogs());
			logsPojos.add(logsPojo);
		}
		return logsPojos;
	}
	
	public void deleteLogsPojos(List<LogsPojo>logsPojos)throws Exception{
		List<TbLogs>tbLogs=new ArrayList<TbLogs>();
		for(LogsPojo logsPojo:logsPojos){
			TbLogs logs=new TbLogs(logsPojo.getId());
			tbLogs.add(logs);
		}
		logsDao.deleteTbLogs(tbLogs);
	}
}
