/****************************************************
 * Description: Service for 订单商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.wx.service;
import java.util.List;

import com.xjj.framework.service.XjjService;
import com.xjj.wefactory.customer.order.entity.OrderGoodsEntity;

public interface OrderGoodsService  extends XjjService<OrderGoodsEntity>{
	
	public List<OrderGoodsEntity> findByOidAndGid(Long orderId,Long goodsId);
}
