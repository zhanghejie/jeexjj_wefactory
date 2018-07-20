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

package com.xjj.wefactory.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.customer.address.dao.AddressDao;
import com.xjj.wefactory.customer.address.entity.AddressEntity;
import com.xjj.wefactory.wx.service.AddressService;

@Service
public class AddressServiceImpl extends XjjServiceSupport<AddressEntity> implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	public XjjDAO<AddressEntity> getDao() {
		
		return addressDao;
	}
	
	public List<AddressEntity> findListByCustomerId(Long customerId)
	{
		return addressDao.findListByColumn("customer_id", customerId);
	}
	
	public void resetDefault(Long userId) {
		addressDao.resetDefault(userId);
    }
	
	public AddressEntity getDefault(Long userId)
	{
		XJJParameter param = new XJJParameter();
        param.addQuery("query.customerId@eq@l",userId);
        param.addQuery("query.isDefault@eq@b",true);
        List<AddressEntity> addressList = addressDao.findList(param.getQueryMap());
        if(null==addressList || 0==addressList.size())
        {
        	return null;
        }
        return addressList.get(0);
	}
}