package com.xjj.wefactory.sec.service;

import java.util.Map;

import com.xjj.framework.exception.ValidationException;
import com.xjj.framework.service.XjjService;
import com.xjj.wefactory.sec.entity.XjjUser;

public interface UserService  extends XjjService<XjjUser>{
	/**
	 * 导入用户
	 * @param fileId
	 * @param orgId
	 * @param loginInfo
	 */
    public Map<String,Object> saveImportUser(Long fileId) throws ValidationException;
}
