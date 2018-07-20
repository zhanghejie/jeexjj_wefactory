/****************************************************
 * Description: Service for 专题
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
import com.xjj.wefactory.business.topic.entity.TopicEntity;

public interface TopicService  extends XjjService<TopicEntity>{
	/**
	 * 查询相关专题
	 * @param id
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<TopicEntity> findRelatedList(Long id, int offset, int limit);
}
