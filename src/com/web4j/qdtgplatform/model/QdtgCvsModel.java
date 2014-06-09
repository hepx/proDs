package com.web4j.qdtgplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.qdtgplatform.dao.QdtgCvsDao;
import com.web4j.qdtgplatform.entity.TbQdtgCvs;
import com.web4j.qdtgplatform.pojo.QdtgCvs;
import com.web4j.systemplatform.entity.TbUserInfo;
import com.web4j.systemplatform.model.userInfo.UserInfoModel;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;

@Service("qdtgCvsModel")
public class QdtgCvsModel {

	@Autowired
	private QdtgCvsDao qdtgCvsDao;
	
	@Autowired
	private UserInfoModel userInfoModel;
	
	public List<QdtgCvs> queryQdtgCvs(Integer start,Integer limit) throws Exception{
		List<TbQdtgCvs> tbQdtgCvs=qdtgCvsDao.quereyTbQdtgCvs(start, limit);
		List<QdtgCvs> qdtgCvs=new ArrayList<QdtgCvs>();
		for(TbQdtgCvs source:tbQdtgCvs){
			QdtgCvs target=new QdtgCvs();
			BeanUtils.copyProperties(source, target);
			qdtgCvs.add(target);
		}
		return qdtgCvs;
	}
	
	public List<QdtgCvs> queryAllQdtgCvs()throws Exception{
		List<TbQdtgCvs> tbQdtgCvs=qdtgCvsDao.queryAllQdtgCvs();
		List<QdtgCvs> qdtgCvs=new ArrayList<QdtgCvs>();
		for(TbQdtgCvs source:tbQdtgCvs){
			QdtgCvs target=new QdtgCvs();
			BeanUtils.copyProperties(source, target);
			qdtgCvs.add(target);
		}
		return qdtgCvs;	
	}
	
	public Long queryQdtgCvsCount(){
		return qdtgCvsDao.counts();
	}
	
	public void saveOrUpdateQdtgCvs(QdtgCvs qdtgCvs,UserInfoPojo userInfo) throws Exception{
		TbQdtgCvs tbQdtgCvs=null;
		if(qdtgCvs.getCvsId()==null||"".equals(qdtgCvs.getCvsId())){
			tbQdtgCvs=new TbQdtgCvs();
			
		}else{
			tbQdtgCvs=qdtgCvsDao.get(qdtgCvs.getCvsId());
		}
		BeanUtils.copyProperties(qdtgCvs, tbQdtgCvs);
		if(userInfo!=null){
			Long userId=userInfoModel.saveUserInfo(userInfo);
			qdtgCvsDao.getHibernateTemplate().clear();
			tbQdtgCvs.setTbUserInfo(new TbUserInfo(userId));
			qdtgCvsDao.save(tbQdtgCvs);
		}else{
			qdtgCvsDao.update(tbQdtgCvs);
		}
	}
	
	public void deleteQdtgCvs(List<QdtgCvs>qdtgCvs) throws Exception{
		for(QdtgCvs cvs:qdtgCvs){
			if(cvs.getCvsId()!=null){
				qdtgCvsDao.deleteById(cvs.getCvsId());
			}
		}
	}
}
