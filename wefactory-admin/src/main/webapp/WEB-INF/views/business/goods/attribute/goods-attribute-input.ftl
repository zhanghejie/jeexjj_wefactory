<#--
/****************************************************
 * Description: t_business_goods_attribute的输入页面，包括添加和修改
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

<@input url="${base}/business/goods/attribute/save" id=tabId>
   <input type="hidden" name="id" value="${goodsAttribute.id}"/>
   
   <@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${goodsAttribute.goodsId}" check-type="required number">
   </@formgroup>
   <@formgroup title='value'>
	<input type="text" name="value" value="${goodsAttribute.value}" check-type="required">
   </@formgroup>
   <@formgroup title='attribute'>
	<input type="text" name="attribute" value="${goodsAttribute.attribute}" >
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=goodsAttribute.addTime  default=true/>
   </@formgroup>
   <@formgroup title='deleted'>
	<input type="text" name="deleted" value="${goodsAttribute.deleted}" check-type="number">
   </@formgroup>
</@input>