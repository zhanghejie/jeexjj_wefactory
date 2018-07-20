<#--
/****************************************************
 * Description: 关键词的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/business/keyword/save" id=tabId>
   <input type="hidden" name="id" value="${keyword.id}"/>
   
   <@formgroup title='seller_id'>
	<input type="text" name="sellerId" value="${keyword.sellerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='keyword'>
	<input type="text" name="keyword" value="${keyword.keyword}" check-type="required">
   </@formgroup>
   <@formgroup title='关键词的跳转链接'>
	<input type="text" name="url" value="${keyword.url}" check-type="required">
   </@formgroup>
   <@formgroup title='是否热点'>
	<input type="text" name="isHot" value="${keyword.isHot}" check-type="required number">
   </@formgroup>
   <@formgroup title='is_default'>
	<input type="text" name="isDefault" value="${keyword.isDefault}" check-type="required number">
   </@formgroup>
   <@formgroup title='is_show'>
	<input type="text" name="isShow" value="${keyword.isShow}" check-type="required number">
   </@formgroup>
   <@formgroup title='sort_order'>
	<input type="text" name="sortOrder" value="${keyword.sortOrder}" check-type="required number">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=keyword.addTime  default=true/>
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${keyword.status}" >
   </@formgroup>
</@input>