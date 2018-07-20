/****************************************************
 * Description: ServiceImpl for 商品
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
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.business.goods.dao.GoodsDao;
import com.xjj.wefactory.business.goods.service.GoodsService;

@Service
public class GoodsServiceImpl extends XjjServiceSupport<GoodsEntity> implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public XjjDAO<GoodsEntity> getDao() {
		
		return goodsDao;
	}
}