/****************************************************
 * Description: ServiceImpl for 关键词
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
import com.xjj.wefactory.business.keyword.dao.KeywordDao;
import com.xjj.wefactory.business.keyword.entity.KeywordEntity;
import com.xjj.wefactory.wx.service.KeywordService;

@Service
public class KeywordServiceImpl extends XjjServiceSupport<KeywordEntity> implements KeywordService {

	@Autowired
	private KeywordDao keywordDao;

	@Override
	public XjjDAO<KeywordEntity> getDao() {
		
		return keywordDao;
	}
	
	public KeywordEntity getDefault()
	{
		List<KeywordEntity> list= keywordDao.findListByColumn("is_default", 1);
		
		if(null==list || list.size()==0){
			return null;
		}
		return list.get(0);
	}
}