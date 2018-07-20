/****************************************************
 * Description: Service for 收藏
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.wx.service;
import com.xjj.wefactory.customer.collect.entity.CollectEntity;
import com.xjj.framework.service.XjjService;

public interface CollectService  extends XjjService<CollectEntity>{
	
	/**
	 * 查询用户收藏数
	 * @param customerId
	 * @param valueId
	 * @return
	 */
	public int getCountByUserIdAndValueId(Long customerId,Long valueId);
	
	/**
	 * 根据参数得到收藏
	 * @param userId
	 * @param type
	 * @param valueId
	 * @return
	 */
	public CollectEntity getByTypeAndValue(Long userId, Integer type, Long valueId);
}
