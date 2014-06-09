package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.BuddhaMusicDao;
import com.web4j.sxdplatform.entity.TbBuddhaMusic;
import com.web4j.sxdplatform.pojo.BuddhaMusic;

@Service("buddhaMusicModel")
public class BuddhaMusicModel {

	@Autowired
	private BuddhaMusicDao buddhaMusicDao;
	
	public void saveOrUpdateMusics(List<BuddhaMusic> buddhaMusics) throws Exception{
		List<TbBuddhaMusic> tbBuddhaMusics=new ArrayList<TbBuddhaMusic>();
		for(BuddhaMusic buddhaMusic:buddhaMusics){
			TbBuddhaMusic tbBuddhaMusic=new TbBuddhaMusic();
			BeanUtils.copyProperties(buddhaMusic, tbBuddhaMusic);
			if(tbBuddhaMusic.getCreateTime()==null){
				tbBuddhaMusic.setCreateTime(new Date());
			}
			tbBuddhaMusics.add(tbBuddhaMusic);
		}
		buddhaMusicDao.saveOrUpdateAll(tbBuddhaMusics);
	}
	
	public List<BuddhaMusic> queryBuddhaMusics(Integer start,Integer limit) throws Exception{
		List<TbBuddhaMusic> tbBuddhaMusics=buddhaMusicDao.queryTbBuddhaMusics(start, limit);
		List<BuddhaMusic> buddhaMusics=new ArrayList<BuddhaMusic>();
		for(TbBuddhaMusic tbBuddhaMusic :tbBuddhaMusics){
			BuddhaMusic buddhaMusic=new BuddhaMusic();
			BeanUtils.copyProperties(tbBuddhaMusic, buddhaMusic);
			buddhaMusics.add(buddhaMusic);
		}
		return buddhaMusics;
	}
	
	public Long getBuddhaMusicCount(){
		return buddhaMusicDao.counts();
	}
	
	public void deleteBuddhaMusics(List<BuddhaMusic>buddhaMusics) throws Exception{
		for(BuddhaMusic buddhaMusic:buddhaMusics){
			if(buddhaMusic.getMusicId()!=null&&!"".equals(buddhaMusic.getMusicId())){
				buddhaMusicDao.deleteById(buddhaMusic.getMusicId());
			}
		}
	}
	
	public TbBuddhaMusic getBuddhaMusicById(Long id) throws Exception{
		return buddhaMusicDao.get(id);
	}
	
	public void updateBuddhaMusic(TbBuddhaMusic tbBuddhaMusic)throws Exception{
		buddhaMusicDao.update(tbBuddhaMusic);
	}
	
	/**********************手机永调用业务**************************/
	
	public List<TbBuddhaMusic> quereyBuddhaMusicByType(String musicType,Integer start,Integer limit) throws Exception{
		return buddhaMusicDao.queryTbBuddhaMusicByType(musicType, start, limit);
	}
	
	public List<TbBuddhaMusic> statBuddhaMusicByType() throws Exception{
		return buddhaMusicDao.statTbBuddhaMusicByType();
	}
}
