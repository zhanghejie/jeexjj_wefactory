/****************************************************
 * Description: ServiceImpl for 类目
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.category.dao.CategoryDao;
import com.xjj.wefactory.business.category.entity.CategoryEntity;
import com.xjj.wefactory.wx.service.CategoryService;

@Service
public class CategoryServiceImpl extends XjjServiceSupport<CategoryEntity> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public XjjDAO<CategoryEntity> getDao() {
		
		return categoryDao;
	}
	
	public List<CategoryEntity> findListByPid(Long pid) {
		XJJParameter query = new XJJParameter();
		query.addQuery("query.parentId@eq@l", pid);
		List<CategoryEntity> list = categoryDao.findList(query.getQueryMap());
		 return list;
	}
	
	public List<CategoryEntity> findListByLevel1()
	{
		XJJParameter query = new XJJParameter();
		query.addQuery("query.level@eq@s", "L1");
        List<CategoryEntity> list = categoryDao.findList(query.getQueryMap());
        return list;
	}
}