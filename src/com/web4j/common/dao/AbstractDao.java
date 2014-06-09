package com.web4j.common.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractDao<T> extends HibernateDaoSupport {

	private Class<T> entityClass;
	public AbstractDao(){
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Long save(T t) throws Exception {
		return (Long) getHibernateTemplate().save(t);
	}

	public void update(T t) throws Exception {
		getHibernateTemplate().update(t);
	}

	public void saveOrUpdate(T t) throws Exception {
		getHibernateTemplate().saveOrUpdate(t);
	}

	public void saveOrUpdateAll(List<T> list) throws Exception {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	public void saveOrUpdateAll(Set<T> set) throws Exception {
		getHibernateTemplate().saveOrUpdateAll(set);
	}

	public void delete(T t) throws Exception {
		getHibernateTemplate().delete(t);
	}

	public void deleteAll(Collection<T> entitys) throws Exception {
		getHibernateTemplate().deleteAll(entitys);
	}

	public void deleteById(Long id) throws Exception {
		delete(get(id));
	}

	public T get(Long id) throws Exception {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public T get(Long id, LockMode lockMode) throws Exception {
		return (T) getHibernateTemplate().get(entityClass, id, lockMode);
	}

	public void save(List<T> list) throws Exception {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	public void delete(List<T> list) throws Exception {
		getHibernateTemplate().deleteAll(list);
	}

	public List<T> list(String queryString) throws Exception {
		Query query = getSession(true).createQuery(queryString);
		return query.list();
	}

	public List<T> list(String queryString, Object value) throws Exception {
		Object[] values = { value };
		return list(queryString, values);

	}

	public List<T> list(String queryString, Object[] values) throws Exception {
		Query query = getSession(true).createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();
	}

	public List<T> list(Integer start,Integer limit)throws Exception{
		String queryString = "from " + entityClass.getSimpleName();
		return list(queryString, start, limit);
	}
	
	public List<T> list(String queryString, Integer start, Integer limit)
			throws Exception {
		Object[] values = {};
		return list(queryString, values, start, limit);

	}

	public List<T> list(String queryString, Object value, Integer start,
			Integer limit) throws Exception {
		Object[] values = { value };
		return list(queryString, values, start, limit);
	}

	public List<T> list(String queryString, Object[] values, Integer start,
			Integer limit) throws Exception {
		Query query = getSession(true).createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.setFirstResult(start).setMaxResults(limit);
		return query.list();
	}

	public List<T> list(String queryString, String[] argNames, Object[] args)
			throws Exception {
		Query query = getSession(true).createQuery(queryString);
		int argNum;
		if ((argNum = argNames.length) != args.length)
			throw new IllegalArgumentException(
					"参数名称List跟参数值List的长度不匹配！");
		for (int i = 0; i < argNum; i++) {
			if (args[i] instanceof List) {
				query.setParameterList(argNames[i], (Collection) args[i]);
			} else {
				query.setParameter(argNames[i], args[i]);
			}
		}
		return query.list();
	}

	public List<T> list(String queryString, String[] argNames, Object[] args,
			Integer start, Integer limit) throws Exception {
		Query query = getSession(true).createQuery(queryString);
		if (argNames.length != args.length)
			throw new Exception("参数名称List跟参数值List的长度不匹配！");
		for (int i = 0; i < argNames.length; i++) {
			if (args[i] instanceof List) {
				query.setParameterList(argNames[i], (Collection) args[i]);
			} else {
				query.setParameter(argNames[i], args[i]);
			}
		}
		if (start != null && limit != null) {
			query.setFirstResult(start).setMaxResults(limit);
		}
		return query.list();
	}
	
	public List<? extends Map<String,?>> map(String queryString)throws Exception{
		Query query = getSession(true).createQuery(queryString);
		return query.list();
	}
	
	public List<? extends Map<String,?>> map(String queryString,Integer start,Integer limit)throws Exception{
		Query query = getSession(true).createQuery(queryString);
		query.setFirstResult(start).setMaxResults(limit);
		return query.list();
	}
	
	public List<T> listAll() throws Exception {
		String queryString = "from " + entityClass.getSimpleName();
		Query query = getSession(true).createQuery(queryString);
		return query.list();
	}
	
	public Long counts(){
		String hql="select count(id)as counts from "+entityClass.getSimpleName();
		List list=getHibernateTemplate().find(hql);
		return (Long)list.get(0);
	}
	
	/*****************************下面方法采用Criteria***********************************/
	
	public List<T> list(T t){
		return getSession(true).createCriteria(entityClass).add(Example.create(t)).list();
	}
	
	public List<T>ListOnePage(T t,Integer start,Integer limit){
		Criteria criteria=getSession(true).createCriteria(entityClass);
		criteria.add(Example.create(t));
	    criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}
	
	public List<T>ListOnePage(T t,String order,Integer start,Integer limit){
		Criteria criteria=getSession(true).createCriteria(entityClass);
		criteria.add(Example.create(t));
	    criteria.addOrder(Order.desc(order));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}
	
	public Long counts(T t){
		Criteria criteria=getSession(true).createCriteria(entityClass);
		criteria.add(Example.create(t));
		criteria.setProjection(Projections.rowCount());
		return new Long((Integer)criteria.list().get(0));
	}
}
