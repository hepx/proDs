package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.SxdVersionDao;
import com.web4j.sxdplatform.entity.TbSxdVersion;
import com.web4j.sxdplatform.pojo.SxdVersion;

@Service("sxdVersionModel")
public class SxdVersionModel {

	@Autowired
	private SxdVersionDao sxdVersionDao;
	
	public void saveOrUpdateSxdVersion(SxdVersion sxdVersion)throws Exception{
		TbSxdVersion tbSxdVersion = new TbSxdVersion();
		BeanUtils.copyProperties(sxdVersion, tbSxdVersion);
		if(tbSxdVersion.getCreateTime()==null){
			tbSxdVersion.setCreateTime(new Date());
		}
		sxdVersionDao.saveOrUpdate(tbSxdVersion);
	}
	
	public List<SxdVersion>querySxdVersions(Integer start,Integer limit)throws Exception{
		List<TbSxdVersion> tbSxdVersions=sxdVersionDao.queryTbSxdVersions(start, limit);
		List<SxdVersion> sxdVersions = new ArrayList<SxdVersion>();
		for(TbSxdVersion source : tbSxdVersions){
			SxdVersion target = new SxdVersion();
			BeanUtils.copyProperties(source, target);
			sxdVersions.add(target);
		}
		return sxdVersions;
	}
	
	public TbSxdVersion getLastTbSxdVersion()throws Exception{
		return sxdVersionDao.getLastTbSxdVersion();
	}

	public TbSxdVersion getTbSxdVersion(Integer internalVersion)throws Exception{
		return sxdVersionDao.getTbSxdVersion(internalVersion);
	}
	
	public Long getSxdVersionCount(){
		return sxdVersionDao.counts();
	}
	
	public void deleteSxdVersions(List<SxdVersion> sxdVersions) throws Exception{
		for(SxdVersion sxdVersion : sxdVersions){
			if(sxdVersion.getVersionId()!=null){
				sxdVersionDao.deleteById(sxdVersion.getVersionId());
			}
		}
	}
	
	public TbSxdVersion quereyTbSxdVersionById(Long versionId)throws Exception{
		return sxdVersionDao.get(versionId);
	}
	
	public void updateTbSxdVersion(TbSxdVersion tbSxdVersion)throws Exception{
		sxdVersionDao.update(tbSxdVersion);
	}
	
}
