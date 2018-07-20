/****************************************************
 * Description: Service for 评论
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
import com.xjj.wefactory.customer.comment.entity.CommentEntity;

public interface CommentService  extends XjjService<CommentEntity>{
	
	/**
	 * 统计相关评论条数
	 * @param type
	 * @param valueId
	 * @param showType
	 * @return
	 */
	public int count(Integer type, Integer valueId, Integer showType);
	
	
	/**
	 * 查询评论
	 * @param type
	 * @param valueId
	 * @param showType
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<CommentEntity> query(Integer type, Integer valueId, Integer showType, Integer page, Integer size);
}
