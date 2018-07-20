/****************************************************
 * Description: ServiceImpl for 常见问题
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.wx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.business.issue.dao.IssueDao;
import com.xjj.wefactory.business.issue.entity.IssueEntity;
import com.xjj.wefactory.wx.service.IssueService;

@Service
public class IssueServiceImpl extends XjjServiceSupport<IssueEntity> implements IssueService {

	@Autowired
	private IssueDao issueDao;

	@Override
	public XjjDAO<IssueEntity> getDao() {
		
		return issueDao;
	}
}