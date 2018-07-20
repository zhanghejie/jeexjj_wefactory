<#--
/****************************************************
 * Description: 客户优惠券的输入页面，包括添加和修改
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

<@input url="${base}/customer/buyer/coupon/save" id=tabId>
   <input type="hidden" name="id" value="${buyerCoupon.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${buyerCoupon.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='coupon_id'>
	<input type="text" name="couponId" value="${buyerCoupon.couponId}" check-type="required number">
   </@formgroup>
   <@formgroup title='user_id'>
	<input type="text" name="userId" value="${buyerCoupon.userId}" check-type="required number">
   </@formgroup>
   <@formgroup title='order_id'>
	<input type="text" name="orderId" value="${buyerCoupon.orderId}" check-type="required number">
   </@formgroup>
   <@formgroup title='used_time'>
	<@date name="usedTime" dateValue=buyerCoupon.usedTime required="required" default=true/>
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=buyerCoupon.addTime  default=true/>
   </@formgroup>
</@input>