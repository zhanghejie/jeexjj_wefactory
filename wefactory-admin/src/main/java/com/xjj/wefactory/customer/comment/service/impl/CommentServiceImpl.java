/****************************************************
 * Description: ServiceImpl for 评论
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wefactory.customer.comment.entity.CommentEntity;
import com.xjj.wefactory.customer.comment.dao.CommentDao;
import com.xjj.wefactory.customer.comment.service.CommentService;

@Service
public class CommentServiceImpl extends XjjServiceSupport<CommentEntity> implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public XjjDAO<CommentEntity> getDao() {
		
		return commentDao;
	}
}