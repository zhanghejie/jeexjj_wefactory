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

package com.xjj.wefactory.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.product.dao.ProductDao;
import com.xjj.wefactory.business.product.entity.ProductEntity;
import com.xjj.wefactory.wx.service.ProductService;

@Service
public class ProductServiceImpl extends XjjServiceSupport<ProductEntity> implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public XjjDAO<ProductEntity> getDao() {
		
		return productDao;
	}
	
	/**
	 * 根据商品id查询product列表
	 * @param goodsId
	 * @return
	 */
	public List<ProductEntity> findListByGoodsId(Long goodsId)
	{
		XJJParameter query = new XJJParameter();
		query.addQuery("query.goodsId@eq@l", goodsId);
		List<ProductEntity> list = productDao.findList(query.getQueryMap());
		 return list;
	}
	
	
}