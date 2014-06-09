package com.web4j.bdgplatform.dao;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.web4j.bdgplatform.entity.TbBdgUser;
import com.web4j.common.dao.AbstractDao;

@Repository("bdgUserDao")
public class BdgUserDao extends AbstractDao<TbBdgUser> {

	public List<TbBdgUser> queryTbBdgUsers(Integer start,Integer limit) throws Exception{
		String queryString="from TbBdgUser t order by t.userId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbBdgUser> queryTbBdgUsers(Integer start,Integer limit,TbBdgUser bdgUser) throws Exception{
		Criteria c=getSession(true).createCriteria(TbBdgUser.class);
		if(bdgUser.getChannel()!=null){
			c.add(Restrictions.eq("channel", bdgUser.getChannel()));
		}
		if(bdgUser.getImei()!=null){
			c.add(Restrictions.eq("imei", bdgUser.getImei()));
		}
		if(bdgUser.getCreateTime()!=null){
			c.add(Restrictions.ge("createTime", bdgUser.getCreateTime()));
			c.add(Restrictions.lt("createTime", new Date(bdgUser.getCreateTime().getTime()+TimeUnit.DAYS.toMillis(1))));
		}
		c.addOrder(Order.desc("userId"));
		c.setFirstResult(start);
		c.setMaxResults(limit);
		return c.list();
	}
	
	@Override
	public Long counts(TbBdgUser bdgUser){
		Criteria criteria=getSession(true).createCriteria(TbBdgUser.class);
		if(bdgUser.getChannel()!=null){
			criteria.add(Restrictions.eq("channel", bdgUser.getChannel()));
		}
		if(bdgUser.getImei()!=null){
			criteria.add(Restrictions.eq("imei", bdgUser.getImei()));
		}
		if(bdgUser.getCreateTime()!=null){
			criteria.add(Restrictions.ge("createTime", bdgUser.getCreateTime()));
			criteria.add(Restrictions.lt("createTime", new Date(bdgUser.getCreateTime().getTime()+TimeUnit.DAYS.toMillis(1))));
		}
		criteria.setProjection(Projections.rowCount());
		return new Long((Integer)criteria.list().get(0));
	}
	
}
