/****************************************************
 * Description: ServiceImpl for 订单商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.order.entity.OrderGoodsEntity;
import com.xjj.wefactory.customer.order.dao.OrderGoodsDao;
import com.xjj.wefactory.customer.order.service.OrderGoodsService;

@Service
public class OrderGoodsServiceImpl extends XjjServiceSupport<OrderGoodsEntity> implements OrderGoodsService {

	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Override
	public XjjDAO<OrderGoodsEntity> getDao() {
		
		return orderGoodsDao;
	}
}