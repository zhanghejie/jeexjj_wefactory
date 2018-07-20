package com.xjj.framework.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xjj.framework.entity.EntitySupport;
public interface XjjDAO<E extends EntitySupport> {
	public void save(E obj);
	
	public void update(E obj);
	
	public void delete(@Param("id") Long id);
	public void deleteByColumn(@Param("column") String property,@Param("val") Object val);
	public E getById(@Param("id") Long id);
	public List<E> findAll();
	
	//查询
	public int getCount(@Param("query") HashMap<String,HashMap<String,Object>> queryMap);
	public List<E> findList(@Param("query") HashMap<String,HashMap<String,Object>> queryMap);
	public List<E> findListLimit(@Param("query") HashMap<String,HashMap<String,Object>> queryMap,@Param("offset") int offset, @Param("limit") int limit);
	public List<E> findPage (@Param("query") HashMap<String,HashMap<String,Object>> queryMap, @Param("offset") int offset, @Param("limit") int limit);

	public List<E> findListByColumn(@Param("column") String property,@Param("val") Object val);
	public List<E> findListByColumnLimit(@Param("column") String property,@Param("val") Object val, @Param("offset") int offset, @Param("limit") int limit);
	public List<E> findListByColumnValues(@Param("column") String property,@Param("valArr") Object[] propValArr);
	public List<E> findListByColumnValuesLimit(@Param("column") String property,@Param("valArr") Object[] propValArr, @Param("offset") int offset, @Param("limit") int limit);
}