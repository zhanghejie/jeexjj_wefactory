/****************************************************
 * Description: Service for 订单
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.wx.service;
import com.xjj.framework.service.XjjService;
import com.xjj.wefactory.customer.order.entity.OrderEntity;

public interface OrderService  extends XjjService<OrderEntity>{
	/**
	 * 生成唯一的订单
	 * @param userId
	 * @return
	 */
    public String generateOrderSn(Long userId);
    
    /**
     * 根据ordersn查询订单
     * @param orderSn
     * @return
     */
    public OrderEntity getByOrderSn(String orderSn);
}
