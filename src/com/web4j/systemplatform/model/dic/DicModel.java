package com.web4j.systemplatform.model.dic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.systemplatform.dao.dic.DicInfoDao;
import com.web4j.systemplatform.entity.TbDicInfo;
import com.web4j.systemplatform.pojo.dic.DicInfo;

@Service("dicModel")
public class DicModel {

	@Autowired
	private DicInfoDao dicInfoDao;

	public List<DicInfo> dicInfos = null;
	public DicInfo dicInfo = null;

	public List<DicInfo> queryDicInfos(String dicGroup) throws Exception {
		List<TbDicInfo> tbDicInfos = dicInfoDao.queryTbDicInfos(dicGroup);
		if (dicInfos != null && dicInfos.size() > 0 && dicInfos.get(0).getDicGroup().equals(dicGroup)) {
			return dicInfos;
		} else {
			if (tbDicInfos != null && tbDicInfos.size() > 0) {
				dicInfos = new ArrayList<DicInfo>();
				for (TbDicInfo t : tbDicInfos) {
					dicInfos.add(new DicInfo(t.getDicCode(), t.getDicValue(),t.getDicGroup()));
				}
				return dicInfos;
			} else {
				return null;
			}
		}
	}

	public DicInfo querydicInfo(String dicGroup, String dicValue)
			throws Exception {
		TbDicInfo tbDicInfo = dicInfoDao.queryTbDicInfo(dicGroup, dicValue);
		if (dicInfo != null && dicInfo.getDicGroup().equals(dicGroup)
				&& dicInfo.getDicValue().equals(dicValue)) {
			return dicInfo;
		} else {
			if (tbDicInfo != null) {
				return new DicInfo(tbDicInfo.getDicGroup(), tbDicInfo
						.getDicCode(), tbDicInfo.getDicValue(), tbDicInfo
						.getDicDesc());
			} else {
				return null;
			}

		}
	}
}
