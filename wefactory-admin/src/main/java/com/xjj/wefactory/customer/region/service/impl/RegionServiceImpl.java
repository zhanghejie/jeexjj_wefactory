/****************************************************
 * Description: ServiceImpl for 地区信息
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.region.entity.RegionEntity;
import com.xjj.wefactory.customer.region.dao.RegionDao;
import com.xjj.wefactory.customer.region.service.RegionService;

@Service
public class RegionServiceImpl extends XjjServiceSupport<RegionEntity> implements RegionService {

	@Autowired
	private RegionDao regionDao;

	@Override
	public XjjDAO<RegionEntity> getDao() {
		
		return regionDao;
	}
}