package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbBuddhaImage;

@Repository("buddhaImageDao")
public class BuddhaImageDao extends AbstractDao<TbBuddhaImage> {

	public List<TbBuddhaImage> queryTbBuddhaImages(Integer start,Integer limit)throws Exception{
		String queryString  ="from TbBuddhaImage t order by t.imageId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbBuddhaImage> queryTbBuddhaImagesOfPhone(Integer start,Integer limit)throws Exception{
		String queryString  ="select new TbBuddhaImage(imageId,buddhaImageName,buddhaImageDesc,buddhaImagePath) " +
				"from TbBuddhaImage t order by t.imageId DESC";
		return list(queryString, start, limit);
	}
}
