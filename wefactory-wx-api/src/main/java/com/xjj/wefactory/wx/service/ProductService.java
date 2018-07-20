/****************************************************
 * Description: Service for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.wx.service;
import java.util.List;

import com.xjj.framework.service.XjjService;
import com.xjj.wefactory.business.product.entity.ProductEntity;

public interface ProductService  extends XjjService<ProductEntity>{
	
	/**
	 * 根据商品id查询product列表
	 * @param goodsId
	 * @return
	 */
	public List<ProductEntity> findListByGoodsId(Long goodsId); 
}
