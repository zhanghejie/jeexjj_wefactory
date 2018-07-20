/****************************************************
 * Description: Service for 类目
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.wx.service;
import java.util.List;

import com.xjj.framework.service.XjjService;
import com.xjj.wefactory.business.category.entity.CategoryEntity;

public interface CategoryService  extends XjjService<CategoryEntity>{
	
	/**
	 * 根据pid查询类目
	 * @param pid
	 * @return
	 */
	public List<CategoryEntity> findListByPid(Long pid);
	
	
	/**
	 * 查询一级有效类目
	 * @return
	 */
	public List<CategoryEntity> findListByLevel1();
}
