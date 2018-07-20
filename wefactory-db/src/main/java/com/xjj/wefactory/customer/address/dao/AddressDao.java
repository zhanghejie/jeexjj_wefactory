/****************************************************
 * Description: DAO for 地址
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.address.dao;

import com.xjj.wefactory.customer.address.entity.AddressEntity;

import org.apache.ibatis.annotations.Param;

import com.xjj.framework.dao.XjjDAO;

public interface AddressDao  extends XjjDAO<AddressEntity> {
	
	public void resetDefault(@Param("customerId") Long customerId);
}

