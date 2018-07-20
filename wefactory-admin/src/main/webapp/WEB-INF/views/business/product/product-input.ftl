<#--
/****************************************************
 * Description: t_business_product的输入页面，包括添加和修改
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

<@input url="${base}/business/product/save" id=tabId>
   <input type="hidden" name="id" value="${product.id}"/>
   
   <@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${product.goodsId}" check-type="required number">
   </@formgroup>
   <@formgroup title='goods_specification_ids'>
	<input type="text" name="goodsSpecificationIds" value="${product.goodsSpecificationIds}" check-type="required">
   </@formgroup>
   <@formgroup title='goods_number'>
	<input type="text" name="goodsNumber" value="${product.goodsNumber}" check-type="required number">
   </@formgroup>
   <@formgroup title='retail_price'>
	<input type="text" name="retailPrice" value="${product.retailPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='url'>
	<input type="text" name="url" value="${product.url}" >
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=product.addTime  default=true/>
   </@formgroup>
   <@formgroup title='deleted'>
	<input type="text" name="deleted" value="${product.deleted}" check-type="number">
   </@formgroup>
</@input>