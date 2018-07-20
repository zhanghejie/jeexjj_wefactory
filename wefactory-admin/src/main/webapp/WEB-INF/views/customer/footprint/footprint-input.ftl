<#--
/****************************************************
 * Description: t_customer_footprint的输入页面，包括添加和修改
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

<@input url="${base}/customer/footprint/save" id=tabId>
   <input type="hidden" name="id" value="${footprint.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${footprint.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${footprint.goodsId}" check-type="required number">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=footprint.addTime  default=true/>
   </@formgroup>
</@input>