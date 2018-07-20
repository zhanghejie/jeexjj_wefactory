/****************************************************
 * Description: ServiceImpl for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.business.product.entity.ProductEntity;
import com.xjj.wefactory.business.product.dao.ProductDao;
import com.xjj.wefactory.business.product.service.ProductService;

@Service
public class ProductServiceImpl extends XjjServiceSupport<ProductEntity> implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public XjjDAO<ProductEntity> getDao() {
		
		return productDao;
	}
}