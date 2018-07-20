package com.xjj.common;

import com.xjj.framework.Constants;


public class XJJConstants extends Constants{
	
	/**
	 * 是否有效
	 */
	public static final String COMMON_STATUS_VALID = "valid";
	public static final String COMMON_STATUS_INVALID = "invalid";
	public static final String[] COMMON_STATUS_LIST = {COMMON_STATUS_VALID,COMMON_STATUS_INVALID};
	
	
	public static final String COMMON_YESNO_YES = "yes";
	public static final String COMMON_YESNO_NO = "no";
	public static final String[] COMMON_YESNO = {COMMON_YESNO_YES,COMMON_YESNO_NO};
	
	/**
	 * 用户类型（管理员和普通用户）
	 */
	public static final String USER_TYPE_ADMIN = "admin";
	public static final String USER_TYPE_USER = "user";
	public static final String[] USER_TYPE = {USER_TYPE_ADMIN,USER_TYPE_USER};
	
}