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

package com.xjj.wefactory.business.category.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.business.category.entity.CategoryEntity;
import com.xjj.wefactory.business.category.dao.CategoryDao;
import com.xjj.wefactory.business.category.service.CategoryService;

@Service
public class CategoryServiceImpl extends XjjServiceSupport<CategoryEntity> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public XjjDAO<CategoryEntity> getDao() {
		
		return categoryDao;
	}
}