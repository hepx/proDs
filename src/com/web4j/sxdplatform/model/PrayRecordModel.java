package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.PrayRecordDao;
import com.web4j.sxdplatform.entity.TbArticle;
import com.web4j.sxdplatform.entity.TbBuddhaImage;
import com.web4j.sxdplatform.entity.TbPrayRecord;
import com.web4j.sxdplatform.pojo.PrayRecord;

@Service("prayRecordModel")
public class PrayRecordModel {

	@Autowired
	private PrayRecordDao prayRecordDao;
	
	public List<PrayRecord> queryPrayRecords(Integer start,Integer limit)throws Exception{
		List<TbPrayRecord> tbPrayRecords = prayRecordDao.quereyTbPrayRecords(start, limit);
		return prayRecordTransform(tbPrayRecords);
	}
	
	public List<PrayRecord> quereyPrayRecordByUser(String xyNo,String prayType,Integer start,Integer limit)throws Exception{
		List<TbPrayRecord> tbTbPrayRecords=prayRecordDao.quereyTbPrayRecordsByUser(xyNo,prayType,start, limit);
		return prayRecordTransform(tbTbPrayRecords);
	}
	
	public List statByPrayType(String xyNo)throws Exception{
		return prayRecordDao.statByPrayType(xyNo);
	}
	
	public Long getPrayRecordCount(){
		return prayRecordDao.counts();
	}
	
	public void deletePrayRecord(Long prayId)throws Exception{
		prayRecordDao.deleteById(prayId);
	}
	
	public void deletePrayRecords(List<PrayRecord> prayRecords)throws Exception{
		for(PrayRecord prayRecord:prayRecords){
			if(prayRecord.getPrayId()!=null&&!"".equals(prayRecord.getPrayId())){
				prayRecordDao.deleteById(prayRecord.getPrayId());
			}
		}
	}
	
	public TbPrayRecord quereyTbPrayRecordById(Long prayId)throws Exception{
		return prayRecordDao.get(prayId);
	}
	
	public void createPrayRecord(PrayRecord prayRecord,Long budddhaImageId,Long articleId)throws Exception{
		TbPrayRecord tbPrayRecord=new TbPrayRecord();
		BeanUtils.copyProperties(prayRecord, tbPrayRecord);
		if(budddhaImageId!=null){
			tbPrayRecord.setTbBuddhaImage(new TbBuddhaImage(budddhaImageId));
		}
		if(articleId!=null){
			tbPrayRecord.setTbArticle(new TbArticle(articleId));
		}
		prayRecordDao.save(tbPrayRecord);
	}
	
	public void updateTbPrayRecord(TbPrayRecord tbPrayRecord)throws Exception{
		prayRecordDao.update(tbPrayRecord);
	}
	
	private List<PrayRecord> prayRecordTransform(List<TbPrayRecord> tbPrayRecords){
		List<PrayRecord> prayRecords=new ArrayList<PrayRecord>();
		for(TbPrayRecord source : tbPrayRecords){
			PrayRecord target=new PrayRecord();
			BeanUtils.copyProperties(source, target);
			//佛像
			TbBuddhaImage tbBuddhaImage=source.getTbBuddhaImage();
			if(tbBuddhaImage!=null){
				target.setBuddhaName(tbBuddhaImage.getBuddhaImageName());
				target.setBuddhaImagePath(tbBuddhaImage.getBuddhaImagePath());
			}
			//物品
			TbArticle tbArticle=source.getTbArticle();
			if(tbArticle!=null){
				target.setArticleName(tbArticle.getArticleName());
				target.setArticlePath(tbArticle.getArticlePath());
			}
			prayRecords.add(target);
		}
		return prayRecords;
	}
}
