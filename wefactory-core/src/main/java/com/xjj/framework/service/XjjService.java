package com.xjj.framework.service;
import java.util.List;

import com.xjj.framework.entity.EntitySupport;
import com.xjj.framework.exception.DataAccessException;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.XJJParameter;

public interface XjjService<E extends EntitySupport> {

	/**
	 * 保存
	 * @param obj
	 * @return
	 */
	public Long save(E obj);
	
	/**
	 * 更新
	 * @param obj
	 */
	public void update(E obj);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(E obj);
	
	/**
	 * 按属性删除
	 * @param property
	 * @param val
	 * @return 
	 */
	public void deleteByProperty(String property,Object val);

	/**
	 * 查询条数
	 * @param param
	 */
	public int getCount(XJJParameter param);
	
	/**
	 * 根据ID得到实体类
	 * @param ID
	 * @return
	 */
	public E getById(Long ID);
	/**
	 * 根据参数得到实体类
	 * @param param
	 * @return
	 */
	public E getByParam(XJJParameter param) throws DataAccessException;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<E> findAll();
	
	/**
	 * 根据参数查询列表
	 * @param param
	 * @return
	 */
	public List<E> findList(XJJParameter param);
	
	/**
	 * 根据参数查询列表
	 * @param param
	 * @return
	 */
	public List<E> findListLimit(XJJParameter param,int offset,int limit);
	
	
	/**
	 * 根据某属性值查询列表
	 * @param property
	 * @param objArr
	 * @return
	 */
	public List<E> findListByProperty(String property,Object val);
	
	
	/**
	 * 根据某属性值查询列表
	 * @param property
	 * @param objArr
	 * @return
	 */
	public List<E> findListByPropertyLimit(String property,Object val,int offset,int limit);
	
	
	/**
	 * 根据某属性值数组查询列表
	 * @param property
	 * @param objArr
	 * @return
	 */
	public List<E> findListByColumnValues(String property,Object[] objArr);
	
	/**
	 * 根据某属性值数组查询列表
	 * @param property
	 * @param objArr
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<E> findListByColumnValuesLimit(String property,Object[] objArr,int offset,int limit);

	/**
	 * 分页查询列表
	 * @param param
	 * @param page
	 * @return
	 */
	public Pagination findPage(XJJParameter param, Pagination page);
	
	/**
	 * 判断是否唯一
	 * @param tableName
	 * @param columnName
	 * @param columnVal
	 * @param id
	 * @return
	 */
	public boolean checkUniqueVal( String tableName,String columnName,String columnVal,Long id);
}