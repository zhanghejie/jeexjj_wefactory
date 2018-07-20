/****************************************************
 * Description: ServiceImpl for 地址
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.address.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.address.entity.AddressEntity;
import com.xjj.wefactory.customer.address.dao.AddressDao;
import com.xjj.wefactory.customer.address.service.AddressService;

@Service
public class AddressServiceImpl extends XjjServiceSupport<AddressEntity> implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	public XjjDAO<AddressEntity> getDao() {
		
		return addressDao;
	}
}