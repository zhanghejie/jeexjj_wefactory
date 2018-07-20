/****************************************************
 * Description: ServiceImpl for 订单
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.wx.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.order.dao.OrderDao;
import com.xjj.wefactory.customer.order.entity.OrderEntity;
import com.xjj.wefactory.wx.service.OrderService;

@Service
public class OrderServiceImpl extends XjjServiceSupport<OrderEntity> implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public XjjDAO<OrderEntity> getDao() {
		
		return orderDao;
	}
	
    public String generateOrderSn(Long userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String now = df.format(LocalDate.now());
        String orderSn = userId+"_"+now;
       
        return orderSn;
    }
    
    public OrderEntity getByOrderSn(String orderSn)
    {
    	List<OrderEntity> orderList = orderDao.findListByColumn("orderSn", orderSn);
    	
    	if(null==orderList)
    	{
    		return null;
    	}
    	return orderList.get(0);
    	
    }
}