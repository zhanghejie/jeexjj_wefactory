<#--
/****************************************************
 * Description: 收藏的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/customer/collect/save" id=tabId>
   <input type="hidden" name="id" value="${collect.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${collect.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='user_id'>
	<input type="text" name="userId" value="${collect.userId}" check-type="required number">
   </@formgroup>
   <@formgroup title='value_id'>
	<input type="text" name="valueId" value="${collect.valueId}" check-type="required number">
   </@formgroup>
   <@formgroup title='是否是关注'>
	<input type="text" name="isAttention" value="${collect.isAttention}" check-type="required number">
   </@formgroup>
   <@formgroup title='type_id'>
	<input type="text" name="typeId" value="${collect.typeId}" check-type="required number">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=collect.addTime  default=true/>
   </@formgroup>
</@input>