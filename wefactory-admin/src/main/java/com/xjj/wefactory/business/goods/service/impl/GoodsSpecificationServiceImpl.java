/****************************************************
 * Description: ServiceImpl for 商品规格
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wefactory.business.goods.dao.GoodsSpecificationDao;
import com.xjj.wefactory.business.goods.service.GoodsSpecificationService;

@Service
public class GoodsSpecificationServiceImpl extends XjjServiceSupport<GoodsSpecificationEntity> implements GoodsSpecificationService {

	@Autowired
	private GoodsSpecificationDao goodsSpecificationDao;

	@Override
	public XjjDAO<GoodsSpecificationEntity> getDao() {
		
		return goodsSpecificationDao;
	}
}