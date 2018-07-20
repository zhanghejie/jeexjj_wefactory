<#--
/****************************************************
 * Description: 购物车的输入页面，包括添加和修改
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

<@input url="${base}/customer/cart/save" id=tabId>
   <input type="hidden" name="id" value="${cart.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${cart.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${cart.goodsId}" check-type="number">
   </@formgroup>
   <@formgroup title='goods_sn'>
	<input type="text" name="goodsSn" value="${cart.goodsSn}" >
   </@formgroup>
   <@formgroup title='goods_name'>
	<input type="text" name="goodsName" value="${cart.goodsName}" >
   </@formgroup>
   <@formgroup title='单价'>
	<input type="text" name="price" value="${cart.price}" >
   </@formgroup>
   <@formgroup title='数量'>
	<input type="text" name="number" value="${cart.number}" check-type="number">
   </@formgroup>
   <@formgroup title='规格属性组成的字符串，用来显示用'>
	<input type="text" name="goodsSpecVals" value="${cart.goodsSpecVals}" >
   </@formgroup>
   <@formgroup title='goods_spec_ids'>
	<input type="text" name="goodsSpecIds" value="${cart.goodsSpecIds}" >
   </@formgroup>
   <@formgroup title='checked'>
	<input type="text" name="checked" value="${cart.checked}" check-type="number">
   </@formgroup>
   <@formgroup title='pic_url'>
	<input type="text" name="picUrl" value="${cart.picUrl}" >
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=cart.addTime  default=true/>
   </@formgroup>
</@input>