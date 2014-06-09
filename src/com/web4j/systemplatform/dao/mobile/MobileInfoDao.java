package com.web4j.systemplatform.dao.mobile;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.systemplatform.entity.TbMobileInfo;

@Repository("mobileInfoDao")
public class MobileInfoDao extends AbstractDao<TbMobileInfo> {

	public void saveOrUpdateMobileInfo(TbMobileInfo tbMobileInfo)throws Exception{
		saveOrUpdate(tbMobileInfo);
	}
	
	public void saveOrUpdateMobileInfos(List<TbMobileInfo> tbMobileInfos)throws Exception{
		saveOrUpdateAll(tbMobileInfos);
	}
	
	public void deleteMobileInfo(TbMobileInfo tbMobileInfo)throws Exception{
		delete(tbMobileInfo);
	}
	
	public void deleteMobileInfos(List<TbMobileInfo> tbMobileInfos)throws Exception{
		delete(tbMobileInfos);
	}
	
	public List<TbMobileInfo> queryTbMobileInfos()throws Exception{
		return listAll();
	}
	
	public Long queryTbMobileInfoCount(TbMobileInfo tbMobileInfo){
		return counts(tbMobileInfo);
	}
	
	public List<TbMobileInfo>queryTbMobileInfos(TbMobileInfo tbMobileInfo,Integer start,Integer limit)throws Exception{
		//String queryString ="from TbMobileInfo order by createTime desc";
		//return mobileInfoDao.list(queryString, start, limit);
		return ListOnePage(tbMobileInfo, start, limit);
	}
}
