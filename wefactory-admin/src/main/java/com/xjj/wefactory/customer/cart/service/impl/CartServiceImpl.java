/****************************************************
 * Description: ServiceImpl for 购物车
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.cart.entity.CartEntity;
import com.xjj.wefactory.customer.cart.dao.CartDao;
import com.xjj.wefactory.customer.cart.service.CartService;

@Service
public class CartServiceImpl extends XjjServiceSupport<CartEntity> implements CartService {

	@Autowired
	private CartDao cartDao;

	@Override
	public XjjDAO<CartEntity> getDao() {
		
		return cartDao;
	}
}