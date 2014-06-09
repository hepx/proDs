package com.web4j.qdtgplatform.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.xwork.time.DateFormatUtils;
import org.apache.commons.lang.xwork.time.DateUtils;
import org.apache.struts2.util.DateFormatter;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.qdtgplatform.entity.TbQdtgRecord;
import com.web4j.qdtgplatform.pojo.QdtgRecord;

@Repository("qdtgRecordDao")
public class QdtgRecordDao extends AbstractDao<TbQdtgRecord> {

	public List<TbQdtgRecord> queryTbQdtgRecord(Integer start,Integer limit,QdtgRecord qdtgRecord) throws Exception{
		Criteria c=getSession(true).createCriteria(TbQdtgRecord.class);
		if(qdtgRecord.getProductId()!=null){
			c.add(Restrictions.eq("productId", qdtgRecord.getProductId()));
		}
		if(qdtgRecord.getChannelId()!=null){
			c.add(Restrictions.eq("channelId", qdtgRecord.getChannelId()));
		}
		if(qdtgRecord.getCvsId()!=null){
			c.add(Restrictions.eq("cvsId", qdtgRecord.getCvsId()));
		}
		if(qdtgRecord.getRecordTime()!=null){
			//c.add(Restrictions.ge("recordTime", (qdtgRecord.getRecordTime()+"00:00:00")));
			//c.add(Restrictions.le("recordTime", (qdtgRecord.getRecordTime()+"23:59:59")));
			c.add(Restrictions.eq("recordTime", Date.valueOf(qdtgRecord.getRecordTime())));
		}
		c.addOrder(Order.desc("recordId"));
		c.setFirstResult(start);
		c.setMaxResults(limit);
		return c.list();
	}
	
	public Long queryTbQdtgRecordCount(QdtgRecord qdtgRecord) throws Exception{
		Criteria c=getSession(true).createCriteria(TbQdtgRecord.class);
		if(qdtgRecord.getProductId()!=null){
			c.add(Restrictions.eq("productId", qdtgRecord.getProductId()));
		}
		if(qdtgRecord.getChannelId()!=null){
			c.add(Restrictions.eq("channelId", qdtgRecord.getChannelId()));
		}
		if(qdtgRecord.getCvsId()!=null){
			c.add(Restrictions.eq("cvsId", qdtgRecord.getCvsId()));
		}
		if(qdtgRecord.getRecordTime()!=null){
			//c.add(Restrictions.ge("recordTime", (qdtgRecord.getRecordTime()+"00:00:00")));
			//c.add(Restrictions.le("recordTime", (qdtgRecord.getRecordTime()+"23:59:59")));
			c.add(Restrictions.eq("recordTime", Date.valueOf(qdtgRecord.getRecordTime())));
		}
		//c.addOrder(Order.desc("recordId"));
		c.setProjection(Projections.rowCount());
		return new Long((Integer)c.list().get(0));
	}
	
	public List<TbQdtgRecord> queryTbQdtgRecordByInStatus(Integer start,Integer limit)throws Exception{
		String hql="select new TbQdtgRecord(recordId,productName,channelNo,fileName,activateQt,createTime,recordTime) " +
				"from TbQdtgRecord t where t.status=false order by t.recordId desc";
		return list(hql,start,limit);
	}
	
	public Long queryTbQdtgRecordByInStatusCount()throws Exception{
		String hql="select count(recordId)as counts from TbQdtgRecord t where t.status=false order by t.recordId desc";
		return (Long)getHibernateTemplate().find(hql).get(0);
	}
	
	public List<Map<String,Object>> statTbQdtgRecordByDay(QdtgRecord statCondition,Integer start,Integer limit)throws Exception{
		StringBuffer hql=new StringBuffer("select new Map(t.productName as productName,t.cvsName as cvsName,t.channelNo as channelNo,t.fileName as fileName," +
				"t.unitPrice as unitPrice,t.activateQt as activateQt,t.activateQt_Net as activateQt_Net,t.totalPrice as totalPrice," +
				"t.totalPrice_Net as totalPrice_Net,date_format(t.recordTime,'%Y-%m-%d') as recordTime) " +
				"from TbQdtgRecord t where 1=1");
		if(statCondition.getProductId()!=null){
			hql.append(" and t.productId="+statCondition.getProductId());
		}
		if(statCondition.getChannelId()!=null){
			hql.append(" and t.channelId="+statCondition.getChannelId());
		}
		if(statCondition.getCvsId()!=null){
			hql.append(" and t.cvsId="+statCondition.getCvsId());
		}
		if(statCondition.getStartTime()!=null&&statCondition.getEndTime()!=null){
			hql.append(" and t.recordTime>='"+statCondition.getStartTime()+" 00:00:00'");
			hql.append(" and t.recordTime<='"+statCondition.getEndTime()+" 23:59:59'");
		}
		if(statCondition.getStatus()!=null){
			hql.append(" and t.status="+statCondition.getStatus());
		}
		hql.append(" order by t.recordTime desc,t.productName,t.channelNo,t.unitPrice,t.cvsName");
		Query q=getSession(true).createQuery(hql.toString());
		//q.setFirstResult(start);
		//q.setMaxResults(limit);
		return q.list();
	}
	
	public Long statTbQdtgRecordByDayCount(QdtgRecord statCondition)throws Exception{
		StringBuffer hql=new StringBuffer("select count(*) as counts from TbQdtgRecord t where 1=1");
		if(statCondition.getProductId()!=null){
			hql.append(" and t.productId="+statCondition.getProductId());
		}
		if(statCondition.getChannelId()!=null){
			hql.append(" and t.channelId="+statCondition.getChannelId());
		}
		if(statCondition.getCvsId()!=null){
			hql.append(" and t.cvsId="+statCondition.getCvsId());
		}
		if(statCondition.getStartTime()!=null&&statCondition.getEndTime()!=null){
			hql.append(" and t.recordTime>='"+statCondition.getStartTime()+" 00:00:00'");
			hql.append(" and t.recordTime<='"+statCondition.getEndTime()+" 23:59:59'");
		}
		if(statCondition.getStatus()!=null){
			hql.append(" and t.status="+statCondition.getStatus());
		}
		return (Long)getHibernateTemplate().find(hql.toString()).get(0);
	}
	
	public List<Map<String,Object>> statTbQdtgRecordByCvsByDay(Long userId,QdtgRecord statCondition,Integer start,Integer limit)throws Exception{
		StringBuffer hql=new StringBuffer("select new Map(t.productName as productName,t.channelNo as channelNo,t.fileName as fileName," +
				"t.unitPrice as unitPrice,t.activateQt_Net as activateQt,t.totalPrice_Net as totalPrice,date_format(t.recordTime,'%Y-%m-%d') as recordTime) " +
				"from TbQdtgRecord t where t.userId="+userId+" and t.status=true");
		//if(statCondition.getProductId()!=null){
		//	hql.append(" and t.productId="+statCondition.getProductId());
		//}
		if(statCondition.getChannelId()!=null){
			hql.append(" and t.channelId="+statCondition.getChannelId());
		}
		if(statCondition.getStartTime()!=null&&statCondition.getEndTime()!=null){
			hql.append(" and t.recordTime>='"+statCondition.getStartTime()+" 00:00:00'");
			hql.append(" and t.recordTime<='"+statCondition.getEndTime()+" 23:59:59'");
		}
		hql.append(" order by t.recordTime desc,t.cvsName,t.productName,t.channelNo,t.unitPrice");
		Query q=getSession(true).createQuery(hql.toString());
		//q.setFirstResult(start);
		//q.setMaxResults(limit);
		return q.list();
	}
	
	public Long statTbQdtgRecordByCvsByDayCount(Long userId,QdtgRecord statCondition)throws Exception{
		StringBuffer hql=new StringBuffer("select count(*) as counts " 
				+ " from TbQdtgRecord t where t.userId="+userId+" and t.status=true");
		//if(statCondition.getProductId()!=null){
		//	hql.append(" and t.productId="+statCondition.getProductId());
		//}
		if(statCondition.getChannelId()!=null){
			hql.append(" and t.channelId="+statCondition.getChannelId());
		}
		if(statCondition.getStartTime()!=null&&statCondition.getEndTime()!=null){
			hql.append(" and t.recordTime>='"+statCondition.getStartTime()+" 00:00:00'");
			hql.append(" and t.recordTime<='"+statCondition.getEndTime()+" 23:59:59'");
		}
		return (Long)getHibernateTemplate().find(hql.toString()).get(0);
	}
	
	public List<Map<String,Object>>statTbQdtgRecordByMonth(QdtgRecord statCondition){
		StringBuffer hql=new StringBuffer("select new Map(t.productName as productName,t.cvsName as cvsName,t.channelNo as channelNo,t.fileName as fileName," +
				"t.unitPrice as unitPrice,sum(t.activateQt) as activateQt,sum(t.activateQt_Net) as activateQt_Net,sum(t.totalPrice) as totalPrice," +
				"sum(t.totalPrice_Net) as totalPrice_Net,date_format(t.recordTime,'%Y-%m') as recordTime) " +
				"from TbQdtgRecord t where 1=1");
		if(statCondition.getProductId()!=null){
			hql.append(" and t.productId="+statCondition.getProductId());
		}
		if(statCondition.getChannelId()!=null){
			hql.append(" and t.channelId="+statCondition.getChannelId());
		}
		if(statCondition.getCvsId()!=null){
			hql.append(" and t.cvsId="+statCondition.getCvsId());
		}
		if(statCondition.getMonth()!=null){
			hql.append(" and date_format(t.recordTime,'%Y-%m')='"+statCondition.getMonth()+"'");
		}
		if(statCondition.getStatus()!=null){
			hql.append(" and t.status="+statCondition.getStatus());
		}
		hql.append(" group by date_format(t.recordTime,'%Y-%m'),t.cvsName,t.productName,t.channelNo,t.fileName,t.unitPrice");
		hql.append(" order by date_format(t.recordTime,'%Y-%m'),t.productName,t.channelNo,t.unitPrice,t.cvsName");
		Query q=getSession(true).createQuery(hql.toString());
		return q.list();
	}
	
	public List<Map<String,Object>>statTbQdtgRecordByCvsByMonth(Long userId,QdtgRecord statCondition){
		StringBuffer hql=new StringBuffer("select new Map(date_format(t.recordTime,'%Y-%m') as recordTime," +
				"t.productName as productName,t.channelNo as channelNo,t.fileName as fileName,t.unitPrice as unitPrice," +
				"sum(t.activateQt_Net) as activateQt,sum(t.totalPrice_Net) as totalPrice) " +
				"from TbQdtgRecord t where t.userId="+userId+" and t.status=true");
		if(statCondition.getChannelId()!=null){
			hql.append(" and t.channelId="+statCondition.getChannelId());
		}
		if(statCondition.getMonth()!=null){
			hql.append(" and date_format(t.recordTime,'%Y-%m')='"+statCondition.getMonth()+"'");
		}
		hql.append(" group by date_format(t.recordTime,'%Y-%m'),t.cvsName,t.channelNo,t.fileName,t.unitPrice");
		hql.append(" order by date_format(t.recordTime,'%Y-%m'),t.cvsName,t.productName,t.channelNo,t.unitPrice");
		Query q=getSession(true).createQuery(hql.toString());
		return q.list();
	}
}
