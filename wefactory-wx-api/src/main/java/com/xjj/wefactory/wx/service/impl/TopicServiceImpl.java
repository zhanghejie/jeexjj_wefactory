/****************************************************
 * Description: ServiceImpl for 专题
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
import com.xjj.wefactory.business.topic.dao.TopicDao;
import com.xjj.wefactory.business.topic.entity.TopicEntity;
import com.xjj.wefactory.wx.service.TopicService;

@Service
public class TopicServiceImpl extends XjjServiceSupport<TopicEntity> implements TopicService {

	@Autowired
	private TopicDao topicDao;

	@Override
	public XjjDAO<TopicEntity> getDao() {
		
		return topicDao;
	}
	
	public List<TopicEntity> findRelatedList(Long id, int offset, int limit) {
        
		XJJParameter param = new XJJParameter();
		param.addQuery("query.id@ne@l", id);
        return topicDao.findListLimit(param.getQueryMap(),offset, limit);
    }
}