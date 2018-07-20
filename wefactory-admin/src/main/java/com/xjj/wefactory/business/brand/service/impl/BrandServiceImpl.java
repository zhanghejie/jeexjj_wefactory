/****************************************************
 * Description: ServiceImpl for 品牌表
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.brand.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.business.brand.entity.BrandEntity;
import com.xjj.wefactory.business.brand.dao.BrandDao;
import com.xjj.wefactory.business.brand.service.BrandService;

@Service
public class BrandServiceImpl extends XjjServiceSupport<BrandEntity> implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Override
	public XjjDAO<BrandEntity> getDao() {
		
		return brandDao;
	}
}