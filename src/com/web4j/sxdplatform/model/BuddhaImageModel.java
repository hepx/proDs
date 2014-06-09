package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.BuddhaImageDao;
import com.web4j.sxdplatform.entity.TbBuddhaImage;
import com.web4j.sxdplatform.pojo.BuddhaImage;

@Service("buddhaImageModel")
public class BuddhaImageModel {

	@Autowired
	private BuddhaImageDao buddhaImageDao;
	
	public void saveOrUpdateBuddhaImages(List<BuddhaImage>buddhaImages)throws Exception{
		List<TbBuddhaImage>tbBuddhaImages=new ArrayList<TbBuddhaImage>();
		for(BuddhaImage source:buddhaImages){
			TbBuddhaImage target =new TbBuddhaImage();
			BeanUtils.copyProperties(source, target);
			if(target.getCreateTime()==null){
				target.setCreateTime(new Date());
			}
			tbBuddhaImages.add(target);
		}
		buddhaImageDao.saveOrUpdateAll(tbBuddhaImages);
	}
	
	public List<BuddhaImage> queryBuddhaImages(Integer start,Integer limit)throws Exception{
		List<TbBuddhaImage> tbBuddhaImages=buddhaImageDao.queryTbBuddhaImages(start, limit);
		List<BuddhaImage> buddhaImages=new ArrayList<BuddhaImage>();
		for(TbBuddhaImage source:tbBuddhaImages){
			BuddhaImage target=new BuddhaImage();
			BeanUtils.copyProperties(source, target);
			buddhaImages.add(target);
		}
		return buddhaImages;
	}
	
	public Long getBuddhaImageCounts(){
		return buddhaImageDao.counts();
	}
	
	public void deleteBuddhaImages(List<BuddhaImage>buddhaImages)throws Exception{
		for(BuddhaImage image:buddhaImages){
			if(image.getImageId()!=null&&!"".equals(image.getImageId())){
				buddhaImageDao.deleteById(image.getImageId());
			}
		}
	}
	
	public TbBuddhaImage quereyTbBuddhaImageById(Long imageId)throws Exception{
		return buddhaImageDao.get(imageId);
	}
	
	public void updateTbBuddhaImage(TbBuddhaImage tbBuddhaImage)throws Exception{
		buddhaImageDao.update(tbBuddhaImage);
	}
	
	/*******************手机调 用******************/
	
	public List<TbBuddhaImage> queryBuddhaImagesOfPhone(Integer start,Integer limit)throws Exception{
		return buddhaImageDao.queryTbBuddhaImagesOfPhone(start, limit);
	}
}
