<#--
/****************************************************
 * Description: 订单商品的输入页面，包括添加和修改
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

<@input url="${base}/customer/order/goods/save" id=tabId>
   <input type="hidden" name="id" value="${orderGoods.id}"/>
   
   <@formgroup title='order_id'>
	<input type="text" name="orderId" value="${orderGoods.orderId}" check-type="required number">
   </@formgroup>
   <@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${orderGoods.goodsId}" check-type="required number">
   </@formgroup>
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${orderGoods.customerId}" check-type="number">
   </@formgroup>
   <@formgroup title='商品名称'>
	<input type="text" name="goodsName" value="${orderGoods.goodsName}" check-type="required">
   </@formgroup>
   <@formgroup title='goods_sn'>
	<input type="text" name="goodsSn" value="${orderGoods.goodsSn}" check-type="required">
   </@formgroup>
   <@formgroup title='数量'>
	<input type="text" name="number" value="${orderGoods.number}" check-type="required number">
   </@formgroup>
   <@formgroup title='单价'>
	<input type="text" name="price" value="${orderGoods.price}" check-type="required">
   </@formgroup>
   <@formgroup title='goods_spec_vals'>
	<input type="text" name="goodsSpecVals" value="${orderGoods.goodsSpecVals}" check-type="required">
   </@formgroup>
   <@formgroup title='goods_spec_ids'>
	<input type="text" name="goodsSpecIds" value="${orderGoods.goodsSpecIds}" >
   </@formgroup>
   <@formgroup title='pic_url'>
	<input type="text" name="picUrl" value="${orderGoods.picUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=orderGoods.addTime  default=true/>
   </@formgroup>
</@input>