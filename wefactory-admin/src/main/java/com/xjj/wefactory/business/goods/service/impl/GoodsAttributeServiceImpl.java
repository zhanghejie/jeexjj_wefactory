/****************************************************
 * Description: ServiceImpl for t_business_goods_attribute
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.business.goods.entity.GoodsAttributeEntity;
import com.xjj.wefactory.business.goods.dao.GoodsAttributeDao;
import com.xjj.wefactory.business.goods.service.GoodsAttributeService;

@Service
public class GoodsAttributeServiceImpl extends XjjServiceSupport<GoodsAttributeEntity> implements GoodsAttributeService {

	@Autowired
	private GoodsAttributeDao goodsAttributeDao;

	@Override
	public XjjDAO<GoodsAttributeEntity> getDao() {
		
		return goodsAttributeDao;
	}
}