package com.web4j.systemplatform.dao.dic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.systemplatform.entity.TbDicInfo;

@Repository("dicInfoDao")
public class DicInfoDao extends AbstractDao<TbDicInfo> {

	public List<TbDicInfo> queryTbDicInfos(String dicGroup)throws Exception{
		String hql="from TbDicInfo T where T.dicGroup=?";
		return list(hql, new String[]{dicGroup});
	}
	
	public TbDicInfo queryTbDicInfo(String dicGroup,String dicValue)throws Exception{
		String hql="from TbDicInfo T where T.dicGroup=? and dicValue=?";
		List<TbDicInfo> tbDicInfos=list(hql, new String[]{dicGroup,dicValue});
		if(tbDicInfos!=null&&tbDicInfos.size()>0){
			return tbDicInfos.get(0);
		}else{
			return null;
		}
	}
}
