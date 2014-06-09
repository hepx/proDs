package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.BuddhaWeiboDao;
import com.web4j.sxdplatform.entity.TbBuddhaWeibo;
import com.web4j.sxdplatform.pojo.BuddhaWeibo;

@Service("buddhaWeiboModel")
public class BuddhaWeiboModel {

	@Autowired
	private BuddhaWeiboDao buddhaWeiboDao;
	
	public void saveOrUpdateBuddhaWeibos(List<BuddhaWeibo>buddhaWeibos)throws Exception{
		List<TbBuddhaWeibo> tbBuddhaWeibos = new ArrayList<TbBuddhaWeibo>();
		for(BuddhaWeibo source : buddhaWeibos){
			TbBuddhaWeibo target = new TbBuddhaWeibo();
			BeanUtils.copyProperties(source, target);
			if(target.getCreateTime()==null){
				target.setCreateTime(new Date());
			}
			tbBuddhaWeibos.add(target);
		}
		buddhaWeiboDao.saveOrUpdateAll(tbBuddhaWeibos);
	}
	
	public List<BuddhaWeibo> queryBuddhaWeibos(Integer start,Integer limit)throws Exception{
		List<TbBuddhaWeibo> tbBuddhaWeibos=buddhaWeiboDao.quereyTbBuddhaWeibos(start, limit);
		List<BuddhaWeibo> buddhaWeibos =new ArrayList<BuddhaWeibo>();
		for(TbBuddhaWeibo source : tbBuddhaWeibos){
			BuddhaWeibo target =new BuddhaWeibo();
			BeanUtils.copyProperties(source, target);
			buddhaWeibos.add(target);
		}
		return buddhaWeibos;
	}
	
	public Long getBuddhaWeiboCount(){
		return buddhaWeiboDao.counts();
	}
	
	public void deleteBuddhaWeibos(List<BuddhaWeibo>buddhaWeibos)throws Exception{
		for(BuddhaWeibo buddhaWeibo:buddhaWeibos){
			if(buddhaWeibo.getWbId()!=null){
				buddhaWeiboDao.deleteById(buddhaWeibo.getWbId());
			}
		}
	}
	
	/*********************手机调用***********************/
	public List<TbBuddhaWeibo> queryTbBuddhaWeibosOfPhone(Integer start,Integer limit)throws Exception{
		return buddhaWeiboDao.quereyTbBuddhaWeibeOfPhone(start, limit);
	}
}
