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

package com.xjj.wefactory.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.Constants;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.customer.cart.entity.CartEntity;
import com.xjj.wefactory.customer.order.dao.OrderGoodsDao;
import com.xjj.wefactory.customer.order.entity.OrderGoodsEntity;
import com.xjj.wefactory.wx.service.OrderGoodsService;

@Service
public class OrderGoodsServiceImpl extends XjjServiceSupport<OrderGoodsEntity> implements OrderGoodsService {

	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Override
	public XjjDAO<OrderGoodsEntity> getDao() {
		
		return orderGoodsDao;
	}
	
	public List<OrderGoodsEntity> findByOidAndGid(Long orderId,Long goodsId)
	{
		XJJParameter param = new XJJParameter();
        param.addQuery("query.orderId@eq@l",orderId);
        param.addQuery("query.goodsId@eq@l",goodsId);
        
        List<OrderGoodsEntity> list = orderGoodsDao.findList(param.getQueryMap());
        return list;
	}
}