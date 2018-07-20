<#--
/****************************************************
 * Description: 搜索历史的输入页面，包括添加和修改
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

<@input url="${base}/customer/search/history/save" id=tabId>
   <input type="hidden" name="id" value="${searchHistory.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${searchHistory.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='关键词'>
	<input type="text" name="keyword" value="${searchHistory.keyword}" check-type="required">
   </@formgroup>
   <@formgroup title='搜索来源，如PC、小程序、APP等'>
	<input type="text" name="comeFrom" value="${searchHistory.comeFrom}" check-type="required">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=searchHistory.addTime  default=true/>
   </@formgroup>
</@input>