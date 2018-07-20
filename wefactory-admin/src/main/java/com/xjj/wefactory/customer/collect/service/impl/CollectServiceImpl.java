/****************************************************
 * Description: ServiceImpl for 收藏
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.collect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.collect.entity.CollectEntity;
import com.xjj.wefactory.customer.collect.dao.CollectDao;
import com.xjj.wefactory.customer.collect.service.CollectService;

@Service
public class CollectServiceImpl extends XjjServiceSupport<CollectEntity> implements CollectService {

	@Autowired
	private CollectDao collectDao;

	@Override
	public XjjDAO<CollectEntity> getDao() {
		
		return collectDao;
	}
}