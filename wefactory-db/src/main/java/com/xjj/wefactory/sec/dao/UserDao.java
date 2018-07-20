package com.xjj.wefactory.sec.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.wefactory.sec.entity.XjjUser;

@Repository
public interface UserDao  extends XjjDAO<XjjUser> {
	public List<XjjUser> managerPage (@Param("query") HashMap<String,HashMap<String,Object>> queryMap, @Param("offset") int offset, @Param("limit") int limit);
}
