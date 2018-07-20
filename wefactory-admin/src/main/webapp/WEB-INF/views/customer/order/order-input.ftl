<#--
/****************************************************
 * Description: 订单的输入页面，包括添加和修改
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

<@input url="${base}/customer/order/save" id=tabId>
   <input type="hidden" name="id" value="${order.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${order.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='order_sn'>
	<input type="text" name="orderSn" value="${order.orderSn}" check-type="required">
   </@formgroup>
   <@formgroup title='订单状态'>
	<input type="text" name="orderStatus" value="${order.orderStatus}" check-type="required number">
   </@formgroup>
   <@formgroup title='consignee'>
	<input type="text" name="consignee" value="${order.consignee}" check-type="required">
   </@formgroup>
   <@formgroup title='mobile'>
	<input type="text" name="mobile" value="${order.mobile}" check-type="required">
   </@formgroup>
   <@formgroup title='address'>
	<input type="text" name="address" value="${order.address}" check-type="required">
   </@formgroup>
   <@formgroup title='商品总费用'>
	<input type="text" name="goodsPrice" value="${order.goodsPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='配送费用'>
	<input type="text" name="freightPrice" value="${order.freightPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='优惠券减免'>
	<input type="text" name="couponPrice" value="${order.couponPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='用户积分减免'>
	<input type="text" name="integralPrice" value="${order.integralPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='订单费用， = goods_price + freight_price - coupon_price'>
	<input type="text" name="orderPrice" value="${order.orderPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='实付费用， = order_price - integral_price'>
	<input type="text" name="actualPrice" value="${order.actualPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='微信付款编号'>
	<input type="text" name="payId" value="${order.payId}" >
   </@formgroup>
   <@formgroup title='支付状态'>
	<input type="text" name="payStatus" value="${order.payStatus}" check-type="number">
   </@formgroup>
   <@formgroup title='微信付款时间'>
	<@date name="payTime" dateValue=order.payTime  default=true/>
   </@formgroup>
   <@formgroup title='发货编号'>
	<input type="text" name="shipSn" value="${order.shipSn}" >
   </@formgroup>
   <@formgroup title='发货快递公司'>
	<input type="text" name="shipChannel" value="${order.shipChannel}" >
   </@formgroup>
   <@formgroup title='发货开始时间'>
	<@date name="shipStartTime" dateValue=order.shipStartTime  default=true/>
   </@formgroup>
   <@formgroup title='发货结束时间'>
	<@date name="shipEndTime" dateValue=order.shipEndTime  default=true/>
   </@formgroup>
   <@formgroup title='用户确认收货时间'>
	<@date name="confirmTime" dateValue=order.confirmTime  default=true/>
   </@formgroup>
   <@formgroup title='end_time'>
	<@date name="endTime" dateValue=order.endTime  default=true/>
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=order.addTime  default=true/>
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${order.status}" >
   </@formgroup>
</@input>